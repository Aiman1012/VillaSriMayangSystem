package com.softwaredev.srimayangvilla.Controller;


import com.softwaredev.srimayangvilla.Model.feedback;
import com.softwaredev.srimayangvilla.Service.feedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class feedbackController {

    @Autowired
    private feedbackService service;

    @GetMapping("/feedbackList")
    public String showfeedbackList(Model model) {
        List<feedback> listfeedback = service.listAll();
        model.addAttribute("listfeedback", listfeedback);
        return "feedbackList";

    }

    @GetMapping("/feedback/new")
    public String showNewForm(Model model) {
        model.addAttribute("feedback", new feedback());
        return "feedback_form";
    }

    @PostMapping("/feedback/save")
    public String savefeedback(feedback feedback) {
        service.save(feedback);
        return "redirect:/feedbackList";
    }

    @GetMapping("/feedback/feedbackList")
    public String showfeedback() {

        return "feedbackList";
    }
}


