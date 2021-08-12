package com.example.my_timetable.Model;

import java.util.Date;
import java.util.List;

public class Batch {
    private String batchID;
    private String batchName;
    private Date startDate;
    private Date endDate;
    private List<Module> modules;
    private List<Timetable> timetables;

    public Batch(String batchID, String batchName, Date startDate, Date endDate, List<Module> modules, List<Timetable> timetables) {
        this.batchID = batchID;
        this.batchName = batchName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.modules = modules;
        this.timetables = timetables;
    }

    public Batch() {
    }

    public String getBatchID() {
        return batchID;
    }

    public void setBatchID(String batchID) {
        this.batchID = batchID;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<Timetable> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<Timetable> timetables) {
        this.timetables = timetables;
    }
}
