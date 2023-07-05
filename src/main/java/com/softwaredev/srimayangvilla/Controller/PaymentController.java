package com.softwaredev.srimayangvilla.Controller;

import com.softwaredev.srimayangvilla.Exception.UserNotFoundException;
import com.softwaredev.srimayangvilla.Model.Payment;
import com.softwaredev.srimayangvilla.Model.Room;
import com.softwaredev.srimayangvilla.Repository.PaymentRepository;
import com.softwaredev.srimayangvilla.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepo;

    @GetMapping("/paymentIndex")
    public String showPayment(Model model){
        List<Payment> listPayments = paymentService.listAllPayments();
        model.addAttribute("ListPayments", listPayments);

        return "paymentIndex";
    }

    @GetMapping("/paymentIndex/addPayment")
    public String addNewPayment(Model model){
        Payment payment = new Payment();
        model.addAttribute("payment", payment);

        return "addPayment";
    }

    @PostMapping("/paymentIndex/paymentUpload")
    public String paymentUpload(@RequestParam("paymentAmount") double paymentAmount, @RequestParam("paymentDate") String paymentDate, @RequestParam("paymentStatus") boolean paymentStatus, HttpSession session){
        Payment payment = new Payment();

        payment.setPaymentAmount(paymentAmount);
        payment.setPaymentDate(paymentDate);
        payment.setPaymentStatus(paymentStatus);

        Payment paymentSave = paymentRepo.save(payment);

        session.setAttribute("msg", "Payment Successful");

        return "redirect:/paymentIndex";
    }

    @GetMapping("/paymentIndex/delete/{paymentId}")
    public String deletePayment(@PathVariable("paymentId") Long paymentId, RedirectAttributes ra){
        try {
            paymentService.deletePaymentbyId(paymentId);
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "The payment has been deleted");
        }
        return "redirect:/paymentIndex";
    }


}
