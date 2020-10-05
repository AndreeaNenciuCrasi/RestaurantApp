package com.codecool.restaurant.FavoriteMeal;

import com.codecool.restaurant.FavoriteMeal.FavoriteMeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteMealRepository extends JpaRepository<FavoriteMeal, Long> {

    List<FavoriteMeal> getAllFavoriteMealsByUserAppId(Long user_id);

}
