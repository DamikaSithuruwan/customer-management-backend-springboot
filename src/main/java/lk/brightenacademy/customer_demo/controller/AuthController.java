package lk.brightenacademy.customer_demo.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lk.brightenacademy.customer_demo.dto.UserLoginDTO;
import lk.brightenacademy.customer_demo.entity.User;
import lk.brightenacademy.customer_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDTO userLoginDTO){
        Authentication authentication =
                authenticationManager.authenticate(
                        UsernamePasswordAuthenticationToken
                                .unauthenticated(
                                        userLoginDTO.getUsername(),
                                        userLoginDTO.getPassword()
                                )
                );
        return "Login Successful:" + authentication.getName();
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
