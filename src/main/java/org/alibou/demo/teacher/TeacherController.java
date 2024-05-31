package org.alibou.demo.teacher;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.alibou.demo.teacher.dto.TeacherLightRequest;
import org.alibou.demo.teacher.dto.TeacherRequest;
import org.alibou.demo.teacher.dto.TeacherResponse;
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
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

  private final TeacherService service;
  @PostMapping
  public ResponseEntity<?> createTeacher(
    @RequestBody @Valid TeacherRequest teacher
  ) {
    service.createTeacher(teacher);
    return ResponseEntity.accepted().body("success");
  }


   @PostMapping("/special")
  // with special access

  public ResponseEntity<?> createTeacherWithLessInformation(
    @RequestBody TeacherLightRequest teacher
  ) {
     service.createTeacherWithLessInformation(teacher);
     return ResponseEntity.accepted().body("success");
  }

  @GetMapping("/{id}")
  public ResponseEntity<TeacherResponse> getTeacherById(@PathVariable Integer id) {
    // Assume teacherService.findById(id) returns an Optional<Teacher>
    return service.findById(id)
      .map(teacher -> ResponseEntity.ok(teacher))
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/search")
  public Page<TeacherResponse> searchTeachers(
    @RequestParam(required = false) String firstname,
    @RequestParam(required = false) String lastname,
    @RequestParam(required = false) Integer id,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {

    return service.search(firstname, lastname, id, page, size);
  }
}
