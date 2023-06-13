package com.anudeep.blog.services;

import com.anudeep.blog.entites.Post;
import com.anudeep.blog.payloads.PostDto;
import com.anudeep.blog.payloads.Postresponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto , Integer userId , Integer categoryId);
    PostDto updatePost(PostDto postDto , Integer postId);
    void deletePost(Integer postId);

    Postresponse getAllPost(Integer pageNumber , Integer pageSize,String sortBy);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);

}
