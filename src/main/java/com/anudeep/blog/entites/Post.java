package com.anudeep.blog.entites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Column(name="post_title" , length=100,nullable=false)
    private String title;
    @Column( length=1000)
    private String Content;
    private String imageName;
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    private user user;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private Set<Comments> comments = new HashSet<>();
}
