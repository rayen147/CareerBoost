package com.example.CareerBoost.Config;

/*
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfiguration {
    private final JwtAutenticationFilter jwtAutenticationFilter;
    private final UserService userService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/api/auth/**").permitAll()
                        .antMatchers("/api/admin/**").hasAuthority(Role.ADMIN.name())
                        .antMatchers("/api/student/**").hasAuthority(Role.Student.name())
                        .antMatchers("/api/investor/**").hasAuthority(Role.Investor.name())
                        .antMatchers("/api/former/**").hasAuthority(Role.Former.name())
                        .anyRequest().authenticated()
                )
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAutenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean

    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService.userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();


    }
}*/
