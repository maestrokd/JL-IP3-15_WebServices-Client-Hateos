package com.infoPulse.lessons.model.service;


import com.infoPulse.lessons.model.entity.CustomerService;
import com.infoPulse.lessons.model.entity.ServiceStatus;
import com.infoPulse.lessons.model.repository.CustomerServiceRepository;
import com.infoPulse.lessons.model.repository.ServiceStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceServiceImpl {

    private CustomerServiceRepository repository;

    @Autowired
    public void setCustomerServiceRepository(CustomerServiceRepository repository) {
        this.repository = repository;
    }

    private ServiceStatusRepository serviceStatusRepository;

    @Autowired
    public void setServiceStatusRepository(ServiceStatusRepository serviceStatusRepository) {
        this.serviceStatusRepository = serviceStatusRepository;
    }

    public List<CustomerService> findCustomerServicesForCustomer(String phoneNumber) {
        return repository.findCustomerServicesByCustomerPhoneNumber(phoneNumber);
    }

    @Transactional
    public void changeTheStatus(String phoneNumber, String serviceName) {
        CustomerService customerService = repository.findCustomerServiceByCustomerPhoneNumberAndServiceName(phoneNumber, serviceName);
        List<ServiceStatus> serviceStatusList = serviceStatusRepository.findAll();
        if (customerService.getServiceStatus().equals(serviceStatusList.get(0))) {
            serviceStatusList.get(0).getCustomerServiceList().remove(customerService);
            customerService.setServiceStatus(serviceStatusList.get(1));
            serviceStatusList.get(1).getCustomerServiceList().add(customerService);
        } else {
            serviceStatusList.get(1).getCustomerServiceList().remove(customerService);
            customerService.setServiceStatus(serviceStatusList.get(0));
            serviceStatusList.get(0).getCustomerServiceList().add(customerService);
        }
        repository.save(customerService);
    }

}
