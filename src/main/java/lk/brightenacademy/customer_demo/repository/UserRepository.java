package lk.brightenacademy.customer_demo.repository;

import lk.brightenacademy.customer_demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByMobileOrNicOrEmail(String mobile, String nic, String email);

    @Query(value = """
        SELECT role_id AS roleId, COUNT(DISTINCT user_id) AS total
        FROM user_role
        GROUP BY role_id
        """, nativeQuery = true)
    List<RoleUserCount> countUsersGroupedByRole();

    interface RoleUserCount {
        Integer getRoleId();
        Long getTotal();
    }

}
