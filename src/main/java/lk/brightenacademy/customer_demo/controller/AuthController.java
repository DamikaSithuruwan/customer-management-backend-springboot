package lk.brightenacademy.customer_demo.controller;

import lk.brightenacademy.customer_demo.dto.LoginResponseDTO;
import lk.brightenacademy.customer_demo.dto.UserLoginDTO;
import lk.brightenacademy.customer_demo.repository.UserRepository;
import lk.brightenacademy.customer_demo.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

@CrossOrigin
@RestController
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody UserLoginDTO userLoginDTO){
        Authentication authentication =
                authenticationManager.authenticate(
                        UsernamePasswordAuthenticationToken
                                .unauthenticated(
                                        userLoginDTO.getUsername(),
                                        userLoginDTO.getPassword()
                                )
                );
        String token = jwtService.generateToken(authentication);

        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setToken(token);
        return responseDTO;
    }

    @GetMapping("/user-details")
    public void getuserDetails(@RequestHeader("Authorization") String token) {
        /*SecretKey key = Keys.hmacShaKeyFor(
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
        System.out.println(claims.get("email"));*/

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
