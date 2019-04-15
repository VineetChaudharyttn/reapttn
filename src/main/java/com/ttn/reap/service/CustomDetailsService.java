package com.ttn.reap.service;

import com.ttn.reap.entity.CustomUserDetails;
import com.ttn.reap.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userService.findByName(username);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("the user not found in database"));
        return new CustomUserDetails(user);
    }
}
