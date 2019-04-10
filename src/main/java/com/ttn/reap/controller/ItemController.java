package com.ttn.reap.controller;

import com.ttn.reap.entity.Item;
import com.ttn.reap.entity.User;
import com.ttn.reap.service.ItemService;
import com.ttn.reap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ItemController {
    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    User user;

    @RequestMapping("/items/{id}")
    public ModelAndView itemList(@PathVariable Integer id){
//        user = userService.findUserById(id);

        ModelAndView modelAndView = new ModelAndView("items");
        modelAndView.addObject("itemList",itemService.findAllItems());
        return modelAndView;
    }

    @GetMapping("/checkAvailability/{id}")
    public ModelAndView check(@PathVariable Integer id, Model model){
       Optional<Item> item = itemService.findItem(1);
       if (!item.isPresent()/*item.get().getPointsWorth()>user.p*/){
           model.addAttribute("error","Purchased failed");
       }
       else {
           model.addAttribute("success","Purchased done");
       }
       ModelAndView modelAndView = new ModelAndView("redirect:/items/" + user.getUserId());
        return modelAndView;
    }
}
