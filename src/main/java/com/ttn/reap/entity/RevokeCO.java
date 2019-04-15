package com.ttn.reap.entity;

public class RevokeCO {
    private Integer transactionId;
    private String reason;
    private String other;

    public RevokeCO() {
    }

    public RevokeCO(Integer transactionId, String reason, String other) {
        this.transactionId = transactionId;
        this.reason = reason;
        this.other = other;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "RevokeCO{" +
                "transactionId=" + transactionId +
                ", reason='" + reason + '\'' +
                ", other='" + other + '\'' +
                '}';
    }
}
