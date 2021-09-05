package com.example.my_timetable.Model;

import java.io.Serializable;
import java.util.List;

public class Module implements Serializable{
    private String moduleID;
    private String moduleName;
    private DtoUser user;
    private List<Batch> batches;


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

    public DtoUser getUser() {
        return user;
    }

    public void setUser(DtoUser user) {
        this.user = user;
    }

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }
}
