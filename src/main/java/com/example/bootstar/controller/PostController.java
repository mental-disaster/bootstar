package com.example.bootstar.controller;

import com.example.bootstar.Service.PostService;
import com.example.bootstar.domain.Post;
import com.example.bootstar.domain.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class PostController {
    final PostService postService;
    private String fileUrl = "/Users/a/IdeaProjects/diexam01/bootstar/src/main/resources/uploadFiles/img/";

    @PostMapping("/post")
    public String createPost(Post post, @RequestPart MultipartFile image_data, RedirectAttributes redirectAttributes, Authentication authentication) throws Exception{
        //이미지 처리
        String sourceFileName = image_data.getOriginalFilename();
        if(sourceFileName.equals("")){
            redirectAttributes.addFlashAttribute("image_error", "이미지를 첨부해주세요");
            return "redirect:/hello";
        }
        String image_name = postService.saveImage(image_data);

        User user = (User) authentication.getPrincipal();
        post.setAuthor_id(user.getUser_id());
        post.setImage(image_name);

        postService.createPost(post);
        return "redirect:/hello";
    }

    @ResponseBody
    @GetMapping("/image/{img_name}")
    public Resource showImage(@PathVariable String img_name) throws MalformedURLException{
        return new UrlResource("file:"+fileUrl+img_name);
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
