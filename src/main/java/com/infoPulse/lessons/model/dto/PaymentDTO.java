package com.infoPulse.lessons.model.dto;


import com.infoPulse.lessons.model.entity.Payment;

public class PaymentDTO {

    private Integer paymentId;

    private Integer customerId;

    private Float amount;

    private String date;

    private String channel;

    public PaymentDTO() {
    }

    public PaymentDTO(Integer paymentId, Integer customerId, Float amount, String date, String channel) {
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.amount = amount;
        this.date = date;
        this.channel = channel;
    }

    public PaymentDTO(Payment payment) {
        this.paymentId = payment.getId();
        this.customerId = payment.getCustomer().getId();
        this.amount = payment.getAmount();
        try{
            this.date = payment.getDateAsString();
        }catch (NullPointerException e){}
        this.channel = payment.getChannel();
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public Integer getCustomerId() {
        return customerId;
    }


    public Float getAmount() {
        return amount;
    }


    public String getDate() {
        return date;
    }


    public String getChannel() {
        return channel;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "customerId=" + customerId +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", channel='" + channel + '\'' +
                '}';
    }
}
