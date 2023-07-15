package com.softwaredev.srimayangvilla.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {
    @GetMapping("guest/reservation")
    public String showReservation(){
        return "reservation";
    }
}
