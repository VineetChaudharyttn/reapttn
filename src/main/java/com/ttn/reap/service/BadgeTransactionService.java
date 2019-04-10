package com.ttn.reap.service;

import com.ttn.reap.entity.BadgeTransaction;
import com.ttn.reap.entity.CommentCO;
import com.ttn.reap.repository.BadgeRepo;
import com.ttn.reap.repository.BadgeTransactionRepo;
import com.ttn.reap.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BadgeTransactionService {

    @Autowired
    BadgeTransactionRepo badgeTransactionRepo;

    @Autowired
    BadgeRepo badgeRepo;

    @Autowired
    UserRepo userRepo;

    public void saveComment(CommentCO commentCO){
        BadgeTransaction badgeTransaction=new BadgeTransaction();
        badgeTransaction.setBadge(badgeRepo.findById(commentCO.getBadgeId()).orElse(null));
        badgeTransaction.setSender(userRepo.findByUsername(commentCO.getSenderId()).orElse(null));
        badgeTransaction.setDate(commentCO.getDate());
        badgeTransaction.setReceiver(userRepo.findByUsername(commentCO.getReceiverId()).orElse(null));
        badgeTransaction.setMessage(commentCO.getMessage());
        badgeTransactionRepo.save(badgeTransaction);
    }



    public Page<BadgeTransaction> findTransaction(int i) {
        return badgeTransactionRepo.findAll(new PageRequest(i,3,new Sort(Sort.Direction.ASC,"date")));
    }
}
