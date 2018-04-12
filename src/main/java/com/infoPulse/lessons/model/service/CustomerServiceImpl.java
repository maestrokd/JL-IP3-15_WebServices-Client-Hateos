package com.infoPulse.lessons.model.service;

import com.infoPulse.lessons.model.dto.CustomerDTO;
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
public class CustomerServiceImpl implements CustomerService {

    // Fields
    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private CustomerStatusRepository customerStatusRepository;

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


    // Methods
    @Override
    public Customer findCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository.findCustomerByPhoneNumber(phoneNumber);
    }


    @Override
    public List<Customer> findCustomersForUser(String login) {
        return customerRepository.findCustomersByUserLogin(login);
    }


    @Override
    public List<Customer> findCustomersLikePhoneNumber(String phoneNumber) {
        return customerRepository.findCustomersByPhoneNumberContaining(phoneNumber);
    }


    @Override
    public Customer saveCustomer(CustomerDTO customerDTO) {
        User user = userRepository.findUserByLogin(customerDTO.getUserLogin());
        CustomerStatus customerStatus = customerStatusRepository.findCustomerStatusByName(customerDTO.getCustomerStatusName());
        Customer customer = new Customer(customerDTO, user, customerStatus);
        return customerRepository.save(customer);
    }


    @Override
//    public boolean updateCustomer(String userLogin, String phoneNumber, String customerStatusName, float balance) {
    public Customer updateCustomer(CustomerDTO newDtoCustomer) {
        System.out.println(newDtoCustomer.getUserLogin() + newDtoCustomer.getPhoneNumber() + newDtoCustomer.getCustomerStatusName() + newDtoCustomer.getBalance());
        User user = userRepository.findUserByLogin(newDtoCustomer.getUserLogin());
        System.out.println("2");
        CustomerStatus customerStatus = customerStatusRepository.findCustomerStatusByName(newDtoCustomer.getCustomerStatusName());
        System.out.println("3");
        Customer customerDB = customerRepository.findCustomerByPhoneNumber(newDtoCustomer.getPhoneNumber());

        if (customerDB.getUser() == null && user != null) {
            customerDB.setUser(user);
            user.getCustomerList().add(customerDB);

        }else if ( !newDtoCustomer.getUserLogin().equals(customerDB.getUser().getLogin())) {
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
        } else if (!newDtoCustomer.getCustomerStatusName().equals(customerDB.getCustomerStatus().getName())) {
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

        if (!newDtoCustomer.getBillingAddress().equals(customerDB.getBillingAddress())) {
            customerDB.setBillingAddress(newDtoCustomer.getBillingAddress());
        }

        customerDB.setBalance(newDtoCustomer.getBalance());

        return customerRepository.save(customerDB);
    }


    @Override
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

////        customer.getCustomerServiceSet().clear();
////        dao.updateEntity(customer);
//        System.out.println(6);
        return true;
    }




}
