package com.ttn.reap.scheduler;

import com.ttn.reap.entity.Badge;
import com.ttn.reap.entity.Quota;
import com.ttn.reap.entity.Role;
import com.ttn.reap.entity.User;
import com.ttn.reap.service.BadgeService;
import com.ttn.reap.service.QuotaService;
import com.ttn.reap.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class QuotaInitalization {

    private static final Logger logger = LoggerFactory.getLogger(QuotaInitalization.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    UserService userService;

    @Autowired
    QuotaService quotaService;

    @Autowired
    BadgeService badgeService;

    @Scheduled(cron = "0 0 0 1 */3 * ")
    public void scheduleTaskWithCronExpression() {
        System.out.println("cron"+dateTimeFormatter.format(LocalDateTime.now()));
        quotaService.deleteAll();
        List<User> users = userService.findAll();
        User user;
        Role role;
        int flag;
        Iterator<User> userIterator = users.iterator();
        while (userIterator.hasNext()) {
            flag = 1;
            user = userIterator.next();
            Iterator<Role> roleIterator = user.getRole().iterator();
            while (roleIterator.hasNext()) {
                role = roleIterator.next();
                if (role.getRole().equals("PRACTICEHEAD")) {
                    if (flag < 3) {
                        flag = 3;
                        break;
                    }
                } else if (role.getRole().equals("SUPERVISOR")) {
                    if (flag < 2)
                        flag = 2;
                }
            }

            initalization(user, flag);
        }

    }

    private void initalization(User user, int flag) {
        Badge gold = badgeService.findById(1);
        Badge silver = badgeService.findById(2);
        Badge bronze = badgeService.findById(3);
        if (flag == 1) {
            quota(user, gold, 1);
            quota(user, silver, 2);
            quota(user, bronze, 3);
        }
        if (flag == 2) {
            quota(user, gold, 2);
            quota(user, silver, 3);
            quota(user, bronze, 6);
        }
        if (flag == 3) {
            quota(user, gold, 3);
            quota(user, silver, 6);
            quota(user, bronze, 9);
        }
    }


    private void quota(User user, Badge badge, int quantity) {
        Quota quota=new Quota();
        quota.setUserId(user);
        quota.setBadgeId(badge);
        quota.setQuantity(quantity);
        quotaService.saveQuota(quota);
    }
}