package org.alibou.demo;

import org.alibou.demo.address.Address;
import org.alibou.demo.student.Student;
import org.alibou.demo.student.StudentRepository;
import org.alibou.demo.student.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class SpringBootRestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(
            StudentService service
    ) {
        return args -> {
            // var s = service.findAll(0, 3);
            // System.out.println(s.toString());
        };
    }
}
