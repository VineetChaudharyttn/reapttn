package com.ttn.reap.controller;

import com.ttn.reap.entity.BadgeTransaction;
import com.ttn.reap.entity.CommentCO;
import com.ttn.reap.entity.CustomUserDetails;
import com.ttn.reap.entity.User;
import com.ttn.reap.service.*;
import com.ttn.reap.utility.ImageUploadUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class TestPage {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    BadgeService badgeService;

    @Autowired
    ImageUploadUtility imageUploadUtility;

    @Autowired
    EmailService emailService;

    @Autowired
    BadgeTransactionService badgeTransactionService;


    @GetMapping("/login")
    public String dashboard(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }


    @PostMapping("/register")
    public String register(User user) {
        MultipartFile multipartFile = user.getImage();
        user.setActive(true);
        user.setImagePath(imageUploadUtility.writeImage(multipartFile, user.getFirstName()));
        user.setRole(Arrays.asList(roleService.findByRole("USER")));
                userService.register(user);
        return "login";
    }


    @PreAuthorize("hasAnyRole('USER')")
    @RequestMapping("/dashboard")
    public String showDash(@AuthenticationPrincipal final CustomUserDetails userDetails, Model model) {
        User user = userService.findByName(userDetails.getUsername()).orElse(null);
        if (user == null) {
            throw new RuntimeException();
        }
        model.addAttribute("user", user);
        model.addAttribute("users",userService.findAll());
        model.addAttribute("comment",new CommentCO());
        model.addAttribute("badges",badgeService.findBadges());
//        Page<BadgeTransaction> page= badgeTransactionService.findTransaction(pageNo);
//        model.addAttribute("pageData",page);
        return "dashboard";
    }

    @PostMapping("/page")
    @ResponseBody
    public Page<BadgeTransaction> page(Integer pageNo){
        Page<BadgeTransaction> page= badgeTransactionService.findTransaction(0);
        List<BadgeTransaction> transactionList =page.getContent();
//        Gson gson=new Gson();
//        System.out.println(gson.toJson(transactionList.get(1)));
        System.out.println("hit cont");
        return page;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/adminpanel")
    public String showAdminPanel(@AuthenticationPrincipal final CustomUserDetails userDetails, Model model) {
        User user = new User();
        user = userService.findByName(userDetails.getUsername()).orElse(user);
        model.addAttribute("user", user);
        model.addAttribute("users",userService.findAll());
        model.addAttribute("role",roleService.findAll());
        return "adminpanel";
    }

    @PreAuthorize("hasAnyRole('USER')")
    @RequestMapping("/badges")
    public String showBadges(@AuthenticationPrincipal final CustomUserDetails userDetails, Model model) {
        User user = new User();
        user = userService.findByName(userDetails.getUsername()).orElse(user);
        model.addAttribute("user", user);
        return "badges";
    }


    @PostMapping("/setNewPassword")
    public String setNewPassword(User user, Model model, HttpServletRequest request) {
        Optional<User> optional = userService.findByName(user.getUsername());

        if (!optional.isPresent()) {
            model.addAttribute("errorMessage", "We didn't find an account for that e-mail address.");
        } else {

            user = optional.get();
            user.setResetToken(UUID.randomUUID().toString());

            userService.register(user);

            String appUrl = request.getScheme() + "://" + request.getServerName();
            System.out.println(appUrl);
            emailService.sandMail(user, appUrl);

            model.addAttribute("successMessage", "A password reset link has been sent to " + user.getUsername());
        }
        return "login";
    }


    @GetMapping("/reset")
    public String reset(@RequestParam("token") String token, Model model) {
        Optional<User> optional = userService.findUserByResetToken(token);
        if (!optional.isPresent()) {
            model.addAttribute("errorMessage", "Sorry token is Already used.");
            model.addAttribute("user", new User());
            return "login";
        }
        User user = new User();
        user.setResetToken(token);
        model.addAttribute("user", user);
        return "passwordreset";
    }

    @PostMapping("/updatePass")
    public String update(User user, Model model) {
        Optional<User> optionalUser = userService.findUserByResetToken(user.getResetToken());
        String password = user.getPassword();
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            model.addAttribute("errorMessage", "Password not reset successfully, You not may Login .........");
            return "login";
        }
        user.setPassword(password);
        user.setResetToken(null);
        userService.register(user);
        model.addAttribute("successMessage", "Password reset successfully, You may Login .........");

        return "login";
    }



}


