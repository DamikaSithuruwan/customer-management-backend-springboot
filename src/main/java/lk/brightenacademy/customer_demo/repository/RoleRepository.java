package lk.brightenacademy.customer_demo.repository;

import lk.brightenacademy.customer_demo.entity.Role;
import lk.brightenacademy.customer_demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<User> findByName(String name);
}
