package com.ttn.reap.controller;

import com.ttn.reap.entity.*;
import com.ttn.reap.scheduler.QuotaInitalization;
import com.ttn.reap.service.*;
import com.ttn.reap.utility.ImageUploadUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class ReapController {

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

    @Autowired
    QuotaInitalization quotaInitalization;

    @Autowired
    PurchaseService purchaseService;


    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

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
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.register(user);
        quotaInitalization.initalization(user,1);
        return "login";
    }


    @PreAuthorize("hasAnyRole('USER')")
    @RequestMapping("/")
    public String showDash(@AuthenticationPrincipal final CustomUserDetails userDetails, Model model) {
        User user = userService.findByName(userDetails.getUsername()).orElse(null);
        if (user == null) {
            throw new RuntimeException();
        }
        model.addAttribute("user", user);
        model.addAttribute("users",userService.findAll());
        model.addAttribute("comment",new CommentCO());
        model.addAttribute("badges",badgeService.findBadges());
        List<BadgeTransaction> list= badgeTransactionService.findTransaction();
        model.addAttribute("pageData",list);
        return "dashboard";
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
        List<BadgeTransaction> list= badgeTransactionService.findTransaction();
        model.addAttribute("pageData",list);
        model.addAttribute("purchase",purchaseService.getHistory(user));
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
            String message="To reset your password, click the link below:\n" + appUrl
                    + ":8080/reset?token=" + user.getResetToken();
            String subject="Password Reset Request";
            emailService.sandMail(user,message,subject);

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
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setResetToken(null);
        userService.register(user);
        model.addAttribute("successMessage", "Password reset successfully, You may Login .........");

        return "login";
    }



    @GetMapping("/test")
    public String test(){
        return "index";
    }

}


