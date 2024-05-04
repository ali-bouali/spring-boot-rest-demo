package org.alibou.demo.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/resources")
public class MyFirstController {


    @PostMapping("/students")
    public ResponseEntity<Student> saveStudent(
            @RequestBody Student student
    ) {
        //Student savedStudent = service.saveToDB(student);
        URI studentLocation = UriComponentsBuilder
                .fromPath("/resources/students/{id}")
                .buildAndExpand(1)
                .toUri();
        // the location will be part of the Header --> Location
        return ResponseEntity.created(studentLocation).body(student);
    }

    @GetMapping("/students")
    public ResponseEntity<Student> getStudent() {
        Student s = new Student();
        s.setFirstname("Ali");
        s.setLastname("Bouali");
        return ResponseEntity.ok(s);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable("id") Integer id
    ) {
        Student s = new Student();
        s.setFirstname("Ali");
        s.setLastname("Bouali");
        return ResponseEntity.ok(s);
    }
    @GetMapping("/students/{name}")
    public ResponseEntity<Student> getStudentByName(
            @PathVariable("name") String name
    ) {
        Student s = new Student();
        s.setFirstname("Ali");
        s.setLastname("Bouali");
        return ResponseEntity.ok(s);
    }
}
