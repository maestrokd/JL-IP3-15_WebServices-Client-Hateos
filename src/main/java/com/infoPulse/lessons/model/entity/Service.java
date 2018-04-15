package com.infoPulse.lessons.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.*;

@Entity
@Table(name = "service")
//@XmlRootElement(name = "service")
public class Service {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "payroll")
    private float payroll;

    @XmlTransient
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH}, orphanRemoval = true, mappedBy = "customer", fetch = FetchType.EAGER)
    private List<CustomerService> customerServiceList = new ArrayList<>();


//    @ManyToMany(mappedBy = "serviceList")
//    private List<Customer> customerList = new ArrayList<>();


    @XmlTransient
    @JsonManagedReference
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH}, orphanRemoval = true, mappedBy = "customer")
    private Set<Event> eventList = new HashSet<>();


    // Constructors
    public Service() {
    }

    public Service(String name) {
        this.name = name;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

 //   @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  //  @XmlElement
    public float getPayroll() {
        return payroll;
    }

    public void setPayroll(float payroll) {
        this.payroll = payroll;
    }

    public List<CustomerService> getCustomerServiceList() {
        return customerServiceList;
    }

    public void setCustomerServiceList(List<CustomerService> customerServiceList) {
        this.customerServiceList = customerServiceList;
    }

    public Set<Event> getEventList() {
        return eventList;
    }

    public void setEventList(Set<Event> eventList) {
        this.eventList = eventList;
    }


    // Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Service service = (Service) o;

        return name != null ? name.equals(service.name) : service.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                ", payroll=" + payroll +
                '}';
    }
}
