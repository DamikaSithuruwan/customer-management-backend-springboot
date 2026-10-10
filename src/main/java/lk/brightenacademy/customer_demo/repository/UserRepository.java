package lk.brightenacademy.customer_demo.repository;

import lk.brightenacademy.customer_demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByMobileOrNicOrEmail(String mobile, String nic, String email);

}
