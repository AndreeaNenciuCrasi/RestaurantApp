package com.codecool.restaurant.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaymentController {

    @Autowired
    private final PaymentService paymentService;


    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public void getPaymentDetails(@RequestBody PaymentDetailsModel paymentDetailsModel) {
        paymentService.registerOrder(paymentDetailsModel);

        System.out.println(paymentDetailsModel);

    }
}
