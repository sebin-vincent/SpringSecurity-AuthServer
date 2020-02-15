package com.example.springauth.config;

import com.example.springauth.model.User;
import com.example.springauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUserEmail(username);
        List<String> previlages=userRepository.getAuthorizations(username);
        for(String previlage:previlages){

            System.out.println(previlage);

        }
        user.setPrevilages(previlages);
        return user ;
    }
}
