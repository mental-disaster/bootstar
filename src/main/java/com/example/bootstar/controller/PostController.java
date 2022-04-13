package com.example.bootstar.controller;

import com.example.bootstar.Service.PostService;
import com.example.bootstar.domain.Post;
import com.example.bootstar.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class PostController {
    final PostService postService;

    @PostMapping("/post")
    public String createPost(Post post, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        post.setAuthor_id(user.getUser_id());
        post.setImage("");
        postService.createPost(post);
        return "redirect:/hello";
    }

    @DeleteMapping("/post/{post_id}")
    public String deletePost(@PathVariable("post_id")int id){
        postService.deletePost(id);
        return "redirect:/hello";
    }

    @PutMapping("/post/{post_id}")
    public String updatePost(Post post){
        postService.updatePost(post);
        return "redirect:/hello";
    }
}
