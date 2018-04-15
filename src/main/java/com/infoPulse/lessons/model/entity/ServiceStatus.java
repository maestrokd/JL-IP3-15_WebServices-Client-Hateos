package com.infoPulse.lessons.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "service_status")
public class ServiceStatus {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name = ServiceStatusNames.DISABLED.serviceStatusNames;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH}, orphanRemoval = true, mappedBy = "serviceStatus")
    private Set<CustomerService> customerServiceList = new HashSet<>();


    // Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CustomerService> getCustomerServiceList() {
        return customerServiceList;
    }

    public void setCustomerServiceList(Set<CustomerService> customerServiceList) {
        this.customerServiceList = customerServiceList;
    }

public enum ServiceStatusNames {
    DISABLED("disabled"),
    ENABLED("enabled");

    private String serviceStatusNames;

    ServiceStatusNames(String serviceStatusNames) {
        this.serviceStatusNames = serviceStatusNames;
    }

    public String getServiceStatusNames() {
        return serviceStatusNames;
    }
}

}

