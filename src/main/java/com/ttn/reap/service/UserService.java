package com.ttn.reap.service;

import com.ttn.reap.entity.Role;
import com.ttn.reap.entity.User;
import com.ttn.reap.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleService roleService;

    @Transactional
    public void register(User user){
        userRepo.save(user);
    }

    public Optional<User> findByName(String username) {
        return userRepo.findByUsername(username);
    }

    public Optional<User> findUserByResetToken(String resetToken) {
        return userRepo.findUserByResetToken(resetToken);
    }



    public Boolean chackAvail(String username){
        Optional<User> optionalUser=userRepo.findByUsername(username);
        return optionalUser.isPresent();
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void deactivateUser(int userId){
        Optional<User> user = userRepo.findByUserId(userId);
        user.get().setActive(false);
        userRepo.save(user.get());
    }

    public void activateUser(int userId){
        Optional<User> user = userRepo.findByUserId(userId);
        user.get().setActive(true);
        userRepo.save(user.get());
    }

    public String changeUserRole(int userId, String selectedRole) {
        Optional<User> userOptional = userRepo.findByUserId(userId);
        User user=userOptional.orElse(null);
        List<Role> list=user.getRole();
        String message=null;
        if (!list.contains(roleService.findByRole(selectedRole))) {
            list.add(roleService.findByRole(selectedRole));
            message=selectedRole+" role is added";
            System.out.println(message);
        }
        else if (list.contains(roleService.findByRole(selectedRole)))  {
            System.out.println("remove");
            list.remove(roleService.findByRole(selectedRole));
            message=selectedRole+" role is removed.";
        }
        user.setRole(list);
        userRepo.save(user);
        return message;
    }
}
