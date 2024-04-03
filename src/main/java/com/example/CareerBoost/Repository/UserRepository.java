package com.example.CareerBoost.Repository;

import com.example.CareerBoost.Entity.Role;
import com.example.CareerBoost.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User,Integer> {
    UserDetails findByEmail(String username);
    User findByRole(Role role);
}
