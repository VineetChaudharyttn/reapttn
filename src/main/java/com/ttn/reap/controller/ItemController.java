package com.ttn.reap.controller;

import com.ttn.reap.entity.Bag;
import com.ttn.reap.entity.CustomUserDetails;
import com.ttn.reap.entity.Item;
import com.ttn.reap.entity.User;
import com.ttn.reap.service.BagService;
import com.ttn.reap.service.ItemService;
import com.ttn.reap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ItemController {
    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    @Autowired
    BagService bagService;

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
    public void addToBag(@RequestParam("itemId") Integer itemId, @AuthenticationPrincipal final CustomUserDetails userDetails){
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
    }

    @PostMapping("/removeItem")
    public void removeFromBag(@RequestParam("itemId") Integer itemId,@AuthenticationPrincipal final CustomUserDetails userDetails){
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
    }
}
