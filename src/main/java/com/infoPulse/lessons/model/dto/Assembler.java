package com.infoPulse.lessons.model.dto;



import com.infoPulse.lessons.model.entity.Payment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 */
public class Assembler {

    private static volatile Assembler instance;

    public static Assembler getInstance() {
        if (instance == null) {
            synchronized (Assembler.class) {
                if (instance == null) {
                    instance = new Assembler();
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
