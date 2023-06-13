package com.anudeep.blog.payloads;

import com.anudeep.blog.entites.Category;
import com.anudeep.blog.entites.Comments;
import com.anudeep.blog.entites.user;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
public class PostDto {


    private  Integer postId;
    private String title;

    private String Content;
   private String imageName;
   private Date addedDate;


    private CategoryDto category;


    private UserDto user;

    private Set<Comments> comments = new HashSet<>();
}
