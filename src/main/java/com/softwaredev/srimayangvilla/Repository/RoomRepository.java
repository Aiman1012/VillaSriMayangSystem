package com.softwaredev.srimayangvilla.Repository;

import com.softwaredev.srimayangvilla.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Long countByRoomId(Long roomId);
}

