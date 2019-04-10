package com.ttn.reap.controller;

import com.ttn.reap.entity.BadgeTransaction;
import com.ttn.reap.entity.CommentCO;
import com.ttn.reap.service.BadgeTransactionService;
import com.ttn.reap.service.TransactionQuota;
import com.ttn.reap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {
    @Autowired
    UserService userService;

    @Autowired
    TransactionQuota transactionQuota;

    @Autowired
    BadgeTransactionService badgeTransactionService;
    @PostMapping("/checkAvailability")
    public String checkAvail(@RequestParam("username") String username){
        if (!userService.chackAvail(username)){
            return "";
        }
        return "<span class='badge badge-danger'>User is already exists "+username+"</span>";
    }

    @PostMapping("/writeComment")
    public String write(CommentCO commentCO){

        transactionQuota.subtractQuota(commentCO);
        badgeTransactionService.saveComment(commentCO);
        return "hit";
    }


    @PostMapping("/deactivateUser")
    @ResponseBody
    public void userDeactivate(@RequestParam("userId") int userId){
        userService.deactivateUser(userId);
    }

    @PostMapping("/activateUser")
    @ResponseBody
    public void userActivate(@RequestParam("userId") int userId){
        userService.activateUser(userId);
    }

    @PostMapping("/changeUserRole")
    @ResponseBody
    public void changeUserRole(@RequestParam("userId") int userId,@RequestParam("selectedRole") String selectedRole){
        userService.changeUserRole(userId,selectedRole);
    }

}
