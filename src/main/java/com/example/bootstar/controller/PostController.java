package com.example.bootstar.controller;

import com.example.bootstar.Service.PostService;
import com.example.bootstar.domain.Post;
import com.example.bootstar.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class PostController {
    final PostService postService;
    private String fileUrl = "/Users/a/IdeaProjects/diexam01/bootstar/src/main/resources/uploadFiles/img/";

    @PostMapping("/post")
    public String createPost(Post post, @RequestPart MultipartFile image_data, RedirectAttributes redirectAttributes, HttpServletRequest request, Authentication authentication) throws Exception{
        //이미지 처리
        String sourceFileName = image_data.getOriginalFilename();
        if(sourceFileName.equals("")){
            redirectAttributes.addFlashAttribute("image_error", "이미지를 첨부해주세요");
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        }
        String image_name = postService.saveImage(image_data);
        post.setImage(image_name);

        User user = (User) authentication.getPrincipal();
        post.setAuthor_id(user.getUser_id());

        postService.createPost(post);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @ResponseBody
    @GetMapping("/image/{img_name}")
    public Resource showImage(@PathVariable String img_name) throws MalformedURLException{
        return new UrlResource("file:"+fileUrl+img_name);
    }

    @DeleteMapping("/post/{post_id}")
    public String deletePost(@PathVariable("post_id")int id, HttpServletRequest request){
        postService.deletePost(id);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @PutMapping("/post/{post_id}")
    public String updatePost(Post post, @RequestPart MultipartFile image_data, HttpServletRequest request) throws Exception{
        String sourceFileName = image_data.getOriginalFilename();
        if(!sourceFileName.equals("")){
            String image_name = postService.saveImage(image_data);
            post.setImage(image_name);
        }

        postService.updatePost(post);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
