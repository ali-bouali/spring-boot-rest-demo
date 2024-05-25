package org.alibou.demo.student;

import jakarta.validation.Valid;
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
public class StudentController {


    @PostMapping
    public ResponseEntity<?> createStudent(
            @RequestBody @Valid StudentRequest student
    ) {
        return null;
    }

    @PostMapping
    // with special access
    public ResponseEntity<?> createStudentWithLessInformation(
            @RequestBody StudentLightRequest student
    ) {
        return null;
    }

    @GetMapping
    // Admin access
    public ResponseEntity<StudentResponse> findById() {
        return null;
    }

    @GetMapping
    // All access
    public ResponseEntity<StudentLightResponse> findLightStudentById() {
        return null;
    }

}
