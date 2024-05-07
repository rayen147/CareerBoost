package com.example.CareerBoost.Controller;

import com.example.CareerBoost.Repository.UserRepository;
import com.example.CareerBoost.ServiceInterface.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")

public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserRepository userRepository;
/*
    @PostMapping("/signup")
    public ResponseEntity<User> sighup(@RequestBody SignUpRequest signUpRequest) throws CarrerBoostException {
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));

    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse>signin(@RequestBody LoginRequest loginRequest)
    {
        return ResponseEntity.ok(authenticationService.signIn(loginRequest));

    }
    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam("token") String verificationToken) {
        User user = userRepository.findByVerificationToken(verificationToken);
        if (user != null) {

            user.setVerificationToken(null);
            user.setEnabled(true);
            userRepository.save(user);
            return "Email verified successfully. You can now log in.";
        } else {
            return "Invalid verification token.";
        }
    }*/
}
