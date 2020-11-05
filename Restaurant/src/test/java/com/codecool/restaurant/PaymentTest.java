package com.codecool.restaurant;

import com.codecool.restaurant.Meal.MealsToCartService;
import com.codecool.restaurant.Payment.PaymentDetailsModel;
import com.codecool.restaurant.Payment.PaymentService;
import com.codecool.restaurant.ShoppingCart.ShoppingCart;
import com.codecool.restaurant.ShoppingCart.ShoppingCartException;
import com.codecool.restaurant.ShoppingCart.ShoppingCartService;
import com.codecool.restaurant.User.UserApp;
import com.codecool.restaurant.User.UserService;
import com.codecool.restaurant.order.UserOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaymentTest {

    @Test
    void testRegisterOrderThrowsExceptionWithEmptyCard() {

        MealsToCartService mealsToCartService=mock(MealsToCartService.class);

        UserService userService=mock(UserService.class);

        ShoppingCartService shoppingCartService=mock(ShoppingCartService.class);

        UserOrderRepository userOrderRepository=mock(UserOrderRepository.class);

        RestTemplate restTemplate=mock(RestTemplate.class);

        PaymentService paymentService = new PaymentService(mealsToCartService, userService, shoppingCartService, userOrderRepository, restTemplate);
        PaymentDetailsModel paymentDetailsModel = new PaymentDetailsModel("j.doe", "success");

        UserApp userApp = new UserApp("Jane", "Doe", "j.doe", "j.doe@gmail.com", "", "", "pass");
        when(userService.getUserByUsername("j.doe")).thenReturn(Optional.of(userApp));
        when(shoppingCartService.getCartByUser(userApp)).thenReturn(null);

        assertThrows(ShoppingCartException.class, () -> paymentService.registerOrder(paymentDetailsModel));
    }
}

