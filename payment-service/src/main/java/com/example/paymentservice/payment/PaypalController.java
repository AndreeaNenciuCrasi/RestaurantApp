package com.example.paymentservice.payment;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
public class PaypalController {

    @Autowired
    private final PaypalService service;

    public static final String SUCCESS_URL = "http://localhost:3000/payment/success";
    public static final String CANCEL_URL = "http://localhost:3000/payment/cancel";
    public static final String ERROR_URL = "http://localhost:3000/payment/error";

    public PaypalController(PaypalService service) {
        this.service = service;
    }


    @PostMapping()
    public String payment(@RequestBody OrderModel orderModel) {
		try {
			Payment payment = service.createPayment(orderModel.getTotalAmount(), orderModel.getDescription(), CANCEL_URL,
					SUCCESS_URL);
			for(Links link:payment.getLinks()) {
				if(link.getRel().equals("approval_url")) {
					return link.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			e.printStackTrace();
		}
		return ERROR_URL;
    }


    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }
}
