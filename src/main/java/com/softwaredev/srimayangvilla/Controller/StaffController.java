package com.softwaredev.srimayangvilla.Controller;


import com.softwaredev.srimayangvilla.Model.Staff;
import com.softwaredev.srimayangvilla.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StaffController {


    @Autowired
    private StaffService service;

    @GetMapping("/dashboardStaff")
    public String showStaffDashboard(){
        return "dashboardStaff";
    }

    @GetMapping("/staff")
    public String showStaffList(Model model) {
        List<Staff> listStaff = service.listAll();
        model.addAttribute("listStaff", listStaff);
        return "StaffList";

    }

    @GetMapping("/staff/new")
    public String showNewForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "Staff_Form";
    }

    @PostMapping("/staff/save")
    public String saveStaff(Staff staff) {
        service.save(staff);
        return "redirect:/";
    }

    @GetMapping("/staff/staffList")
    public String showStaff() {
        return "StaffList";
    }
}




