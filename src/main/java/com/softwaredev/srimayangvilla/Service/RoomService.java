package com.softwaredev.srimayangvilla.Service;

import com.softwaredev.srimayangvilla.Exception.UserNotFoundException;
import com.softwaredev.srimayangvilla.Model.Room;
import com.softwaredev.srimayangvilla.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepo;

    public List<Room> listAllRoom(){
        return roomRepo.findAll();
    }

    public void saveRoom(Room room){
        roomRepo.save(room);
    }

    public Room getRoombyId(Long roomId) throws UserNotFoundException {
        Optional<Room> resultRoom = roomRepo.findById(roomId);
        if (resultRoom.isPresent()){
            return resultRoom.get();
        }
        throw new UserNotFoundException("Could not found any room with ID " + roomId);
    }

    public void deleteRoombyId(Long roomId) throws UserNotFoundException {
        Long roomCount = roomRepo.countByRoomId(roomId);
        if(roomCount== null || roomCount == 0){
            throw new UserNotFoundException("Could not found any room with ID " + roomId);
        }
        roomRepo.deleteById(roomId);
    }
}
