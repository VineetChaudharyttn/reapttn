package com.ttn.reap.bootstrap;

import com.ttn.reap.entity.Badge;
import com.ttn.reap.entity.Item;
import com.ttn.reap.entity.Role;
import com.ttn.reap.entity.User;
import com.ttn.reap.scheduler.QuotaInitalization;
import com.ttn.reap.service.BadgeService;
import com.ttn.reap.service.ItemService;
import com.ttn.reap.service.RoleService;
import com.ttn.reap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Arrays;

@Component
public class Bootstrap {


    @Autowired
    ItemService itemService;

    @Autowired
    BadgeService badgeService;

    @Autowired
    RoleService roleService;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserService userService;

    @Autowired
    EntityManager entityManager;

    @Autowired
    QuotaInitalization quotaInitalization;

    @EventListener(ContextRefreshedEvent.class)
    void init() {
        badge();
        roles();
        item();
        user();
    }


    private void roles() {
        Role user = new Role("USER");
        Role admin = new Role("ADMIN");
        Role superviser = new Role("SUPERVISER");
        Role precticeHead = new Role("PRECTICEHEAD");
        roleService.save(user);
        roleService.save(admin);
        roleService.save(superviser);
        roleService.save(precticeHead);
    }

    public void user() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setFirstName("User");
            user.setLastName("" + i);
            user.setUsername("user" + i + "@gmail.com");
            user.setActive(true);
            user.setImagePath("images/profilePics/user.png");
            user.setPassword(bCryptPasswordEncoder.encode("123456"));
            if (i==0) {
                user.setRole(Arrays.asList(roleService.findByRole("USER"),roleService.findByRole("ADMIN")));
            }
            else {
                user.setRole(Arrays.asList(roleService.findByRole("USER")));
            }
            userService.register(user);
            quotaInitalization.initalization(user, 1);
        }
    }



    private void badge() {
        Badge goldBadge = new Badge("Gold", 30, "images/gold.png");
        Badge silverBadge = new Badge("Silver", 20, "images/silver.png ");
        Badge bronzeBadge = new Badge("Bronze", 10, "images/bronze.png");
        badgeService.save(goldBadge);
        badgeService.save(silverBadge);
        badgeService.save(bronzeBadge);
    }

    private void item() {

        Item item1 = new Item("/images/items/tshirt.jpg", "T-Shirt", 100, 50);
        itemService.saveItem(item1);


        Item item2 = new Item("/images/items/cap.jpg", "Cap", 70, 100);
        itemService.saveItem(item2);

        Item item3 = new Item("/images/items/backpack.jpg", "Backpack", 120, 55);
        itemService.saveItem(item3);

        Item item4 = new Item("/images/items/bottle.png", "Bottle", 75, 85);
        itemService.saveItem(item4);

        Item item5 = new Item("/images/items/easy-button.jpg", "Easy Button", 155, 22);
        itemService.saveItem(item5);

        Item item6 = new Item("/images/items/keychain.jpg", "Keychain", 30, 100);
        itemService.saveItem(item6);

        Item item7 = new Item("/images/items/notebook.jpg", "Spiral Notebook + Pen Set", 40, 80);
        itemService.saveItem(item7);

        Item item8 = new Item("/images/items/passport-wallet.jpg", "Passport/Travel Wallet", 125, 30);
        itemService.saveItem(item8);

        Item item9 = new Item("/images/items/stationery-organizer.jpg", "Stationery Organizer", 50, 50);
        itemService.saveItem(item9);

        Item item10 = new Item("/images/items/power-bank.jpeg", "Belkin 10000 mAh Power Bank", 200, 60);
        itemService.saveItem(item10);

    }
}
