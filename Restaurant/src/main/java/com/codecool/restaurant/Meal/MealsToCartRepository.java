package com.codecool.restaurant.Meal;

import com.codecool.restaurant.ShoppingCart.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("mealToCartDB")
public interface MealsToCartRepository extends JpaRepository<MealsToCart, Long> {
    List<MealsToCart> findAllByShoppingCartId(Long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update MealsToCart mc set mc.quantity = mc.quantity+1 where mc.meal=:meal ")
    void updateQuantity(Meal meal);

    @Query(value= "SELECT SUM(mc.quantity) FROM MealsToCart mc WHERE mc.shoppingCart=:shoppingCart")
    double totalQty(ShoppingCart shoppingCart);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="DELETE FROM MealsToCart mc where mc.shoppingCart=:shoppingCart")
    void deleteByShoppingCart(ShoppingCart shoppingCart);
}
