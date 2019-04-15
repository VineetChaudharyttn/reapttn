package com.ttn.reap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String username;
    private String password;
    private boolean active;
    private String imagePath;
    private String resetToken;
    @Column(columnDefinition = "int default 0")
    private Integer goldBadge;
    @Column(columnDefinition = "int default 0")
    private Integer silverBadge;
    @Column(columnDefinition = "int default 0")
    private Integer bronzeBadge;
    @Column(columnDefinition = "int default 0")
    private Integer point;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    @JsonIgnore
    private List<Role> role = new ArrayList<Role>();

    @OneToMany(mappedBy = "userId")
    @JsonIgnore
    private List<Quota> quota=new ArrayList<Quota>();

    @Transient
    private MultipartFile image;

    public User() {

    }

    public User(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.role = user.getRole();
        this.imagePath = user.getImagePath();
        this.image = user.getImage();
        this.quota=user.getQuota();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public List<Quota> getQuota() {
        return quota;
    }

    public void setQuota(List<Quota> quota) {
        this.quota = quota;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", imagePath='" + imagePath + '\'' +
                ", resetToken='" + resetToken + '\'' +
                ", goldBadge=" + goldBadge +
                ", silverBadge=" + silverBadge +
                ", bronzeBadge=" + bronzeBadge +
                ", point=" + point +
                ", role=" + role +
                ", quota=" + quota +
                ", image=" + image +
                '}';
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

    public Integer getBronzeBadge() {
        return bronzeBadge;
    }

    public void setBronzeBadge(Integer bronzeBadge) {
        this.bronzeBadge = bronzeBadge;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

}
