package com.anudeep.blog.controllers;

import com.anudeep.blog.payloads.ApiResponse;
import com.anudeep.blog.payloads.commentDto;
import com.anudeep.blog.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentsController {
    @Autowired
    private CommentsService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<commentDto> createComment( @RequestBody commentDto commentDto  , @PathVariable Integer postId ){
        commentDto createComment = this.commentService.createComment(commentDto,postId);
        return new ResponseEntity<commentDto>(createComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId ){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("delete comment",true),HttpStatus.OK);
    }

}
