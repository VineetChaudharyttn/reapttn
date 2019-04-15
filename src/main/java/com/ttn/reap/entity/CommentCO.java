package com.ttn.reap.entity;

public class CommentCO {
    private String senderId;
    private String receiverId;
    private Integer badgeId;
    private String message;
    private String date;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public Integer getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Integer badgeId) {
        this.badgeId = badgeId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CommentCO{" +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", badgeId=" + badgeId +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
