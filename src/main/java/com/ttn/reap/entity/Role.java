package com.ttn.reap.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    private String role;
    @ManyToMany(mappedBy = "role")
    private List<User> userId=new ArrayList<User>();


    public Role() {
    }

    public Role(String role) {
        this.role=role;
    }
//    @ManyToMany
//    private List<BadgeRepo> badges=new ArrayList<BadgeRepo>();

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUserId() {
        return userId;
    }

    public void setUserId(List<User> userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", role='" + role + '\'' +
                '}';
    }
}
