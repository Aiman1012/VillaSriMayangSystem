package com.softwaredev.srimayangvilla.Model;

import javax.persistence.*;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomId", nullable = false, unique = true)
    private Long roomId;

    @Column(name = "roomName", nullable = false)
    private String roomName;


    private String roomDesc;

    @Column(name = "roomPrice", nullable = false)
    private double roomPrice;

    @Column(name = "roomCapacity", nullable = false)
    private  String roomCapacity;

    @Column(name = "roomAvailability", nullable = false)
    private boolean roomAvail;

    @Column(name = "roomImg", nullable = false)
    private String roomImage;



    public Room() {
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(String roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public boolean isRoomAvail() {
        return roomAvail;
    }

    public void setRoomAvail(boolean roomAvail) {
        this.roomAvail = roomAvail;
    }

    public String getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                ", roomDesc='" + roomDesc + '\'' +
                ", roomPrice=" + roomPrice +
                ", roomCapacity='" + roomCapacity + '\'' +
                ", roomAvailability=" + roomAvail +
                ", roomImage='" + roomImage + '\'' +
                '}';
    }
}
