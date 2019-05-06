package com.ttn.reap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer purchaseId;
    @ManyToOne
    private User user;

    @ManyToOne
    private Item itemId;

    private Date purchaseTimestamp;

    private Integer quantity;

    public Purchase() {
    }

    public Purchase(User user, Item itemId, Date purchaseTimestamp,Integer quantity) {
        this.user = user;
        this.itemId = itemId;
        this.purchaseTimestamp = purchaseTimestamp;
        this.quantity=quantity;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    public Date getPurchaseTimestamp() {
        return purchaseTimestamp;
    }

    public void setPurchaseTimestamp(Date purchaseTimestamp) {
        this.purchaseTimestamp = purchaseTimestamp;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
