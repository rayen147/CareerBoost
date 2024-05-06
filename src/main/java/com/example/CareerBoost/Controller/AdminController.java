package com.example.CareerBoost.Controller;

import com.example.CareerBoost.Entity.User;
import com.example.CareerBoost.ServiceInterface.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/")
@AllArgsConstructor
public class AdminController {
    UserService iuserService;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public String test()
    {
        return "hello im admin";
    }

    //postman
    @GetMapping("/retrieveallUser")
    public List<User> retrieveAllusers() {
        return iuserService.retrieveAllusers();
    }
}
