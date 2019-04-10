package com.ttn.reap.entity;


import javax.persistence.*;

@Entity
public class BadgeTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    User sender;
    @ManyToOne
    @JoinColumn(name = "reciever_id")
    User receiver;
    String date;
    String message;
    @ManyToOne
    Badge badge;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    @Override
    public String toString() {
        return "BadgeTransaction{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", date=" + date +
                ", message='" + message + '\'' +
                ", badge=" + badge +
                '}';
    }

    public BadgeTransaction(User sender, User receiver, String date, String message, Badge badge) {
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
        this.message = message;
        this.badge = badge;
    }

    public BadgeTransaction() {
    }
}
