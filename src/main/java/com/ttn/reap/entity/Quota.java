package com.ttn.reap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Quota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer quotaId;
    @ManyToOne
    @JsonIgnore
    private User userId;
    @ManyToOne
    @JsonIgnore
    private Badge badgeId;
    private Integer quantity;

    public Integer getQuotaId() {
        return quotaId;
    }

    public void setQuotaId(Integer quotaId) {
        this.quotaId = quotaId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Badge getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Badge badgeId) {
        this.badgeId = badgeId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Quota{" +
                "quotaId=" + quotaId +
                ", userId=" + userId +
                ", badgeId=" + badgeId +
                ", quantity=" + quantity +
                '}';
    }
}
