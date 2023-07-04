package com.softwaredev.srimayangvilla.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class MainController {

    @GetMapping("")
    public String showMainPage(){
        return "index";
    }
    @GetMapping("/room")
    public String showRoomPage(){
        return "room";
    }
}
