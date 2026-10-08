package lk.brightenacademy.customer_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CustomerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerDemoApplication.class, args);
    }

}
