package com.codecool.restaurant.ShoppingCart;

import com.codecool.restaurant.ShoppingCart.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("cartDB")
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

        @Query("SELECT c FROM ShoppingCart c JOIN  c.userApp u WHERE u.userName = ?1")
        ShoppingCart findShoppingcartByUsername(String username);


}