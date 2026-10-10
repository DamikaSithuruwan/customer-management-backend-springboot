package lk.brightenacademy.customer_demo.service;

import lk.brightenacademy.customer_demo.entity.User;
import lk.brightenacademy.customer_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {

    @Autowired
    UserRepository userRepository;

    public Authentication getAuthentication(){
        return SecurityContextHolder
                .getContext()
                .getAuthentication();
    }

    public Integer getAuthUserId(){
        try {
            return Integer.parseInt(getAuthentication().getName());
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }

    public User getAuthUser(){
        try {
            return userRepository.findById(getAuthUserId()).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}
