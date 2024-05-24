package org.alibou.demo;

import org.alibou.demo.student.StudentRepository;
import org.alibou.demo.student.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootRestDemoApplication {

  private final StudentRepository repo;  // Dependency declaration
  private final StudentService studentService;

  public SpringBootRestDemoApplication(StudentRepository repo, StudentService studentService) {
    this.repo = repo;  // Constructor injection
    this.studentService = studentService;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringBootRestDemoApplication.class, args);


  }

  @Bean
  public CommandLineRunner commandLineRunner() {
    return args -> {
      //studentService.updateAllStudents();
  studentService.findStudentByFirstnameContainingIgnoreCase("John").getFirstname() .toString();
     studentService.printStudentDetails("John");
      studentService.findBySubjectsTeachersId(1);
      var page = studentService.findAll(0, 3);
      System.out.println(page.toString());
      System.out.println("Total Elements: " + page.getTotalElements());
      System.out.println("Total Pages: " + page.getTotalPages());
      page.getContent().forEach(student -> {
        System.out.println("First Name: " + student.getFirstname());
        System.out.println("Last Name: " + student.getLastname());
        System.out.println("Username: " + student.getUsername());
        System.out.println("Email: " + student.getEmail());
        System.out.println("-----");
      });

    };
  }
}
