package com.example.bootstar.service;

import com.example.bootstar.domain.Post;
import com.example.bootstar.domain.User;
import com.example.bootstar.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {

    @Value("${fileUrl.imgUrl}")
    private String imgUrl;

    final PostMapper postMapper;

    //게시물 생성
    @Transactional
    public void createPost(Post post){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        post.setAuthor_id(user.getUser_id());
        postMapper.insertPost(post);
    }

    //게시물 삭제
    @Transactional
    public void deletePost(int postId){
        List<Map<String ,Object>> post = postMapper.getPostByPostId(postId);
        String destFileUrl = imgUrl+post.get(0).get("image");
        File file = new File(destFileUrl);
        file.delete();

        postMapper.terminatePost(postId);
    }

    //게시물 수정
    @Transactional
    public void updatePost(Post post){
        if(post.getImage() != null){
            List<Map<String ,Object>> oldPost = postMapper.getPostByPostId(post.getPost_id());
            String destFileUrl = imgUrl+oldPost.get(0).get("image");
            File file = new File(destFileUrl);
            file.delete();
        }
        postMapper.changePost(post);
    }

    //게시물 읽기
    public List<Map<String ,Object>> selectAllPost(){
        return postMapper.getAllPost();
    }

    public List<Map<String ,Object>> selectPostByUserId(int authorId){
        return postMapper.getPostByUserId(authorId);
    }

    //이미지 처리
    public String saveImage(MultipartFile imgData){
        String srcFileName = imgData.getOriginalFilename();
        String srcFileNameExt = FilenameUtils.getExtension(srcFileName).toLowerCase();
        String destFileName = RandomStringUtils.randomAlphanumeric(64)+"."+srcFileNameExt;
        File destFile = new File(imgUrl + destFileName);
        destFile.getParentFile().mkdir();
        try {
            imgData.transferTo(destFile);
        } catch (IOException e) {
            //TODO: 예외처리 로직 추가 필요
            e.printStackTrace();
        }

        return destFileName;
    }
}
