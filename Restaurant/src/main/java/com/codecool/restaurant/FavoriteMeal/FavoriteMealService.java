package com.codecool.restaurant.FavoriteMeal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteMealService {

    private final FavoriteMealRepository favoriteMealRepository;

    @Autowired
    public FavoriteMealService(FavoriteMealRepository favoriteMealRepository){
        this.favoriteMealRepository = favoriteMealRepository;
    }

    public void addFavoriteMeal(FavoriteMeal favoriteMeal) {
        favoriteMealRepository.save(favoriteMeal);
    }

    public List<FavoriteMeal> getAllFavoriteMeals(Long user_id) {
        return favoriteMealRepository.getAllFavoriteMealsByUserAppId(user_id);
    }
}
