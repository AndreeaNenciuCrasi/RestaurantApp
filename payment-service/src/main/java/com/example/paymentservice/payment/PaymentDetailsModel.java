package com.example.paymentservice.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentDetailsModel {

    private String paymentId;
    private String payerId;
}
