package com.ttn.reap.service;

import com.ttn.reap.entity.Badge;
import com.ttn.reap.entity.User;
import com.ttn.reap.repository.BadgeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadgeService {

    @Autowired
    BadgeRepo badgeRepo;
    public Iterable<Badge> findBadges(){
        return badgeRepo.findAll();
    }


    public Badge findById(Integer id) {
        return badgeRepo.findById(id).get();
    }
}
