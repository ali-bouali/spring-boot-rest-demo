package org.alibou.demo.student;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.alibou.demo.student.dto.StudentLightRequest;
import org.alibou.demo.student.dto.StudentRequest;
import org.alibou.demo.student.dto.StudentResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    service.createStudent(student);
    return ResponseEntity.accepted().body("success");
  }


   @PostMapping("/special")
  // with special access

  public ResponseEntity<?> createStudentWithLessInformation(
    @RequestBody StudentLightRequest student
  ) {
     service.createStudentWithLessInformation(student);
     return ResponseEntity.accepted().body("success");
  }

  @GetMapping("/{id}")
  public ResponseEntity<StudentResponse> getStudentById(@PathVariable Integer id) {
    // Assume studentService.findById(id) returns an Optional<Student>
    return service.findById(id)
      .map(student -> ResponseEntity.ok(student))
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/search")
  public Page<StudentResponse> searchStudents(
    @RequestParam(required = false) String firstname,
    @RequestParam(required = false) String lastname,
    @RequestParam(required = false) String email,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {

    return service.search(firstname, lastname, email, page, size);
  }
}
