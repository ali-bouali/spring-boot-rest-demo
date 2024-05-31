package org.alibou.demo.subject;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.alibou.demo.subject.dto.SubjectLightRequest;
import org.alibou.demo.subject.dto.SubjectRequest;
import org.alibou.demo.subject.dto.SubjectResponse;
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
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

  private final SubjectService service;

  @PostMapping
  public ResponseEntity<?> createSubject(
    @RequestBody @Valid SubjectRequest subject
  ) {
    service.createSubject(subject);
    return ResponseEntity.accepted().body("success");
  }


   @PostMapping("/special")
  // with special access

  public ResponseEntity<?> createSubjectWithLessInformation(
    @RequestBody SubjectLightRequest subject
  ) {
     service.createSubjectWithLessInformation(subject);
     return ResponseEntity.accepted().body("success");
  }

  @GetMapping("/{id}")
  public ResponseEntity<SubjectResponse> getSubjectById(@PathVariable Integer id) {
    return service.findById(id)
      .map(subject -> ResponseEntity.ok(subject))
      .orElseGet(() -> ResponseEntity.notFound().build());
  }



  @GetMapping("/search")
  public Page<SubjectResponse> searchSubjects(
    @RequestParam(required = false)
    Integer capacity,
    @RequestParam(required = false) String name,

    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {

    return service.search(capacity, name, page, size);
  }
}
