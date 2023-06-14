
package com.anudeep.blog;

import com.anudeep.blog.entites.Role;
import com.anudeep.blog.repositories.RolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BloggingApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RolesRepo rolesRepo;
	public static void main(String[] args) {
		SpringApplication.run(BloggingApplication.class, args);
	}
	
	@Bean
	public ModelMapper ModelMapper()
	{
	 return	new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("xyz"));
		try{
			Role role = new Role();
			role.setId(501);
			role.setName("ROLE_ADMIN");
			Role role2 = new Role();
			role2.setId(502);
			role2.setName("ROLE_NORMAL");

			List<Role> roles = List.of(role, role2);
			List<Role> result = this.rolesRepo.saveAll(roles);
			result.forEach(r->{
				System.out.println(r.getName());
			});
		}catch(Exception e){
			System.out.println("Something went wrong");
		}
	}
}
