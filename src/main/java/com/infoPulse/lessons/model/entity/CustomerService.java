package com.infoPulse.lessons.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer_service")
public class CustomerService implements Serializable {


    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

//    @Id
    @ManyToOne
//    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_customer_customer_services"))
    private Customer customer;

//    @Id
    @ManyToOne
//    @JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "fk_service_customer_services"))
    private Service service;

    @ManyToOne
    @JoinColumn(name = "service_status_id", foreignKey = @ForeignKey(name = "fk_service_status_customer_services"))
    private ServiceStatus serviceStatus;

    // Constructors
    public CustomerService() {
    }

    public CustomerService(String phoneNumber, String serviceName) {
        this.customer = new Customer(phoneNumber);
        this.service = new Service(serviceName);
    }


    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }


    // Methods


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerService that = (CustomerService) o;

        return service != null ? service.equals(that.service) : that.service == null;
    }

    @Override
    public int hashCode() {
        return service != null ? service.hashCode() : 0;
    }
}
