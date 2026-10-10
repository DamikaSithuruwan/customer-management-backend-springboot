package lk.brightenacademy.customer_demo.config;

import jakarta.persistence.EntityManager;
import lk.brightenacademy.customer_demo.entity.User;
import lk.brightenacademy.customer_demo.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<User> {

    @Autowired
    AuthUserService authUserService;

    @Autowired
    EntityManager entityManager;

    @Override
    public Optional<User> getCurrentAuditor() {

        Authentication authentication = authUserService.getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {

            return Optional.empty();
        }

        if (authentication instanceof JwtAuthenticationToken) {

            return Optional.of(entityManager.getReference(User.class, authUserService.getAuthUserId()) );
        }

        return Optional.empty();
    }
}