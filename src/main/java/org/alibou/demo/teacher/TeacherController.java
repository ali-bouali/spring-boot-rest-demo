package org.alibou.demo.teacher;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.alibou.demo.teacher.dto.TeacherCreateRequest;
import org.alibou.demo.teacher.dto.TeacherLightRequest;
import org.alibou.demo.teacher.dto.TeacherResponse;
import org.alibou.demo.teacher.dto.TeacherUpdateRequest;
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
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

  private final TeacherService service;

  @PostMapping
  public ResponseEntity<TeacherResponse> createTeacher(
      @RequestBody @Valid TeacherCreateRequest teacher
  ) {
    TeacherResponse teacherResponse = service.createTeacher(teacher);
    return ResponseEntity.accepted().body(teacherResponse);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TeacherResponse> updateTeacher(@PathVariable("{id}") Integer id,
      @RequestBody @Valid TeacherUpdateRequest teacher
  ) {
    TeacherResponse teacherResponse = service.updateTeacher(teacher);
    return ResponseEntity.accepted().body(teacherResponse);
  }


  @PostMapping("/special")
  public ResponseEntity<?> createTeacherWithLessInformation(
      @RequestBody TeacherLightRequest teacher
  ) {
    TeacherResponse teacherResponse = service.createTeacherWithLessInformation(teacher);
    return ResponseEntity.accepted().body(teacherResponse);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TeacherResponse> getTeacherById(@PathVariable Integer id) {
    TeacherResponse teacherResponse = service.findById(id);
    return ResponseEntity.ok(teacherResponse);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteTeacherById(@PathVariable Integer id) {
    service.deleteTeacherById(id);
    ;
    return ResponseEntity.noContent().build();
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
