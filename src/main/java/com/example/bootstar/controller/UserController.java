package com.example.bootstar.controller;

import com.example.bootstar.Service.UserService;
import com.example.bootstar.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String root() { return "redirect:/login"; }

    @GetMapping("/login")
    public String login() { return "/login"; }

    @GetMapping("/hello")
    public String userAccess(Model model, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("name", user.getNickname());
        return "/hello";
    }

    @GetMapping("/login_fail")
    public String AccessDenied() { return "/login_fail"; }

    @GetMapping("/signup")
    public String signUpForm(){ return "/signup"; }

    @PostMapping("/signup")
    public String signup(@Valid User user, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("user",user);

            Map<String, String> validResult = userService.validHandling(errors);
            for(String key:validResult.keySet()){
                model.addAttribute(key, validResult.get(key));
            }

            return "/signup";
        }

        userService.joinUser(user);
        return "redirect:/login";
    }
}
