package org.alibou.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/resources")
public class MyFirstController {


    // http://localhost:8080/api/v1/dassd/student
    @PostMapping("/students")
    //@RequestMapping(value = "/students", method = RequestMethod.POST)
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

    @PostMapping("/students/special")
    public ResponseEntity<Student> saveSpecialStudent(
            @RequestBody Student student,
            @RequestHeader("X-SOURCE-FROM") String from
    ) {
        if (!"mobile-app".equals(from)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    //@GetMapping("/students")
    public ResponseEntity<Student> getStudent() {
        Student s = new Student();
        s.setFirstname("Ali");
        s.setLastname("Bouali");
        return ResponseEntity.ok(s);
    }

    // http://localhost:8080/api/v1/dassd/students/1
    // POST http://localhost:8080/api/v1/dassd/students/1/courses/2
    @GetMapping("/students/{id-student}/courses/{course-id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable(name = "id-student") Integer idStudent
    ) {
        Student s = new Student();
        s.setFirstname("Ali");
        s.setLastname("Bouali");
        return ResponseEntity.ok(s);
    }

    // http://localhost:8080/api/v1/dassd/students?id-student=1&id-course=2
    // http://localhost:8080/api/v1/dassd/students
    @GetMapping("/students")
    public ResponseEntity<Student> getStudentById2(
            @RequestParam(value = "id-student", required = false, defaultValue = "1") Integer idStudent,
            @RequestParam(value = "id-course", required = false, defaultValue = "2") Integer idCourse
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


    @DeleteMapping
    public ResponseEntity<?> delete() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("");
    }
}
