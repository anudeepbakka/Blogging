package com.anudeep.blog.Security;

import com.anudeep.blog.entites.user;
import com.anudeep.blog.exceptions.ResourceNotFoundException;
import com.anudeep.blog.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustumUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user user = this.userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("user", "email :" + username, 0));
        return user;
    }
}
