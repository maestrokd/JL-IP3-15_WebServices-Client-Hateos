package com.infoPulse.lessons.model.service;

import com.infoPulse.lessons.model.dto.UserDto;
import com.infoPulse.lessons.model.entity.*;
import com.infoPulse.lessons.model.repository.*;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class CustomerServiceImpl {

    // Fields

    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private CustomerStatusRepository customerStatusRepository;
    private ServiceRepository serviceRepository;
    private ServiceStatusRepository serviceStatusRepository;
    private CustomerServiceRepository customerServiceRepository;


    @Autowired
    Logger logger;


    // Setters
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Autowired
    public void setCustomerStatusRepository(CustomerStatusRepository customerStatusRepository) {
        this.customerStatusRepository = customerStatusRepository;
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
    public void setCustomerServiceRepository(CustomerServiceRepository customerServiceRepository) {
        this.customerServiceRepository = customerServiceRepository;
    }


    // Methods
    public Customer findCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository.findCustomerByPhoneNumber(phoneNumber);
    }

    public List<Customer> findCustomersForUser(String login) {
        return customerRepository.findCustomersByUserLogin(login);
    }

    public void saveCustomer(Customer customer) {
        customer.setUser(userRepository.findUserByLogin(customer.getUser().getLogin()));
        customerRepository.save(customer);
    }


    public boolean updateCustomer(String userLogin, String phoneNumber, String customerStatusName, float balance) {
        System.out.println(userLogin + phoneNumber + customerStatusName + balance);
        User user = userRepository.findUserByLogin(userLogin);
        System.out.println("2");
        CustomerStatus customerStatus = customerStatusRepository.findCustomerStatusByName(customerStatusName);
        System.out.println("3");
        Customer customerDB = customerRepository.findCustomerByPhoneNumber(phoneNumber);

        if (customerDB.getUser() == null && user != null) {
            customerDB.setUser(user);
            user.getCustomerList().add(customerDB);

        }else if ( !userLogin.equals(customerDB.getUser().getLogin())) {
//            System.out.println("If UL");
            User oldUser = customerDB.getUser();
            oldUser.getCustomerList().remove(customerDB);
            customerDB.setUser(user);
            user.getCustomerList().add(customerDB);
            userRepository.save(oldUser);
        }

        if (customerDB.getCustomerStatus() == null && customerStatus != null) {
            customerDB.setCustomerStatus(customerStatus);
            customerStatus.getCustomerList().add(customerDB);
        } else if (!customerStatusName.equals(customerDB.getCustomerStatus().getName())) {
//            System.out.println("If CuSt");
            CustomerStatus oldCustomerStatus = customerDB.getCustomerStatus();
            oldCustomerStatus.getCustomerList().remove(customerDB);
            customerDB.setCustomerStatus(customerStatus);
            customerStatus.getCustomerList().add(customerDB);

            if (customerStatus.getId() == 2) {
                customerDB.setActivatedDate(new Date());
            }
            if (customerStatus.getId() == 1) {
                customerDB.setDeactivatedDate(new Date());
            }
            customerStatusRepository.save(oldCustomerStatus);
        }


        customerDB.setBalance(balance);
//        System.out.println("updateCostumer: 1");
//        System.out.println(customerDB);
        customerRepository.save(customerDB);
//        dao.updateEntity(user);
//        System.out.println("updateCostumer: 2");

        return true;
    }


//    public boolean deleteCustomer(User user, String customerPhoneNumber) {
    public boolean deleteCustomer(String customerPhoneNumber) {
//        System.out.println(1);
//        User userTemp = dao.findUserByLogin(user.getLogin());
        System.out.println(2);
        Customer customerTemp = customerRepository.findCustomerByPhoneNumber(customerPhoneNumber);
        System.out.println(3);

//        System.out.println("S" + userTemp.getCustomerList().size());
//        System.out.println(userTemp.getCustomerList().contains(customerTemp));
//        System.out.println(userTemp.getCustomerList());
//        System.out.println(customerTemp);
//        System.out.println("R" + userTemp.getCustomerList().remove(customerTemp));
//        customerTemp.setUser(null);
//        System.out.println("S" + userTemp.getCustomerList().size());
//        System.out.println(4);

//        CustomerStatus customerStatus = customerTemp.getCustomerStatus();
//        customerStatus.getCustomerList().remove(customerTemp);
//        customerTemp.setCustomerStatus(null);

//        System.out.println(5);
//        dao.updateEntity(userTemp);
//        System.out.println("S" + userTemp.getCustomerList().size());
//        System.out.println(6);
//        dao.updateEntity(customerStatus);
//        System.out.println(7);
        customerRepository.delete(customerTemp);
        System.out.println(8);

////        customer.getCustomerServiceList().clear();
////        dao.updateEntity(customer);
//        System.out.println(6);
        return true;
    }


    public void addCustomerService(String selectedPhoneNumber, String selectedServiceName) {
        Customer selectedCustomer = customerRepository.findCustomerByPhoneNumber(selectedPhoneNumber);
        com.infoPulse.lessons.model.entity.Service selectedService = serviceRepository.findServiceByName(selectedServiceName);
        ServiceStatus serviceStatus = serviceStatusRepository.findServiceStatusByName("disabled");
//        dao.addEntity(selectedCustomer.addService(selectedService, serviceStatus));
        selectedCustomer.addService(selectedService, serviceStatus);
        customerRepository.save(selectedCustomer);

//        dao.updateEntity(selectedService);
    }


    public void deleteCustomerService(String phoneNumber, String serviceName) {
        System.out.println("/customerservice/{phonenumber}/{servicename}/delete: " + phoneNumber + "|" + serviceName);
//        CustomerService customerService = dao.getCustomerServicesByPhoneNumberAndServiceName(phoneNumber, serviceName);
        CustomerService customerService = customerServiceRepository.findCustomerServiceByCustomerPhoneNumberAndServiceName(phoneNumber, serviceName);
        customerServiceRepository.delete(customerService);

//        Customer customer = dao.findCustomerByPhoneNumber(phoneNumber);
//        Customer customer = customerRepository.findCustomerByPhoneNumber(phoneNumber);
//        com.infoPulse.lessons.classesForTable.Service service = dao.getServiceForName(serviceName);
//        System.out.println("Before:\n" + customer.getCustomerServiceList().size() + "|" + service.getCustomerServiceList().size());
//        System.out.println("Before:\n" + customer.getCustomerServiceList().size() + "|" + service.getCustomerServiceList().size());
//        customer.removeService(service);
//        dao.updateEntity(customer);
//        dao.updateEntity(service);
//        System.out.println("After:\n" + customer.getCustomerServiceList().size() + "|" + service.getCustomerServiceList().size());
//        System.out.println("After:\n" + customer.getCustomerServiceList().size() + "|" + service.getCustomerServiceList().size());
//
//        System.out.println("Delete CustomerService id: " + customerService.getId());
//        dao.deleteEntity(customerService);
    }


}
