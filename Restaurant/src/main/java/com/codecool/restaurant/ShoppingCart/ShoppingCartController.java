package com.codecool.restaurant.ShoppingCart;


import com.codecool.restaurant.Meal.Meal;
import com.codecool.restaurant.Meal.MealService;
import com.codecool.restaurant.Meal.MealsToCart;
import com.codecool.restaurant.Meal.MealsToCartService;
import com.codecool.restaurant.User.UserApp;
import com.codecool.restaurant.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RequestMapping("yellowrestaurant/api/v1/cart")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final MealService mealService;
    private final UserService userService;
    private final MealsToCartService mealsToCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, MealService mealService, UserService userService, MealsToCartService mealsToCartService) {
        this.shoppingCartService = shoppingCartService;
        this.mealService = mealService;
        this.userService = userService;
        this.mealsToCartService = mealsToCartService;
    }


    @PostMapping(path = "{username}/meal/{mealName}/tocart/{image}")
    public void addMealToDB(@PathVariable("username") String username, @PathVariable("mealName") String mealName, @PathVariable("image") String image){

        if (mealService.findByName(mealName) != null) {
            mealsToCartService.updateQuantity(mealService.findByName(mealName));
        }else{
            Meal meal = new Meal(mealName, "https://www.themealdb.com/images/media/meals/" + image);
            mealService.addMeal(meal);
            UserApp userApp = userService.getUserByUsername(username)
                    .orElse(null);
            ShoppingCart cart = shoppingCartService.getCartByUser(userApp);
            mealsToCartService.addMealsToCart(new MealsToCart(cart, meal));
        }
    }


    @GetMapping(path="mealsInCart/{id}")
    public Map<Meal, Integer> getAllMealInCart(@PathVariable("id") Long id){
        Optional<ShoppingCart> cart = shoppingCartService.getCartById(id);
        System.out.println(id);
        List<MealsToCart> list = mealsToCartService.getAllMealsByCart(cart.get());
        Map<Meal, Integer> listOfMeals = new HashMap<>();
        for (MealsToCart meal: list
             ) {
            listOfMeals.put(meal.getMeal(),meal.getQuantity());
        }
        return listOfMeals;
    }


    @GetMapping(path="view/{username}")
    public long getCartIdByUserName(@PathVariable("username") String username){
          UserApp userApp = userService.getUserByUsername(username)
                .orElse(null);
          return shoppingCartService.getCartByUser(userApp).getId();
    }

}