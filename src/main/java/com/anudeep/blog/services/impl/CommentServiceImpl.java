package com.anudeep.blog.services.impl;

import com.anudeep.blog.entites.Comments;
import com.anudeep.blog.entites.Post;
import com.anudeep.blog.exceptions.ResourceNotFoundException;
import com.anudeep.blog.payloads.commentDto;
import com.anudeep.blog.repositories.CommentsRepo;
import com.anudeep.blog.repositories.PostRepo;
import com.anudeep.blog.services.CommentsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
@Service
public class CommentServiceImpl implements CommentsService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentsRepo commentsRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public commentDto createComment(commentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));
        Comments comment1 = this.modelMapper.map(commentDto, Comments.class);
        comment1.setPost(post);
        Comments savedComments = this.commentsRepo.save(comment1);
        return this.modelMapper.map(savedComments,commentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comments comment = this.commentsRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment","id",commentId));
        this.commentsRepo.delete(comment);
    }
}
