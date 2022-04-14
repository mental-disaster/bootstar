package com.example.bootstar.Service;

import com.example.bootstar.domain.Post;
import com.example.bootstar.domain.User;
import com.example.bootstar.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {

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
    public void deletePost(int post_id){
        List<Map<String ,Object>> post = postMapper.getPostByPostId(post_id);
        String fileUrl = "/Users/a/IdeaProjects/diexam01/bootstar/src/main/"+post.get(0).get("image");
        File file = new File(fileUrl);
        file.delete();

        postMapper.terminatePost(post_id);
    }

    //게시물 수정
    @Transactional
    public void updatePost(Post post){
        postMapper.changePost(post);
    }

    //게시물 읽기
    public List<Map<String ,Object>> selectAllPost(){
        return postMapper.getAllPost();
    }

    public List<Map<String ,Object>> selectPostByUserId(int author_id){
        return postMapper.getPostByUserId(author_id);
    }
}
