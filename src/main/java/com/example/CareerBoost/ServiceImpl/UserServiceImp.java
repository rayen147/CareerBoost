package com.example.CareerBoost.ServiceImpl;

import com.example.CareerBoost.Entity.User;
import com.example.CareerBoost.Repository.UserRepository;
import com.example.CareerBoost.ServiceInterface.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> retrieveAllusers() {
        return userRepository.findAll();
    }
  /*  public UserDetailsService userDetailsService()
    {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username);
            }
        };
    }*/
}
