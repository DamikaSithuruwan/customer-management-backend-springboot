package lk.brightenacademy.customer_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class JwtService {

    @Autowired
    JwtEncoder jwtEncoder;

    @Value("${security.jwt.expiration}")
    Long jwtExpiration;

    // Generate JWT
    public String generateToken(Authentication authentication){

        Instant now = Instant.now();

        List<String> authorities =
                authentication
                        .getAuthorities()
                        .stream()
                        .map(
                                GrantedAuthority::getAuthority
                        )
                        .toList();

        JwtClaimsSet claims =
                JwtClaimsSet.builder()
                        .issuer("customer-management-backend-springboot")
                        .issuedAt(now)
                        .expiresAt(
                                now.plusSeconds(jwtExpiration)
                        )
                        .subject(
                                authentication.getName()
                        )
                        .claim(
                                "authorities",
                                       authorities
                        )
                        .build();

        JwsHeader header =
                JwsHeader
                        .with(MacAlgorithm.HS256)
                        .build();

        return jwtEncoder
                .encode(
                        JwtEncoderParameters.from(
                                header,
                                claims
                        )
                )
                .getTokenValue();

    }
}
