package org.alibou.demo.subject;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.alibou.demo.subject.dto.SubjectCreateRequest;
import org.alibou.demo.subject.dto.SubjectLightRequest;
import org.alibou.demo.subject.dto.SubjectResponse;
import org.alibou.demo.subject.dto.SubjectUpdateRequest;
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
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

  private final SubjectService service;

  @PostMapping
  public ResponseEntity<SubjectResponse> createSubject(
      @RequestBody @Valid SubjectCreateRequest subject
  ) {
    SubjectResponse subj = service.createSubject(subject);
    return ResponseEntity.accepted().body(subj);
  }

  @PutMapping("/{id}")
  public ResponseEntity<SubjectResponse> updateSubject(
      @RequestBody @Valid SubjectUpdateRequest subject
  ) {
    SubjectResponse subj = service.updateSubject(subject);
    return ResponseEntity.accepted().body(subj);
  }

  @PostMapping("/special")
  // with special access

  public ResponseEntity<SubjectResponse> createSubjectWithLessInformation(
      @RequestBody SubjectLightRequest subject
  ) {
    SubjectResponse subj = service.createSubjectWithLessInformation(subject);
    return ResponseEntity.accepted().body(subj);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SubjectResponse> getSubjectById(@PathVariable Integer id) {
    SubjectResponse subjectResponse = service.findById(id);
    return ResponseEntity.ok(subjectResponse);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteSubjectById(@PathVariable Integer id) {
    service.deleteSubjectById(id);
    return ResponseEntity.noContent().build();
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
