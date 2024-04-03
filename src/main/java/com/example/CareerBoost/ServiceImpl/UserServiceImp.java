package com.example.CareerBoost.ServiceImpl;

import com.example.CareerBoost.Repository.UserRepository;
import com.example.CareerBoost.ServiceInterface.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    public UserDetailsService userDetailsService()
    {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username);
            }
        };
    }
}
