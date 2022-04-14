package com.example.bootstar.controller;

import com.example.bootstar.Service.PostService;
import com.example.bootstar.domain.Post;
import com.example.bootstar.domain.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class PostController {
    final PostService postService;

    @PostMapping("/post")
    public String createPost(Post post, @RequestPart MultipartFile image_data, RedirectAttributes redirectAttributes, Authentication authentication) throws Exception{
        //이미지 처리
        String sourceFileName = image_data.getOriginalFilename();
        if(sourceFileName.equals("")){
            redirectAttributes.addFlashAttribute("image_error", "이미지를 첨부해주세요");
            return "redirect:/hello";
        }
        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
        File destinationFile;
        String destinationFileName;
        String fileUrl = "/Users/a/IdeaProjects/diexam01/bootstar/src/main/resources/uploadFiles/img/";
        String onDbFileUrl = "resources/uploadFiles/img/";
        destinationFileName = RandomStringUtils.randomAlphanumeric(64)+"."+sourceFileNameExtension;
        destinationFile = new File(fileUrl + destinationFileName);
        destinationFile.getParentFile().mkdir();
        image_data.transferTo(destinationFile);


        User user = (User) authentication.getPrincipal();
        post.setAuthor_id(user.getUser_id());
        post.setImage(onDbFileUrl + destinationFileName);

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
