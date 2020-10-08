package com.codecool.restaurant.FavoriteMeal;

import com.codecool.restaurant.User.UserApp;
import com.codecool.restaurant.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequestMapping("yellowrestaurant/api/v1/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FavoriteMealController {

    private final FavoriteMealService favoriteMealService;
    private final UserService userService;

    @Autowired
    public FavoriteMealController(FavoriteMealService favoriteMealService, UserService userService) {
        this.favoriteMealService = favoriteMealService;
        this.userService = userService;
    }

    @PutMapping(path = "{username}/favorites/{idMeal}")
    public void addMealToFavorites(@PathVariable("username") String username, @RequestBody HashMap<String, String> listMealAttributes){
        UserApp userApp = userService.getUserByUsername(username).orElse(null);
        String idMeal = listMealAttributes.get("idMeal");
        String name = listMealAttributes.get("strMeal");
        String image = listMealAttributes.get("strMealThumb");
        FavoriteMeal favoriteMeal = new FavoriteMeal(userApp,name,image,idMeal);
        favoriteMealService.addFavoriteMeal(favoriteMeal);
    }

    @GetMapping(path = "{username}/favorites")
    public List<FavoriteMeal> getFavorites(@PathVariable("username") String username) {
        UserApp userApp = userService.getUserByUsername(username).orElse(null);
        return favoriteMealService.getAllFavoriteMeals(userApp.getId());
    }

    @DeleteMapping(path = "{username}/favorites/delete/{idMeal}")
    public void deleteFavoriteByIdMeal(@PathVariable("username") String username, @PathVariable("idMeal") String idMeal) {
        UserApp userApp = userService.getUserByUsername(username).orElse(null);
        List<FavoriteMeal> favoriteMeals = favoriteMealService.getAllFavoriteMeals(userApp.getId());
        for (FavoriteMeal favoriteMeal : favoriteMeals) {
            if (favoriteMeal.getIdMeal().equals(idMeal)) {
                favoriteMealService.deleteFavoriteMealByIdMeal(idMeal);
            }
        }
    }
}
