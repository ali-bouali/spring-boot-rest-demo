package org.alibou.demo.content;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.alibou.demo.content.dto.ContentCreateRequest;
import org.alibou.demo.content.dto.ContentMapper;
import org.alibou.demo.content.dto.ContentResponse;
import org.alibou.demo.content.dto.ContentUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Builder
@AllArgsConstructor
@Service
public class ContentService {

  private final ContentMapper contentMapper;

  private final ContentRepository contentRepository;

  @Transactional
  public ContentResponse createContent(ContentCreateRequest request) {
    Content content = contentRepository.save(contentMapper.toContent(request));
    return contentMapper.toContentResponse(content);
  }

  @Transactional
  public ContentResponse updateContent(Integer id, ContentUpdateRequest request) {
    contentRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Content is not Found  " + id));
    Content content = contentRepository.save(contentMapper.toContent(request));
    return contentMapper.toContentResponse(content);
  }

  ContentResponse findById(Integer id) {
    Content content = contentRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("the content not found with id  :" + id));
    return contentMapper.toContentResponse(content);
  }

  @Transactional
  public void deleteContentById(Integer id) {
    contentRepository.deleteById(id);
  }
}