package lk.brightenacademy.customer_demo.repository;

import lk.brightenacademy.customer_demo.entity.Customer;
import lk.brightenacademy.customer_demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

    Optional<Customer> findByMobile(String name);
    Optional<Customer> findByNic(String name);

}
