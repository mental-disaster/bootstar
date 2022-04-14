package com.example.bootstar.controller;

import com.example.bootstar.Service.PostService;
import com.example.bootstar.Service.UserService;
import com.example.bootstar.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class UserController {

    final UserService userService;
    final PostService postService;

    //메인화면(로그인 화면)
    @GetMapping
    public String root() { return "redirect:/login"; }

    @GetMapping("/login")
    public String login() { return "/login"; }

    @GetMapping("/login_fail")
    public String accessDenied() { return "/login_fail"; }

    //회원가입
    @GetMapping("/signup")
    public String signUpForm(){ return "/signup"; }

    @PostMapping("/signup")
    public String signup(@Valid User user, Errors errors, Model model){

        boolean trigger = false;
        //아이디 중복 검증 - DB에서 아이디 기반 검색에 성공하면 이미 존재하는 아이디
        User user_info = userService.loadUserByUsername(user.getUsername());
        if(user_info != null){
            trigger = true;
        }
        model.addAttribute("error_username", "이미 존재하는 회원입니다");
        //입력값 유효성 검증
        if(errors.hasErrors()){
            Map<String, String> validResult = userService.validHandling(errors);
            for(String key:validResult.keySet()){
                model.addAttribute(key, validResult.get(key));
            }
            trigger = true;
        }
        if(trigger){
            return "/signup";
        }

        //신규 유저 생성
        userService.joinUser(user);
        return "redirect:/login";
    }

    //로그인 후 메인화면(모든 유저 타임라인)
    @GetMapping("/hello")
    public String userAccess(Model model, @ModelAttribute("image_error") String image_error, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        List<Map<String, Object>> posts = postService.selectAllPost();
        model.addAttribute("image_error",image_error);
        model.addAttribute("posts",posts);
        return "/hello";
    }

    //개인 타임라인
    @GetMapping("/personal")
    public String personalPage(Model model, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        List<Map<String, Object>> posts = postService.selectPostByUserId(user.getUser_id());
        model.addAttribute("posts",posts);
        return "/personal";

    }
}
