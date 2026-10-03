package lk.brightenacademy.customer_demo.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lk.brightenacademy.customer_demo.dto.UserLoginDTO;
import lk.brightenacademy.customer_demo.entity.User;
import lk.brightenacademy.customer_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HexFormat;
import java.util.Optional;

@CrossOrigin
@RestController
public class AuthController {

    String secret = "7f3c9a1e8b6d2f04c5a7e9b1d3f6a8c2e4b7d9f1a6c8e0d2f5b3a7c9e1d4f6b8";

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDTO userLoginDTO){

        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();
        Optional<User> optionalUser = userRepository.findByMobileOrNicOrEmail(username, username, username);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if(encoder.matches(password, user.getPassword())){

                SecretKey key = Keys.hmacShaKeyFor(
                        secret.getBytes(StandardCharsets.UTF_8)
                );

                String token = Jwts.builder()
                        .subject(user.getId())
                        .claim("mobile", user.getMobile())
                        .claim("nic", user.getNic())
                        .claim("email", user.getEmail())
                        .issuedAt(new Date())
                        .expiration(new Date(System.currentTimeMillis() + 3600000))
                        .signWith(key)
                        .compact();

                return token;
            }
            else{
                return "Authentication Failed";
            }
        }
        else{
            return "Authentication Failed";
        }
    }

    @GetMapping("/user-details")
    public void getuserDetails(@RequestHeader("Authorization") String token) {
        SecretKey key = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        System.out.println(claims.getSubject());
        System.out.println(claims.get("mobile"));
        System.out.println(claims.get("nic"));
        System.out.println(claims.get("email"));

    }

    public static  String md5(String value) {
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] hash = md.digest(
                    value.getBytes(StandardCharsets.UTF_8)
            );

            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
