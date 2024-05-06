package com.example.CareerBoost.ServiceInterface;


import com.example.CareerBoost.Entity.User;

import java.util.List;

public interface UserService {
   /* UserDetailsService userDetailsService();*/
   List<User> retrieveAllusers();
}
