package com.example.CareerBoost;

import com.example.CareerBoost.Entity.Role;
import com.example.CareerBoost.Entity.User;
import com.example.CareerBoost.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CareerBoostApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;


	public static void main(String[] args) {
		SpringApplication.run(CareerBoostApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User adminAccount=userRepository.findByRole(Role.ADMIN);
		if(null==adminAccount)
		{
			User user=new User();
			user.setEmail("admin@gmail.com");
			user.setFirstName("admin");
			user.setLastName("admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}

	}
}
