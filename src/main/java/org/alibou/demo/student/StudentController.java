package org.alibou.demo.student;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.alibou.demo.student.dto.StudentLightRequest;
import org.alibou.demo.student.dto.StudentLightResponse;
import org.alibou.demo.student.dto.StudentRequest;
import org.alibou.demo.student.dto.StudentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PostMapping
    public ResponseEntity<?> createStudent(
            @RequestBody @Valid StudentRequest student
    ) {
        this.service.createStudent(student);
        return ResponseEntity.accepted().body("success");
    }

    // /search/by/name
    // /search/by/age
    // /search/by/admin/address --> n7ebbha tkoun accessible ken lel admin




    // @PostMapping
    // with special access
    public ResponseEntity<?> createStudentWithLessInformation(
            @RequestBody StudentLightRequest student
    ) {
        return null;
    }

    @GetMapping
    // Admin access
    public ResponseEntity<StudentResponse> findById() {
        StudentResponse std = StudentResponse.builder()
                .firstname("sdfsdf")
                .lastname("asasd")
                .email("asasd")
                .build();
        return ResponseEntity.ok(std);
    }

    //@GetMapping
    // All access
    public ResponseEntity<StudentLightResponse> findLightStudentById() {
        return null;
    }

}
