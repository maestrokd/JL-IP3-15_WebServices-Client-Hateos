package com.infoPulse.lessons.model.dto;

import com.infoPulse.lessons.model.entity.Customer;
import com.infoPulse.lessons.validators.userLoginValidatorAtCustomerUpdateForm.annotations.ValidLoginIsExist;

public class CustomerDTO {

    // Fields
    private String phoneNumber;
    private String customerStatusName = "deactivated";
    private float balance;
    private String billingAddress;
    @ValidLoginIsExist
    private String userLogin;


    // Constructors
    public CustomerDTO() {
    }

    public CustomerDTO(Customer customer) {
        this.phoneNumber = customer.getPhoneNumber();
        this.customerStatusName = customer.getCustomerStatus().getName();
        this.balance = customer.getBalance();
        this.billingAddress = customer.getBillingAddress();
        this.userLogin = customer.getUser().getLogin();
    }


    // Getters and Setters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerStatusName() {
        return customerStatusName;
    }

    public void setCustomerStatusName(String customerStatusName) {
        this.customerStatusName = customerStatusName;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

}
