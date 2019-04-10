package com.ttn.reap.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class ReceivedBadge implements Serializable {
    @Id
    @OneToOne
    private User userId;
    private Integer goldBadge;
    private Integer silverBadge;
    private Integer bronze;
    private Integer point;

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Integer getGoldBadge() {
        return goldBadge;
    }

    public void setGoldBadge(Integer goldBadge) {
        this.goldBadge = goldBadge;
    }

    public Integer getSilverBadge() {
        return silverBadge;
    }

    public void setSilverBadge(Integer silverBadge) {
        this.silverBadge = silverBadge;
    }

    public Integer getBronze() {
        return bronze;
    }

    public void setBronze(Integer bronze) {
        this.bronze = bronze;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
