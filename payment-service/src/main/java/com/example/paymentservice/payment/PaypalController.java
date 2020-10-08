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
    public PaypalOrderModel payment(@RequestBody PaypalOrderModel paypalOrderModel) {
        System.out.println("MicroService");
        try {
            Payment payment = service.createPayment(paypalOrderModel.getTotalAmount(), paypalOrderModel.getDescription(), CANCEL_URL,
                    SUCCESS_URL);
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    paypalOrderModel.setLinkPaypal(link.getHref());
                    return paypalOrderModel;
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        paypalOrderModel.setLinkPaypal(ERROR_URL);
        return paypalOrderModel;
    }


//    @PostMapping("/details")
//        public String successPay(@RequestBody PaymentDetailsModel paymentDetailsModel) {
//        try {
//            Payment payment = service.getPaymentDetails(paymentDetailsModel.getPaymentId(), paymentDetailsModel.getPayerId());
//            System.out.println(payment.toJSON());
//            if (payment.getState().equals("approved")) {
//                return "success";
//            }
//        } catch (PayPalRESTException e) {
//            System.out.println(e.getMessage());
//        }
//        return "error";
//    }


}
