package com.jepsolucoes.fansmarvel.model;

public class Results {
    public int id;
    public String name;
    private String description;
    public CharThumbnail thumbnail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {

        if(description.isEmpty() || description == null){
            this.description = "No description available for this character";
        }else{
            this.description = description;
        }

    }

    public CharThumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(CharThumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }
}
