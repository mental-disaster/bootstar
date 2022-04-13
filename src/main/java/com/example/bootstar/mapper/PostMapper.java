package com.example.bootstar.mapper;

import com.example.bootstar.domain.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper {
    //게시물 생성
    void insertPost(Post post);

    //게시물 수정
    void changePost(Post post);

    //게시물 삭제
    void terminatePost(Post post);

    //게시물 읽기
    List<Map<String, Object>> getPostByUserId(int author_id);
}
