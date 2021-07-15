package com.example.finalassignment;

public class Model {

    String lid, image, title, description, addTimeStamp, updateTimeStamp;

    public Model(String lid, String image, String title, String description, String addTimeStamp, String updateTimeStamp) {
        this.lid = lid;
        this.image = image;
        this.title = title;
        this.description = description;
        this.addTimeStamp = addTimeStamp;
        this.updateTimeStamp = updateTimeStamp;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddTimeStamp() {
        return addTimeStamp;
    }

    public void setAddTimeStamp(String addTimeStamp) {
        this.addTimeStamp = addTimeStamp;
    }

    public String getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public void setUpdateTimeStamp(String updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }
}
