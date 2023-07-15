package com.softwaredev.srimayangvilla.Controller;

import com.softwaredev.srimayangvilla.Model.Package;
import com.softwaredev.srimayangvilla.Model.Room;
import com.softwaredev.srimayangvilla.Service.PackageService;
import com.softwaredev.srimayangvilla.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GuestController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private PackageService packageService;

    @GetMapping("/guestDashboard")
    public String showGuestDashboard(){
        return "dashboardGuest";
    }

    @GetMapping("/guestDashboard/guestRoom")
    public String showGuestRoom(Model model){
        List<Room> listRooms = roomService.listAllRoom();
        model.addAttribute("ListRooms", listRooms);

        return "roomGuest";
    }

    @GetMapping("/guestDashboard/guestPackage")
    public String showGuestPackage(Model model){
        List<Package> listPackages = packageService.listAllPackage();
        model.addAttribute("ListPackages", listPackages);

        return "guestPackage";
    }
}


