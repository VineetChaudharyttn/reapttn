package com.ttn.reap.service;

import com.ttn.reap.entity.Role;
import com.ttn.reap.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepo roleRepo;

    public Role findByRole(String role) {
        return roleRepo.findByRole(role);
    }

    public List<Role> findByUserId(Integer userId){
        return roleRepo.findByUserId(userId);
    }

    public Iterable<Role> findAll(){
        return roleRepo.findAll();
    }

}
