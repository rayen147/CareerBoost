package com.example.techcloudpi.Repository;

import com.example.techcloudpi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRep extends JpaRepository<User,Long> {
}
