package org.alibou.demo;

import java.util.stream.Collectors;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import  org.alibou.demo.student.StudentRepository;
@SpringBootApplication
public class SpringBootRestDemoApplication {
  private final StudentRepository repo;  // Dependency declaration

  public SpringBootRestDemoApplication(StudentRepository repo) {
    this.repo = repo;  // Constructor injection
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringBootRestDemoApplication.class, args);




  }
  @Bean
  public CommandLineRunner commandLineRunner() {
      return args -> {
      repo.findBySubjectsName("Mathematics");
        System.out.println("\n here"+repo.findByEmailContainingIgnoreCase("M").stream().map(student -> student.getFirstname()).collect(
            Collectors.toSet()));
        repo.findByLastnameAndFirstname("tt","tt");

    };
  }
}
