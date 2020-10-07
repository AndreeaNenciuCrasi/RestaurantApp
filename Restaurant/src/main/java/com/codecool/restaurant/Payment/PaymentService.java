package com.codecool.restaurant.Payment;

import com.codecool.restaurant.Meal.MealsToCartService;
import com.codecool.restaurant.ShoppingCart.ShoppingCart;
import com.codecool.restaurant.ShoppingCart.ShoppingCartService;
import com.codecool.restaurant.User.UserApp;
import com.codecool.restaurant.User.UserService;
import com.codecool.restaurant.order.UserOrder;
import com.codecool.restaurant.order.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private final MealsToCartService mealsToCartService;
    @Autowired
    private final UserService userService;
    @Autowired
    private final ShoppingCartService shoppingCartService;
    @Autowired
    private final UserOrderRepository userOrderRepository;

    public PaymentService(MealsToCartService mealsToCartService, UserService userService, ShoppingCartService shoppingCartService, UserOrderRepository userOrderRepository) {
        this.mealsToCartService = mealsToCartService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.userOrderRepository = userOrderRepository;
    }

    public void registerOrder(PaymentDetailsModel paymentDetailsModel) {
        String userName = paymentDetailsModel.getUserName();
        String paymentStatus = paymentDetailsModel.getPaymentStatus();

        UserApp userApp = userService.getUserByUsername(userName).orElse(null);
        ShoppingCart shoppingCart = shoppingCartService.getCartByUser(userApp);
        double total = mealsToCartService.getTotalPrice(shoppingCart);

        UserOrder userOrder = new UserOrder(paymentStatus,total,userApp);

        userOrderRepository.save(userOrder);

        mealsToCartService.clearCart(shoppingCart);
    }


}
