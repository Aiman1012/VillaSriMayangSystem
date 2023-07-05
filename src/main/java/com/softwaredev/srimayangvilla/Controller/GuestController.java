package com.softwaredev.srimayangvilla.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuestController {

    @GetMapping("/guestDashboard")
    public String showGuestDashboard(){
        return "dashboardGuest";
    }

    @GetMapping("/guestDashboard/guestRoom")
    public String showGuestRoom(){
        return "guestRoom";
    }

    @GetMapping("/guestDashboard/guestPackage")
    public String showGuestPackage(){
        return "guestPackage";
    }
}
