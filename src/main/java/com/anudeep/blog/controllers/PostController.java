package com.anudeep.blog.controllers;

import com.anudeep.blog.entites.Post;
import com.anudeep.blog.payloads.*;
import com.anudeep.blog.services.FileService;
import com.anudeep.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userid}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPosts(@Valid @RequestBody PostDto postDto , @PathVariable Integer userid , @PathVariable Integer categoryId){
        PostDto createPost = this.postService.createPost(postDto,userid,categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getallPostsByCategory(@PathVariable Integer categoryId)
    {
        return ResponseEntity.ok(this.postService.getPostByCategory(categoryId));
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getallPostsByUser(@PathVariable Integer userId)
    {
        List<PostDto> posts = this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    @GetMapping("posts/{id}")
    public ResponseEntity<PostDto> getPostsById(@PathVariable("id") int id)
    {
        return ResponseEntity.ok(this.postService.getPostById(id));
    }

    @GetMapping("/posts")
    public ResponseEntity<Postresponse> getAllPosts(
            @RequestParam(value = "pageNumber" , defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize" , defaultValue = "10",required = false) Integer pageSize,
            @RequestParam(value = "sortBy" , defaultValue = "postId",required = false) String sortBy
    )
    {
        Postresponse allPost = this.postService.getAllPost(pageNumber,pageSize,sortBy);
        return new ResponseEntity<Postresponse>(allPost, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("id") Integer userId)
    {
        this.postService.deletePost(userId);

        return  new ResponseEntity(new ApiResponse("Category deleted Sucess",true),HttpStatus.OK);
    }

    @PutMapping("posts/{id}")
    public ResponseEntity<PostDto> updateCategory(@Valid @RequestBody PostDto postDto , @PathVariable("id") Integer userId){
        PostDto updatePostDto = this.postService.updatePost(postDto,userId);
        return new ResponseEntity<PostDto>(updatePostDto,HttpStatus.OK);
    }

    @GetMapping("posts/search/{keyWord}")
    public ResponseEntity<List<PostDto>> searchpostBytitle(
            @PathVariable("keyWord") String keyWord
    ){
        List<PostDto> result = this.postService.searchPosts(keyWord);
        return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
    }

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadImage(
             @PathVariable("postId") Integer postId,
             @RequestParam("image") MultipartFile image
    ) throws IOException {
        PostDto postDto=this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        PostDto upadtedPost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(upadtedPost,HttpStatus.OK);
    }

    @GetMapping(value = "/images/{postId}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void download(
            @PathVariable("postId") Integer postId,
            HttpServletResponse response
    ) throws IOException {
        PostDto postDto = this.postService.getPostById(postId);
        String imageName=postDto.getImageName();
        InputStream resource = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
