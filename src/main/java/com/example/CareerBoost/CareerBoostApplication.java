package com.example.CareerBoost;
<<<<<<< HEAD



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CareerBoostApplication  {
	public static void main(String[] args) {
		SpringApplication.run(CareerBoostApplication.class, args);
	}
=======
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
//@scheduled nhotha f methode sans parametre w f scheduled nhotou parametre()

@EnableAspectJAutoProxy


@SpringBootApplication
public class CareerBoostApplication {

	public static void main(String[] args) {
		SpringApplication.run(CareerBoostApplication.class, args);
	}

>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
}
