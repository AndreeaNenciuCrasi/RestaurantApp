package com.codecool.restaurant.FavoriteMeal;

import com.codecool.restaurant.FavoriteMeal.FavoriteMeal;
import com.codecool.restaurant.User.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface FavoriteMealRepository extends JpaRepository<FavoriteMeal, Long> {

    List<FavoriteMeal> getAllFavoriteMealsByUserAppId(Long user_id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE from FavoriteMeal fm where fm.idMeal = :idMeal")
    void deleteFavoriteMealByIdMeal(String idMeal);

}
