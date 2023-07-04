package com.softwaredev.srimayangvilla.Controller;

        import com.softwaredev.srimayangvilla.Exception.UserNotFoundException;
        import com.softwaredev.srimayangvilla.Model.Room;
        import com.softwaredev.srimayangvilla.Repository.RoomRepository;
        import com.softwaredev.srimayangvilla.Service.RoomService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.core.io.ClassPathResource;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.multipart.MultipartFile;
        import org.springframework.web.servlet.mvc.support.RedirectAttributes;

        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpSession;
        import java.io.File;
        import java.io.IOException;
        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.nio.file.Paths;
        import java.nio.file.StandardCopyOption;
        import java.util.List;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepo;


    @GetMapping("/roomIndex")
    public String showRoom(Model model){
        List<Room> listRooms = roomService.listAllRoom();
        model.addAttribute("ListRooms", listRooms);

        return "roomIndex";
    }

    @GetMapping("/roomIndex/addRoom")
    public String addNewRoom(Model model){
        Room room = new Room();
        model.addAttribute("room", room);

        return "addRoom";
    }

    @PostMapping("/roomIndex/roomUpload")
    public String roomUpload(@RequestParam("roomImg")MultipartFile roomImg, @RequestParam("roomName") String roomName, @RequestParam("roomPrice") double roomPrice,
                             @RequestParam("roomDesc") String roomDesc, @RequestParam("roomCapacity") int roomCapacity, @RequestParam("roomAvail") boolean roomAvail,
                             HttpSession session){
        Room room = new Room();

        room.setRoomImage(roomImg.getOriginalFilename());
        room.setRoomName(roomName);
        room.setRoomPrice(roomPrice);
        room.setRoomDesc(roomDesc);
        room.setRoomCapacity(String.valueOf(roomCapacity));
        room.setRoomAvail(roomAvail);

        Room roomSave = roomRepo.save(room);
        if(roomSave!=null){
            try {
                File saveFile = new ClassPathResource("static/roomImg").getFile();

                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + roomImg.getOriginalFilename());
                System.out.println(path);

                Files.copy(roomImg.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        session.setAttribute("msg", "Room uploaded Succesfully");

        return "redirect:/roomIndex";
    }

//    @PostMapping("/roomIndex/save")
//    public String saveRoom(@RequestParam("roomImg")MultipartFile roomImg, @RequestParam("roomName") String roomName, @RequestParam("roomPrice") double roomPrice,
//                           @RequestParam("roomDesc") String roomDesc, @RequestParam("roomCapacity") int roomCapacity, @RequestParam("roomAvail") boolean roomAvail){
//        roomService.saveRoom(roomImg, roomName,roomPrice,roomDesc,roomCapacity, roomAvail);
//    }

//    @GetMapping("/roomIndex/edit/{roomId}")
//    public String editRoom(@PathVariable("roomId") Long roomId, Model model, RedirectAttributes ra){
//        try {
//            Room room = roomService.getRoombyId(roomId);
//            model.addAttribute("room", room);
//            return "editRoom";
//        } catch (UserNotFoundException e) {
//            ra.addFlashAttribute("message", e.getMessage());
//        }
//        return "redirect:/roomIndex";
//    }

    @GetMapping("/roomIndex/delete/{roomId}")
    public String deleteRoom(@PathVariable("roomId") Long roomId, RedirectAttributes ra){
        try {
            roomService.deleteRoombyId(roomId);
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "The room has been deleted");
        }
        return "redirect:/roomIndex";
    }

    @GetMapping("/roomIndex/edit/{roomId}")
    public String editRoom(@PathVariable("roomId") Long roomId, Model model, RedirectAttributes ra){
        try {
            Room room = roomService.getRoombyId(roomId);
            model.addAttribute("room", room);
            return "editRoom";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/roomIndex";
    }

    @PostMapping("/roomIndex/update")
    public String updateRoom(@RequestParam("roomId") Long roomId, @RequestParam("roomImg") MultipartFile roomImg, @RequestParam("roomName") String roomName,
                             @RequestParam("roomPrice") double roomPrice, @RequestParam("roomDesc") String roomDesc, @RequestParam("roomCapacity") int roomCapacity,
                             @RequestParam("roomAvail") boolean roomAvail, HttpSession session) {
        try {
            Room room = roomService.getRoombyId(roomId);
            room.setRoomName(roomName);
            room.setRoomPrice(roomPrice);
            room.setRoomDesc(roomDesc);
            room.setRoomCapacity(String.valueOf(roomCapacity));
            room.setRoomAvail(roomAvail);

            if (!roomImg.isEmpty()) {
                room.setRoomImage(roomImg.getOriginalFilename());
                File saveFile = new ClassPathResource("static/roomImg").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + roomImg.getOriginalFilename());
                Files.copy(roomImg.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }

            roomService.saveRoom(room);
            session.setAttribute("msg", "Room updated successfully");
        } catch (UserNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/roomIndex";
    }
}
