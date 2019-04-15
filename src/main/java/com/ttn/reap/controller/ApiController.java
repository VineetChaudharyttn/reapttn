package com.ttn.reap.controller;

import com.ttn.reap.entity.BadgeTransaction;
import com.ttn.reap.entity.CommentCO;
import com.ttn.reap.entity.RevokeCO;
import com.ttn.reap.entity.User;
import com.ttn.reap.repository.BadgeTransactionRepo;
import com.ttn.reap.service.BadgeTransactionService;
import com.ttn.reap.service.EmailService;
import com.ttn.reap.service.TransactionQuota;
import com.ttn.reap.service.UserService;
import com.ttn.reap.utility.WriteDataToCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class ApiController {
    @Autowired
    UserService userService;

    @Autowired
    TransactionQuota transactionQuota;

    @Autowired
    BadgeTransactionService badgeTransactionService;

    @Autowired
    BadgeTransactionRepo badgeTransactionRepo;


    @Autowired
    EmailService emailService;


    @PostMapping("/checkAvailability")
    public String checkAvail(@RequestParam("username") String username) {
        if (!userService.chackAvail(username)) {
            return "";
        }
        return "<span class='badge badge-danger'>User is already exists " + username + "</span>";
    }

    @PostMapping("/writeComment")
    public String write(CommentCO commentCO) {

        transactionQuota.subtractQuota(commentCO);
        badgeTransactionService.saveComment(commentCO);
        Optional<User> userOptional = userService.findByName(commentCO.getReceiverId());
        User user = userOptional.orElse(null);
        switch (commentCO.getBadgeId()) {
            case 1:
                user.setGoldBadge(user.getGoldBadge() + 1);
                user.setPoint(user.getPoint() + 30);
                break;
            case 2:
                user.setSilverBadge(user.getSilverBadge() + 1);
                user.setPoint(user.getPoint() + 20);
                break;
            case 3:
                user.setBronzeBadge(user.getBronzeBadge() + 1);
                user.setPoint(user.getPoint() + 10);
                break;
        }
        userService.register(user);
        return "hit";
    }


    @PostMapping("/deactivateUser")
    public void userDeactivate(@RequestParam("userId") int userId) {
        userService.deactivateUser(userId);
    }

    @PostMapping("/activateUser")
    public void userActivate(@RequestParam("userId") int userId) {
        userService.activateUser(userId);
    }

    @PostMapping("/changeUserRole")
    public void changeUserRole(@RequestParam("userId") int userId, @RequestParam("selectedRole") String selectedRole) {
        userService.changeUserRole(userId, selectedRole);
    }

    @PostMapping("/revokeBadge")
    public void revokeBadge(RevokeCO revokeCO) {
        BadgeTransaction badgeTransaction = badgeTransactionService.findById(revokeCO.getTransactionId());
        String message = "Your " + badgeTransaction.getBadge().getBadge() + " badge is revoked by Admin due to " +
                revokeCO.getReason() + " reason " + revokeCO.getOther();
        String subject = "Reap Recognition Revoked";
        User user = badgeTransaction.getReceiver();
        emailService.sandMail(user, message, subject);
        switch (badgeTransaction.getBadge().getBadgeId()) {
            case 1:
                user.setPoint(user.getPoint() - 30);
                user.setGoldBadge(user.getGoldBadge() - 1);
                break;
            case 2:
                user.setPoint(user.getPoint() - 20);
                user.setSilverBadge(user.getSilverBadge() - 1);
                break;
            case 3:
                user.setPoint(user.getPoint() - 10);
                user.setBronzeBadge(user.getBronzeBadge() - 1);
                break;
        }
        userService.register(user);
        badgeTransactionService.delete(badgeTransaction);
    }

    @GetMapping("/api/download/transaction.csv")
    public void downloadCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=transactions.csv");

        List<BadgeTransaction> transactions = (List<BadgeTransaction>) badgeTransactionRepo.findAll();
        WriteDataToCSV.writeObjectToCSV(response.getWriter(), transactions);
    }

}
