package com.softwaredev.srimayangvilla.Controller;

import com.softwaredev.srimayangvilla.Model.User;
import com.softwaredev.srimayangvilla.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/users/userlist")
    public String showUserList(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "GuestList";

    }

    @GetMapping("/users/newform")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        return "Guest_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user) {
        service.save(user);
        return "redirect:/log";
    }

    @GetMapping("/users/guestlist")
    public String showGuest() {
        return "GuestList";
    }


    @GetMapping("/users/delete/{id}")
    public String deletePackage(@PathVariable("id") String id, RedirectAttributes ra) {
        try {
            UserService.deletePackagebyid(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/GuestList";
    }


    public UserController(UserService userService) {
        this.service = userService;
    }



}











