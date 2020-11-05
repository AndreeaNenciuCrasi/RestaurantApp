package com.codecool.restaurant.Payment;

import com.codecool.restaurant.Payment.common.PaypalOrderModel;
import com.codecool.restaurant.ShoppingCart.ShoppingCartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// to do: write code for exceptions handling!!!
@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaymentController {

    @Autowired
    private final PaymentService paymentService;


    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/request-payment")
    public String requestPayment(@RequestBody PaypalOrderModel paypalOrderModel){
        return paymentService.requestPayment(paypalOrderModel);

    }

    @PostMapping
    public String getPaymentDetails(@RequestBody PaymentDetailsModel paymentDetailsModel)
    {
        try {
            paymentService.registerOrder(paymentDetailsModel);
        } catch (ShoppingCartException e) {
            return "Username does not exist!";
        }
        return "Success!";

    }
}
