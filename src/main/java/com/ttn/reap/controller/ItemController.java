package com.ttn.reap.controller;

import com.ttn.reap.entity.*;
import com.ttn.reap.service.BagService;
import com.ttn.reap.service.ItemService;
import com.ttn.reap.service.PurchaseService;
import com.ttn.reap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@RestController
public class ItemController {
    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    @Autowired
    BagService bagService;

    @Autowired
    PurchaseService purchaseService;

    @PreAuthorize("hasAnyRole('USER')")
    @RequestMapping("/items")
    public ModelAndView itemList(@AuthenticationPrincipal final CustomUserDetails userDetails) {
        ModelAndView modelAndView = new ModelAndView("items");
        User user=userService.findByName(userDetails.getUsername()).orElse(null);
        modelAndView.addObject("itemList", itemService.findAllItems());
        modelAndView.addObject("items",bagService.findByUser(user.getUserId()));
        return modelAndView;
    }


    @PostMapping("/addItem")
    public List<Bag> addToBag(@RequestParam("itemId") Integer itemId, @AuthenticationPrincipal final CustomUserDetails userDetails){
        Item item=itemService.findItem(itemId).get();
        User user=userService.findByName(userDetails.getUsername()).orElse(null);
        Bag bag=bagService.findByUserAndItem(itemId,user.getUserId());
        if(bag==null){
            bag=new Bag();
            bag.setItemId(item.getId());
            bag.setName(item.getName());
            bag.setUserId(user.getUserId());
            bag.setPointsWorth(item.getPointsWorth());
            bag.setQuantity(1);
            bag.setTotal(item.getPointsWorth());
            bagService.saveItem(bag);
        }
        else {
            bag.setQuantity(bag.getQuantity()+1);
            bag.setTotal(bag.getTotal()+item.getPointsWorth());
            bagService.saveItem(bag);
        }
        return bagService.findByUser(user.getUserId());
    }

    @PostMapping("/removeItem")
    public List<Bag> removeFromBag(@RequestParam("itemId") Integer itemId, @AuthenticationPrincipal final CustomUserDetails userDetails){
        User user=userService.findByName(userDetails.getUsername()).orElse(null);
        Bag bag=bagService.findByUserAndItem(itemId,user.getUserId());
        if(bag.getQuantity()==1){
            bagService.deleteItem(bag);
        }
        else {
            bag.setQuantity(bag.getQuantity()-1);
            Item item=itemService.findItem(itemId).get();
            bag.setTotal(bag.getTotal()-item.getPointsWorth());
            bagService.saveItem(bag);
        }
        return bagService.findByUser(user.getUserId());
    }

    @PostMapping("/buy")
    public String buy(@AuthenticationPrincipal final CustomUserDetails userDetails){
        User user=userService.findByName(userDetails.getUsername()).orElse(null);
        List<Bag> bagList=bagService.findByUser(user.getUserId());
        Integer total=0;
        for (Bag bag:bagList){
            total+=bag.getTotal();
        }
        if (bagList.isEmpty()) {
           return "listEmpty";
        } else if (total<=user.getPoint()) {
            user.setPoint(user.getPoint()-total);
            userService.register(user);
            for (Bag bag:bagList
                 ) {
                purchaseService.save(new Purchase(user,itemService.findItem(bag.getItemId()).get(),new Date(),bag.getQuantity()));
                bagService.deleteItem(bagService.findByUserAndItem(bag.getItemId(),user.getUserId()));
            }
           return "success";
        }
        return "fails";
    }
}
