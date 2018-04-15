package com.infoPulse.lessons.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.infoPulse.lessons.model.dto.CustomerDTO;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "customer")
public class Customer {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "phone_number", length = 25)
    private String phoneNumber = null;

    @Column(name = "billing_address")
    private String billingAddress;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "customer_status_id", foreignKey = @ForeignKey(name = "fk_customer_status_customer"))
    private CustomerStatus customerStatus;

    @Column(name = "activated_date")
    private Date activatedDate;

    @Column(name = "deactivated_date")
    private Date deactivatedDate;

    @Column(name = "balance")
    private float balance;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_customer"))
    private User user;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH}, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<CustomerService> customerServiceSet = new HashSet<>();


//    @ManyToMany
//    @JoinTable(name = "cus_ser",
//            joinColumns = {@JoinColumn(name = "customer_id")},
//            inverseJoinColumns = {@JoinColumn(name = "servise_id")})
//    private List<Service> serviceList = new ArrayList<>();


    @JsonManagedReference
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH}, orphanRemoval = true, mappedBy = "customer")
    private Set<Event> eventList = new HashSet<>();



    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH}, orphanRemoval = true, mappedBy = "customer")
    private List<Bill> billList = new ArrayList<>();


    // to avoid infinite recursion in JSON master-detail-master...
    @JsonManagedReference
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH}, orphanRemoval = true, mappedBy = "customer")
    private List<Payment> paymentList = new ArrayList<>();


    // Constructors
    public Customer() {}

    public Customer(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Customer(CustomerDTO customerDTO, User user, CustomerStatus customerStatus) {
        this.phoneNumber = customerDTO.getPhoneNumber();
        this.billingAddress = customerDTO.getBillingAddress();
        this.customerStatus = customerStatus;
        this.balance = customerDTO.getBalance();
        this.user = user;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public CustomerStatus getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(CustomerStatus customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Date getActivatedDate() {
        return activatedDate;
    }

    public void setActivatedDate(Date activatedDate) {
        this.activatedDate = activatedDate;
    }

    public Date getDeactivatedDate() {
        return deactivatedDate;
    }

    public void setDeactivatedDate(Date deactivatedDate) {
        this.deactivatedDate = deactivatedDate;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<CustomerService> getCustomerServiceSet() {
        return customerServiceSet;
    }

    public void setCustomerServiceSet(Set<CustomerService> customerServiceSet) {
        this.customerServiceSet = customerServiceSet;
    }

    public Set<Event> getEventList() {
        return eventList;
    }

    public void setEventList(Set<Event> eventList) {
        this.eventList = eventList;
    }

    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }


    // Methods
    @Override
    public String toString() {
        return "Customer{" +
                "phoneNumber='" + phoneNumber + '\'' +
//                ", customerStatus='" + customerStatus.getName() + '\'' +
                ", balance=" + balance +
//                ", user=" + user.getLogin() +
                ", hash=" + this.hashCode() +
//                ", customerServiceSet=" + customerServiceSet +
               // ", eventList=" + eventList +
//                ", billList=" + billList +
//                ", paymentList=" + paymentList +
                '}';
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
