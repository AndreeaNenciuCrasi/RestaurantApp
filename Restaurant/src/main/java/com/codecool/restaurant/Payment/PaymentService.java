package com.codecool.restaurant.Payment;

import com.codecool.restaurant.Meal.MealsToCartService;
import com.codecool.restaurant.Payment.common.PaypalOrderModel;
import com.codecool.restaurant.ShoppingCart.ShoppingCart;
import com.codecool.restaurant.ShoppingCart.ShoppingCartException;
import com.codecool.restaurant.ShoppingCart.ShoppingCartService;
import com.codecool.restaurant.User.UserApp;
import com.codecool.restaurant.User.UserService;
import com.codecool.restaurant.order.UserOrder;
import com.codecool.restaurant.order.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    private final RestTemplate restTemplate;

    public PaymentService(MealsToCartService mealsToCartService, UserService userService, ShoppingCartService shoppingCartService, UserOrderRepository userOrderRepository, RestTemplate restTemplate) {
        this.mealsToCartService = mealsToCartService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.userOrderRepository = userOrderRepository;
        this.restTemplate = restTemplate;
    }

    public void registerOrder(PaymentDetailsModel paymentDetailsModel) throws ShoppingCartException {
        String userName = paymentDetailsModel.getUserName();
        String paymentStatus = paymentDetailsModel.getPaymentStatus();

        UserApp userApp = userService.getUserByUsername(userName).orElse(null);
        ShoppingCart shoppingCart = shoppingCartService.getCartByUser(userApp);

        if (shoppingCart == null) {
            throw new ShoppingCartException();
        }
        double total = mealsToCartService.getTotalPrice(shoppingCart);

        UserOrder userOrder = new UserOrder(paymentStatus,total,userApp);

        userOrderRepository.save(userOrder);

        mealsToCartService.clearCart(shoppingCart);
    }


    public String requestPayment(PaypalOrderModel paypalOrderModel) {
        String result;
        PaypalOrderModel response = restTemplate.postForObject("http://payment-service/api/v1/payment", paypalOrderModel, PaypalOrderModel.class);
        result=response.getLinkPaypal();
        return result;
    }
}
