package com.example.paymentservice.payment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.api.payments.PaymentHistory;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

@Configuration
public class PaypalConfig {

    private String clientId = "AaiXPDpObw2Kjdem44-qsQGbbFN-Mn0sDYN3uFkgySOmnybtCYuRPwy0LCmW7HcR5Ysuy564fdF33mTc";
    private String clientSecret = "EDxQWyEdcQ-eERlb_2K8EA6ovfNa7yu1TxlpWwWMWtlpSmjL68tj70Ag0-wD14tQux5vSCa5Rw035XlU";
    private String mode = "sandbox";

    @Bean
    public Map<String, String> paypalSdkConfig() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("mode", mode);
        return configMap;
    }

    @Bean
    public OAuthTokenCredential oAuthTokenCredential() {
        return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
    }

    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        APIContext context = new APIContext(oAuthTokenCredential().getAccessToken());
        context.setConfigurationMap(paypalSdkConfig());
        return context;
    }

}
