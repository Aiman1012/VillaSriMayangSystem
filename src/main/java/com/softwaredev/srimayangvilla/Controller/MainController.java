package com.softwaredev.srimayangvilla.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String showMainPage(){
        return "index";
    }
//    @GetMapping("/room")
//    public String showRoomPage(){
//        return "room";
//    }

    @GetMapping("/staffDashboard")
    public String showDashboardStaff(){
        return "dashboardStaff";
    }
}
