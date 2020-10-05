package com.codecool.restaurant.Meal;

import com.codecool.restaurant.ShoppingCart.ShoppingCart;

import javax.persistence.*;

@Entity
@Table(name="MEALS_TO_CART")
public class MealsToCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity = 1;

    @ManyToOne
    @JoinColumn(name="shoppingCart_id")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name="meal_id")
    private Meal meal;


    public MealsToCart() {
    }

    public MealsToCart(ShoppingCart shoppingCart, Meal meal) {
        this.shoppingCart = shoppingCart;
        this.meal = meal;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
