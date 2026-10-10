package lk.brightenacademy.customer_demo.service;

import lk.brightenacademy.customer_demo.entity.User;
import lk.brightenacademy.customer_demo.repository.UserRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByMobileOrNicOrEmail(username, username, username);

        if(optionalUser.isEmpty()){
            throw UsernameNotFoundException.fromUsername(username );
        }
        User user = optionalUser.get();

        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public @Nullable String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {

                return user.getId().toString();
            }
        };
        return userDetails;
    }
}
