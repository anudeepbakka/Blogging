package com.anudeep.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.anudeep.blog.exceptions.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anudeep.blog.entites.user;
import com.anudeep.blog.payloads.UserDto;
import com.anudeep.blog.repositories.UserRepo;
import com.anudeep.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		user user = this.dtoTouser(userDto);
		user savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		user user = this.userRepo.findById(userId)
				.orElseThrow((()-> new ResourceNotFoundException("user","id",userId)));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		user updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		user user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
		UserDto userDto = this.userToDto(user);
		return userDto;
		
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<user> users = this.userRepo.findAll();
		List<UserDto> userDto = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		user user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
		this.userRepo.delete(user);
		

	}
	
	private user dtoTouser(UserDto userDto)
	{
		user user = new user();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		return user;
		
	}
	
	private UserDto userToDto(user user)
	{
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		
		return userDto;
		
	}

}
