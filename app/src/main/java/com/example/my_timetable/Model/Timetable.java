package com.example.my_timetable.Model;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class Timetable {
    private int timetableId;
    private LocalTime startTime;
    private LocalTime endTime;
    private Date scheduledDate;
    private Module module;
    private Classroom classRoom;
    private List<Batch> batches;

    public Timetable(int timetableId, LocalTime startTime, LocalTime endTime, Date scheduledDate, Module module, Classroom classRoom, List<Batch> batches) {
        this.timetableId = timetableId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.scheduledDate = scheduledDate;
        this.module = module;
        this.classRoom = classRoom;
        this.batches = batches;
    }

    public Timetable() {
    }

    public int getTimetableId() {
        return timetableId;
    }

    public void setTimetableId(int timetableId) {
        this.timetableId = timetableId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Classroom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(Classroom classRoom) {
        this.classRoom = classRoom;
    }

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }
}
