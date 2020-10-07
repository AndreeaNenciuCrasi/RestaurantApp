package com.codecool.restaurant.Meal;

import com.codecool.restaurant.ShoppingCart.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealsToCartService {
    private final MealsToCartRepository mealsToCartRepository;

    @Autowired
    public MealsToCartService(@Qualifier("mealToCartDB")MealsToCartRepository mealsToCartRepository) {
        this.mealsToCartRepository = mealsToCartRepository;
    }

    public void addMealsToCart(MealsToCart mealsToCart){
        mealsToCartRepository.save(mealsToCart);
    }

    public List<MealsToCart> getAllMealsByCart(ShoppingCart shoppingCart) {

        return mealsToCartRepository.findAllByShoppingCartId(shoppingCart.getId());
    }

    public void updateQuantity(Meal meal) {
        mealsToCartRepository.updateQuantity(meal);
    }

    public double getTotalPrice(ShoppingCart shoppingCart) {
        return mealsToCartRepository.totalQty(shoppingCart) * 5;
    }

    public void clearCart(ShoppingCart shoppingCart) {
        mealsToCartRepository.deleteByShoppingCart(shoppingCart);
    }
}
