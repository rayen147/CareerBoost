package com.example.CareerBoost.ServiceInterface;

public interface JWTService {
    String extractUsername(String token);
    /*String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token ,UserDetails userDetails);

    String  generateRefreshToken(HashMap<String, Object> objectObjectHashMap, UserDetails user);*/
}
