//package com.infoPulse.lessons.model.entity;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * @author Danny Briskin (sql.coach.kiev@gmail.com)
// */
//@Entity
//@Table(name = "oper_customers")
//@Component
//public class Customer {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "ID")
//    private Integer id;
//
//    @Column(name = "name")
//    private String customerName;
//
//    @Column
//    private String billingAddress;
//
//    @Column
//    private Float customerBalance;
//
//    @Column(nullable = false)
//    private Date customerActivated;
//
//    @Column(nullable = false)
//    private Date customerDeactivated;
//
//    // to avoid infinite recursion in JSON master-detail-master...
//    @JsonManagedReference
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customer")
//    private List<Payment> paymentList = new ArrayList<>();
//
//
//    public Customer() {
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getCustomerName() {
//        return customerName;
//    }
//
//    public void setCustomerName(String customerName) {
//        this.customerName = customerName;
//    }
//
//    public String getBillingAddress() {
//        return billingAddress;
//    }
//
//    public void setBillingAddress(String billingAddress) {
//        this.billingAddress = billingAddress;
//    }
//
//    public Float getCustomerBalance() {
//        return customerBalance;
//    }
//
//    public void setCustomerBalance(Float customerBalance) {
//        this.customerBalance = customerBalance;
//    }
//
//    public Date getCustomerActivated() {
//        return customerActivated;
//    }
//
//    public void setCustomerActivated(Date customerActivated) {
//        this.customerActivated = customerActivated;
//    }
//
//    public Date getCustomerDeactivated() {
//        return customerDeactivated;
//    }
//
//    public void setCustomerDeactivated(Date customerDeactivated) {
//        this.customerDeactivated = customerDeactivated;
//    }
//
//    public List<Payment> getPaymentList() {
//        return paymentList;
//    }
//
//    public void setPaymentList(List<Payment> paymentList) {
//        this.paymentList = paymentList;
//    }
//}
