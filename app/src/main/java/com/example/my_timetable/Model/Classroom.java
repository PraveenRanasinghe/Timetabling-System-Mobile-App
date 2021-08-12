package com.example.my_timetable.Model;

public class Classroom {
    private String classRoomID;
    private String capacity;
    private String smartBoard;
    private String Ac;

    public Classroom(String classRoomID, String capacity, String smartBoard, String ac) {
        this.classRoomID = classRoomID;
        this.capacity = capacity;
        this.smartBoard = smartBoard;
        Ac = ac;
    }

    public Classroom() {
    }

    public String getClassRoomID() {
        return classRoomID;
    }

    public void setClassRoomID(String classRoomID) {
        this.classRoomID = classRoomID;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getSmartBoard() {
        return smartBoard;
    }

    public void setSmartBoard(String smartBoard) {
        this.smartBoard = smartBoard;
    }

    public String getAc() {
        return Ac;
    }

    public void setAc(String ac) {
        Ac = ac;
    }
}
