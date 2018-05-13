package com.infoPulse.lessons.model.service;

import com.infoPulse.lessons.model.dto.CustomerDTO;
import com.infoPulse.lessons.model.entity.*;
import com.infoPulse.lessons.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {


    // Fields
    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private CustomerStatusRepository customerStatusRepository;


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
    public Customer updateCustomer(CustomerDTO newDtoCustomer) {
        User user = userRepository.findUserByLogin(newDtoCustomer.getUserLogin());
        CustomerStatus customerStatus = customerStatusRepository.findCustomerStatusByName(newDtoCustomer.getCustomerStatusName());
        Customer customerDB = customerRepository.findCustomerByPhoneNumber(newDtoCustomer.getPhoneNumber());

        if (customerDB.getUser() == null && user != null) {
            customerDB.setUser(user);
            user.getCustomerList().add(customerDB);
        }else if ( user != null && !user.getLogin().equals(customerDB.getUser().getLogin())) {
            User oldUser = customerDB.getUser();
            oldUser.getCustomerList().remove(customerDB);
            customerDB.setUser(user);
            user.getCustomerList().add(customerDB);
            userRepository.save(oldUser);
        }

        if (customerDB.getCustomerStatus() == null && customerStatus != null) {
            customerDB.setCustomerStatus(customerStatus);
            customerStatus.getCustomerList().add(customerDB);
        } else if (customerStatus != null && !customerStatus.getName().equals(customerDB.getCustomerStatus().getName())) {
            CustomerStatus oldCustomerStatus = customerDB.getCustomerStatus();
            oldCustomerStatus.getCustomerList().remove(customerDB);
            customerDB.setCustomerStatus(customerStatus);
            customerStatus.getCustomerList().add(customerDB);

            if (CustomerStatus.CustomerStatusNames.ACTIVE.getCustomerStatusNames().equals(customerStatus.getName())) {
                customerDB.setActivatedDate(new Date());
            }
            if (CustomerStatus.CustomerStatusNames.DEACTIVE.getCustomerStatusNames().equals(customerStatus.getName())) {
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
    public boolean deleteCustomer(String customerPhoneNumber) {
        Customer customerTemp = customerRepository.findCustomerByPhoneNumber(customerPhoneNumber);
        customerRepository.delete(customerTemp);
        return !customerRepository.exists(customerTemp.getId());
    }

}
