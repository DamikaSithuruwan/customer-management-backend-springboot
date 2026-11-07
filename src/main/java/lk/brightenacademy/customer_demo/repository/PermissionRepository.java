package lk.brightenacademy.customer_demo.repository;

import lk.brightenacademy.customer_demo.entity.Permission;
import lk.brightenacademy.customer_demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    Optional<User> findByName(String name);
}
