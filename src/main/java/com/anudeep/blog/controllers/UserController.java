package com.anudeep.blog.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anudeep.blog.payloads.ApiResponse;
import com.anudeep.blog.payloads.UserDto;
import com.anudeep.blog.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")

public class UserController {
	
	@Autowired
    private UserService userService;
    
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
    {
		UserDto createUserDto = this.userService.createUser(userDto);
    	return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
    	
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateuser(@Valid @RequestBody UserDto userDto ,@PathVariable("id") Integer userId)
    {
    	UserDto updateUserDto = this.userService.updateUser(userDto, userId);
    	
    	return new ResponseEntity<>(updateUserDto,HttpStatus.OK);
		
    	
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Integer userId)
    {
    	this.userService.deleteUser(userId);
    	
    	return  new ResponseEntity(new ApiResponse("user deleted Sucess",true),HttpStatus.OK);
    }
    
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getallUsers()
    {
    	return ResponseEntity.ok(this.userService.getAllUsers());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") int id)
    {
    	return ResponseEntity.ok(this.userService.getUserById(id));
    }
    
}
