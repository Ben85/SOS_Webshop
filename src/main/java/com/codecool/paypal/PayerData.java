package com.codecool.paypal;

public class PayerData {
    private String paymentId;
    private String payerId;

    public String getPaymentId() {
        return paymentId;
    }

    public String getPayerId() {
        return payerId;
    }

    public PayerData(String paymentId, String payerId) {
        this.paymentId = paymentId;
        this.payerId = payerId;
    }
}
