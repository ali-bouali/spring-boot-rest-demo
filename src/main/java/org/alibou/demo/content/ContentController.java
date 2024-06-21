package org.alibou.demo.content;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.alibou.demo.content.dto.ContentCreateRequest;
import org.alibou.demo.content.dto.ContentResponse;
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
@RequestMapping("/contents")
@RequiredArgsConstructor
public class ContentController {

  private final ContentService service;
  private final ContentRepository contentRepository;

  @PostMapping
  public ResponseEntity<ContentResponse> createContent(
      @RequestBody @Valid ContentCreateRequest content
  ) {

    ContentResponse contentResponse = service.createContent(content);
    return ResponseEntity.accepted().body(contentResponse);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ContentResponse> updateContent(@PathVariable("id") Integer id,
      @RequestBody @Valid ContentCreateRequest content
  ) {
    contentRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(" The content  is not found with  " + id));
    ContentResponse contentResponse = service.createContent(content);
    return ResponseEntity.accepted().body(contentResponse);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ContentResponse> getContentById(@PathVariable Integer id) {
    ContentResponse contentResponse = service.findById(id);
    return ResponseEntity.ok(contentResponse);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteContentById(@PathVariable Integer id) {
    service.deleteContentById(id);
    return ResponseEntity.noContent().build();
  }


}
