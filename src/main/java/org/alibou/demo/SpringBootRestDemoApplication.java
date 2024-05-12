package org.alibou.demo;

import org.alibou.demo.address.Address;
import org.alibou.demo.student.Student;
import org.alibou.demo.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootRestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestDemoApplication.class, args);
    }

    // @Bean
    public CommandLineRunner init(
            StudentRepository repo
    ) {
        return args -> {
            Student s = new Student();
            s.setFirstname("Ali");
            s.setLastname("Bouali");
            s.setUsername("alibou");
            s.setEmail("alibou");
            Address adr = new Address();
            adr.setId(1); // optional
            adr.setCity("sdsds");
            adr.setStudent(s);
            // adr info

            s.setAddress(adr);

            repo.save(s);

            // repo.delete(s);
        };
    }
}
