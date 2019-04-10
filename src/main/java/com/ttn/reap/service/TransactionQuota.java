package com.ttn.reap.service;

import com.ttn.reap.entity.Badge;
import com.ttn.reap.entity.CommentCO;
import com.ttn.reap.entity.Quota;
import com.ttn.reap.entity.User;
import com.ttn.reap.repository.BadgeRepo;
import com.ttn.reap.repository.QuotaRepo;
import com.ttn.reap.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransactionQuota {

    @Autowired
    UserRepo userRepo;

    @Autowired
    QuotaRepo quotaRepo;

    @Autowired
    BadgeRepo badgeRepo;
    public void subtractQuota(CommentCO commentCO) {
        User user=userRepo.findByUsername(commentCO.getSenderId()).orElse(null);
        Badge badge=badgeRepo.findById(commentCO.getBadgeId()).orElse(null);
        Quota quota=quotaRepo
                .findByUserIdAndBadgeIdOrderByBadgeId(user,badge)
                .orElse(null);
        if (quota == null) {
            throw new RuntimeException();
        }
        quota.setQuantity(quota.getQuantity()-1);
        quotaRepo.save(quota);
        System.out.println(quota);
    }
}
