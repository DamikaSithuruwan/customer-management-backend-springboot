package lk.brightenacademy.customer_demo.repository;

import lk.brightenacademy.customer_demo.entity.Role;
import lk.brightenacademy.customer_demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<User> findByName(String name);
}
