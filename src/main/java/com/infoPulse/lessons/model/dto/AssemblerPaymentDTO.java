package com.infoPulse.lessons.model.dto;

import com.infoPulse.lessons.model.entity.Payment;

import java.util.ArrayList;
import java.util.List;


public class AssemblerPaymentDTO {

    private static volatile AssemblerPaymentDTO instance;

    public static AssemblerPaymentDTO getInstance() {
        if (instance == null) {
            synchronized (AssemblerPaymentDTO.class) {
                if (instance == null) {
                    instance = new AssemblerPaymentDTO();
                }
            }
        }
        return instance;
    }

    public List<PaymentDTO> getPaymentDToList(List<Payment> paymentList) {
        List<PaymentDTO> paymentDTOList = new ArrayList<>();
        for (Payment payment : paymentList) {
            paymentDTOList.add(new PaymentDTO(payment));
        }
        return  paymentDTOList;
    }

    public PaymentDTO getPaymentDTO (Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO(payment);
        return  paymentDTO;
    }

}
