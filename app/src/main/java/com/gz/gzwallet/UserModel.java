package com.gz.gzwallet;

public class UserModel {

    String paymentId;
    private String user_number;
    private String totalAmount;

    public UserModel() {
    }

    public UserModel(String paymentId, String user_number, String totalAmount) {
        this.paymentId = paymentId;
        this.user_number = user_number;
        this.totalAmount = totalAmount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getUser_number() {
        return user_number;
    }

    public void setUser_number(String user_number) {
        this.user_number = user_number;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
