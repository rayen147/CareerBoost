package com.example.CareerBoost.ServiceImpl;

import com.example.CareerBoost.Repository.UserRepository;
import com.example.CareerBoost.ServiceInterface.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
 /*   private final PasswordEncoder passwordEncoder;
    //validate email and pasword
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final MailService mailService;
    @Value("${app.baseurl}")
    private String baseUrl;
    public User signup(SignUpRequest signUpRequest) throws CarrerBoostException {
        User user=new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setRole(signUpRequest.getRole());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEnabled(false);
        user.setVerificationToken(UUID.randomUUID().toString());
        User savedUser=userRepository.save(user);
        String verificationLink = baseUrl + "/verify-email?token=" + user.getVerificationToken();
        String emailBody = "Thank you for signing up! Please click the following link to verify your email address: " + verificationLink;
        mailService.sendMail(new NotificationEmail("Verify your email address", user.getEmail(), emailBody));
        return  savedUser;
    }

    public JwtAuthenticationResponse signIn(LoginRequest loginRequest)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));

        var user=userRepository.findByEmail(loginRequest.getEmail());
        if (!user.isEnabled()) {

            throw new RuntimeException("User account is not enabled. Please verify your email to activate your account.");
        }
        var jwt=jwtService.generateToken(user);
        var refresh=jwtService.generateRefreshToken(new HashMap<>(),user);
        JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refresh);
        return jwtAuthenticationResponse;

    }*/
}
