package com.infoPulse.lessons.model.dto;

import com.infoPulse.lessons.model.entity.Customer;
import com.infoPulse.lessons.model.entity.Payment;

import java.util.ArrayList;
import java.util.List;


public class AssemblerCustomerDTO {

    private static volatile AssemblerCustomerDTO instance;

    public static AssemblerCustomerDTO getInstance() {
        if (instance == null) {
            synchronized (AssemblerCustomerDTO.class) {
                if (instance == null) {
                    instance = new AssemblerCustomerDTO();
                }
            }
        }
        return instance;
    }

    public List<CustomerDTO> getCustomerDToList(List<Customer> customerList) {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : customerList) {
            customerDTOList.add(new CustomerDTO(customer));
        }
        return  customerDTOList;
    }

    public CustomerDTO getCustomerDTO (Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO(customer);
        return  customerDTO;
    }

}
