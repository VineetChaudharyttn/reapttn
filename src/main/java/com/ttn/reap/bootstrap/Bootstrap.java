package com.ttn.reap.bootstrap;

import com.ttn.reap.entity.Item;
import com.ttn.reap.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap {


    @Autowired
    ItemService itemService;

//        @EventListener(ContextRefreshedEvent.class)
    void setUp() {

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
