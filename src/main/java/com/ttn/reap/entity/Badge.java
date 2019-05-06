package com.ttn.reap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer badgeId;
    private String badge;
    private Integer weightage;
    private String imagePath;
    @OneToMany(mappedBy = "badgeId")
    @JsonIgnore
    private List<Quota> quotas=new ArrayList<Quota>();

    public Badge() {
    }

    public Badge(String badge, Integer weightage, String imagePath) {
        this.badge = badge;
        this.weightage = weightage;
        this.imagePath = imagePath;
    }

    public Integer getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Integer badgeId) {
        this.badgeId = badgeId;
    }

    public List<Quota> getQuotas() {
        return quotas;
    }

    public void setQuotas(List<Quota> quotas) {
        this.quotas = quotas;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public Integer getWeightage() {
        return weightage;
    }

    public void setWeightage(Integer weightage) {
        this.weightage = weightage;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Badge{" +
                "badgeId=" + badgeId +
                ", badge='" + badge + '\'' +
                ", weightage=" + weightage +
                '}';
    }
}
