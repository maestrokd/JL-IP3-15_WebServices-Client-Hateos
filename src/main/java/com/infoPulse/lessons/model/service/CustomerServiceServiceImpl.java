package com.infoPulse.lessons.model.service;


import com.infoPulse.lessons.model.entity.Customer;
import com.infoPulse.lessons.model.entity.CustomerService;
import com.infoPulse.lessons.model.entity.ServiceStatus;
import com.infoPulse.lessons.model.repository.CustomerRepository;
import com.infoPulse.lessons.model.repository.CustomerServiceRepository;
import com.infoPulse.lessons.model.repository.ServiceRepository;
import com.infoPulse.lessons.model.repository.ServiceStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceServiceImpl implements CustomerServiceService {

    // Fields
    private CustomerServiceRepository customerServiceRepository;
    private ServiceRepository serviceRepository;
    private ServiceStatusRepository serviceStatusRepository;
    private CustomerRepository customerRepository;


    // Getters and Setters
    @Autowired
    public void setCustomerServiceRepository(CustomerServiceRepository customerServiceRepository) {
        this.customerServiceRepository = customerServiceRepository;
    }

    @Autowired
    public void setServiceRepository(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Autowired
    public void setServiceStatusRepository(ServiceStatusRepository serviceStatusRepository) {
        this.serviceStatusRepository = serviceStatusRepository;
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    // Methods
    @Override
    public List<CustomerService> findCustomerServicesForCustomer(String phoneNumber) {
        return customerServiceRepository.findCustomerServicesByCustomerPhoneNumber(phoneNumber);
    }


    @Transactional
    @Override
    public void changeTheStatus(String phoneNumber, String serviceName) {
        CustomerService customerService = customerServiceRepository.findCustomerServiceByCustomerPhoneNumberAndServiceName(phoneNumber, serviceName);
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
        customerServiceRepository.save(customerService);
    }


    @Override
    public CustomerService addCustomerService(CustomerService customerService) {

        Customer selectedCustomer = customerRepository.findCustomerByPhoneNumber(customerService.getCustomer().getPhoneNumber());
        com.infoPulse.lessons.model.entity.Service selectedService = serviceRepository.findServiceByName(customerService.getService().getName());
        ServiceStatus serviceStatus = serviceStatusRepository.findServiceStatusByName(ServiceStatus.ServiceStatusNames.DISABLED.getServiceStatusNames());


        CustomerService newCustomerService = new CustomerService();
        newCustomerService.setCustomer(selectedCustomer);
        newCustomerService.setService(selectedService);
        newCustomerService.setServiceStatus(serviceStatus);

        if (selectedCustomer.getCustomerServiceSet().contains(newCustomerService)) {
            return null;
        }

        selectedCustomer.getCustomerServiceSet().add(customerService);
        selectedService.getCustomerServiceList().add(customerService);

//        selectedCustomer.addService(selectedService, serviceStatus);
//        customerRepository.save(selectedCustomer);

        return customerServiceRepository.saveAndFlush(newCustomerService);
    }

    @Override
    public void deleteCustomerService(CustomerService customerService) {
        CustomerService customerServiceFromDB = customerServiceRepository.findCustomerServiceByCustomerPhoneNumberAndServiceName(customerService.getCustomer().getPhoneNumber(), customerService.getService().getName());
        customerServiceRepository.delete(customerServiceFromDB);
    }
}
