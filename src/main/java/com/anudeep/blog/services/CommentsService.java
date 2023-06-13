package com.anudeep.blog.services;

import com.anudeep.blog.payloads.commentDto;

public interface CommentsService {

    commentDto createComment(commentDto commentDto , Integer postId);
    void deleteComment(Integer commentId);
}
