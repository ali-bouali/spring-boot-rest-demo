package org.alibou.demo.student;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.alibou.demo.student.dto.StudentCreateRequest;
import org.alibou.demo.student.dto.StudentLightRequest;
import org.alibou.demo.student.dto.StudentResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

  private final StudentService service;
  private final StudentRepository studentRepository;

  @PostMapping
  public ResponseEntity<StudentResponse> createStudent(
      @RequestBody @Valid StudentCreateRequest student
  ) {

    StudentResponse studentResponse = service.createStudent(student);
    return ResponseEntity.accepted().body(studentResponse);
  }

  @PutMapping("/{id}")
  public ResponseEntity<StudentResponse> updateStudent(@PathVariable("id") Integer id,
      @RequestBody @Valid StudentCreateRequest student
  ) {
    studentRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(" The student  is not found with  " + id));
    StudentResponse studentResponse = service.createStudent(student);
    return ResponseEntity.accepted().body(studentResponse);
  }

  @PostMapping("/special")

  public ResponseEntity<StudentResponse> createStudentWithLessInformation(
      @RequestBody StudentLightRequest student
  ) {
    StudentResponse resStudent = service.createStudentWithLessInformation(student);
    return ResponseEntity.accepted().body(resStudent);
  }

  @GetMapping("/{id}")
  public ResponseEntity<StudentResponse> getStudentById(@PathVariable Integer id) {
    StudentResponse studentResponse = service.findById(id);
    return ResponseEntity.ok(studentResponse);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteStudentById(@PathVariable Integer id) {
    service.deleteStudentById(id);
    return ResponseEntity.noContent().build();
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
