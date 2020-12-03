package com.example.pas_raul_11rpl2;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ModelFootballRealm extends RealmObject {
    @PrimaryKey
    private int id;
    private String team;
    private String imageUrl;
    private String description;
    private String formedYear;
    private String stadionName;
    private String stadionDesc;
    private String stadionLocation;
    private String stadionImage;

    private Boolean isFavourite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeam() { return team; }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormedYear() {
        return formedYear;
    }

    public void setFormedYear(String formedYear) {
        this.formedYear = formedYear;
    }

    public String getStadionName() {
        return stadionName;
    }

    public void setStadionName(String stadionName) {
        this.stadionName = stadionName;
    }

    public String getStadionDesc() {
        return stadionDesc;
    }

    public void setStadionDesc(String stadionDesc) {
        this.stadionDesc = stadionDesc;
    }

    public String getStadionLocation() {
        return stadionLocation;
    }

    public void setStadionLocation(String stadionLocation) {
        this.stadionLocation = stadionLocation;
    }

    public String getStadionImage() {
        return stadionImage;
    }

    public void setStadionImage(String stadionImage) {
        this.stadionImage = stadionImage;
    }

    public Boolean getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(Boolean isFavourite  ) {
        this.isFavourite = isFavourite;
    }

}
