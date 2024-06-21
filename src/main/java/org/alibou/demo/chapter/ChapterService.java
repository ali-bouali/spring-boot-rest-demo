package org.alibou.demo.chapter;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.alibou.demo.chapter.dto.ChapterCreateRequest;
import org.alibou.demo.chapter.dto.ChapterMapper;
import org.alibou.demo.chapter.dto.ChapterResponse;
import org.alibou.demo.chapter.dto.ChapterUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Builder
@AllArgsConstructor
@Service
public class ChapterService {

  private final ChapterMapper chapterMapper;

  private final ChapterRepository chapterRepository;

  @Transactional
  public ChapterResponse createChapter(ChapterCreateRequest request) {
    Chapter chapter = chapterRepository.save(chapterMapper.toChapter(request));
    return chapterMapper.toChapterResponse(chapter);
  }

  @Transactional
  public ChapterResponse updateChapter(Integer id, ChapterUpdateRequest request) {
    chapterRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Chapter is not Found  " + id));
    Chapter chapter = chapterRepository.save(chapterMapper.toChapter(request));
    return chapterMapper.toChapterResponse(chapter);
  }

  ChapterResponse findById(Integer id) {
    Chapter chapter = chapterRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("the chapter not found with id  :" + id));
    return chapterMapper.toChapterResponse(chapter);
  }

  @Transactional
  public void deleteChapterById(Integer id) {
    chapterRepository.deleteById(id);
  }
}
