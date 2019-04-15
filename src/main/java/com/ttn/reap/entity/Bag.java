package com.ttn.reap.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemBagId;
    private Integer itemId;
    private String name;
    private Integer pointsWorth;
    private Integer quantity;
    private Integer total;
    private Integer userId;

    public Integer getItemBagId() {
        return itemBagId;
    }

    public void setItemBagId(Integer itemBagId) {
        this.itemBagId = itemBagId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPointsWorth() {
        return pointsWorth;
    }

    public void setPointsWorth(Integer pointsWorth) {
        this.pointsWorth = pointsWorth;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
