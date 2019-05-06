package com.ttn.reap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Column(nullable = false, columnDefinition = "Integer default 0")
    private Integer goldBadge = 0;
    @Column(nullable = false, columnDefinition = "Integer default 0")
    private Integer silverBadge = 0;
    @Column(nullable = false, columnDefinition = "Integer default 0")
    private Integer bronzeBadge = 0;
    @Column(nullable = false, columnDefinition = "Integer default 0")
    private Integer point = 0;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    @JsonIgnore
    private List<Role> role;

    @OneToMany(mappedBy = "userId")
    private List<Quota> quota = new ArrayList<Quota>();

    @Transient
    private MultipartFile image;

    /*@OneToMany(mappedBy = "user")
    private Purchase purchase;*/

    public User() {

    }

    public User(String firstName, String lastName, String username, String password, boolean active, String imagePath, String resetToken, Integer goldBadge, Integer silverBadge, Integer bronzeBadge, Integer point, List<Role> role, MultipartFile image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.active = active;
        this.imagePath = imagePath;
        this.resetToken = resetToken;
        this.goldBadge = goldBadge;
        this.silverBadge = silverBadge;
        this.bronzeBadge = bronzeBadge;
        this.point = point;
        this.role = role;
        this.quota = quota;
        this.image = image;
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
        this.quota = user.getQuota();
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
}
