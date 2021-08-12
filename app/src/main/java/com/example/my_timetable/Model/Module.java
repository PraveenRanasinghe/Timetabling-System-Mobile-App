package com.example.my_timetable.Model;

import java.util.List;

public class Module {
    private String moduleID;
    private String moduleName;
    private User user;
    private List<Timetable> lectureList;
    private List<Batch> batches;

    public Module(String moduleID, String moduleName, User user, List<Timetable> lectureList, List<Batch> batches) {
        this.moduleID = moduleID;
        this.moduleName = moduleName;
        this.user = user;
        this.lectureList = lectureList;
        this.batches = batches;
    }

    public Module() {
    }

    public String getModuleID() {
        return moduleID;
    }

    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Timetable> getLectureList() {
        return lectureList;
    }

    public void setLectureList(List<Timetable> lectureList) {
        this.lectureList = lectureList;
    }

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }
}
