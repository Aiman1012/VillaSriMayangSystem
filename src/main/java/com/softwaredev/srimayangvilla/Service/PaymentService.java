package com.softwaredev.srimayangvilla.Service;

import com.softwaredev.srimayangvilla.Exception.UserNotFoundException;
import com.softwaredev.srimayangvilla.Model.Payment;
import com.softwaredev.srimayangvilla.Model.Room;
import com.softwaredev.srimayangvilla.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepo;

    public List<Payment> listAllPayments(){
        return paymentRepo.findAll();
    }

    public void savePayment(Payment payment){
        paymentRepo.save(payment);
    }

    public Payment getPaymentById(Long paymentId) throws UserNotFoundException {
        Optional<Payment> resultPayment = paymentRepo.findById(paymentId);
        if (resultPayment.isPresent()){
            return resultPayment.get();
        }
        throw new UserNotFoundException("Could not found any payment with ID " + paymentId);
    }

    public void deletePaymentbyId(Long paymentId) throws UserNotFoundException {
        Long paymentCount = paymentRepo.countByPaymentId(paymentId);
        if(paymentCount== null || paymentCount == 0){
            throw new UserNotFoundException("Could not found any payment with ID " + paymentId);
        }
        paymentRepo.deleteById(paymentId);
    }
}
