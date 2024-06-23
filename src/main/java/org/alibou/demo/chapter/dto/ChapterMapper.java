package org.alibou.demo.chapter.dto;

import jakarta.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.alibou.demo.chapter.Chapter;
import org.alibou.demo.content.Content;
import org.alibou.demo.content.ContentRepository;
import org.alibou.demo.content.dto.ContentMapper;
import org.alibou.demo.student.dto.StudentMapper;
import org.alibou.demo.subject.Subject;
import org.alibou.demo.subject.SubjectRepository;
import org.alibou.demo.subject.dto.SubjectMapper;
import org.springframework.stereotype.Service;

@Setter
@Getter
@Builder
@Data
@Service
public class ChapterMapper {
  private final ContentRepository contentRepository;
  private final ContentMapper contentMapper;
  private final SubjectRepository subjectRepository;
  private final SubjectMapper subjectMapper;
  private final StudentMapper studentMapper;

  public Chapter toChapter(ChapterCreateRequest request) {
    Chapter.ChapterBuilder chapterBuilder =
        Chapter.builder()
            .title(request.getTitle());
    if (request.getSubject() != null) {
      chapterBuilder.subject(Subject.builder().id(request.getSubject()).build());
    }
//    if (request.getContents() != null) {
//      chapterBuilder.contents(
//          request.getContents().stream()
//              .map(id -> Content.builder().id(id).build())
//              .collect(Collectors.toSet())
//      );
    //}

    return chapterBuilder.build();
  }


  public Chapter toChapter(ChapterUpdateRequest request) {
    Chapter.ChapterBuilder chapterBuilder =
        Chapter.builder()
            .id(request.getId())
            .title(request.getTitle());

    if (request.getSubject() != null) {
      chapterBuilder.subject(Subject.builder().id(request.getSubject()).build());
    }
//    if (request.getContents() != null) {
//      chapterBuilder.contents (
//          request.getContents().stream()
//              .map(id -> Content.builder().id(id).build())
//              .collect(Collectors.toSet())
//      );
//    }
    return chapterBuilder.build();
  }

  public ChapterResponse toChapterResponse(Chapter request) {
    Subject subject = null;
    if (request.getSubject() != null) {
      subject = subjectRepository.findById(request.getSubject().getId())
          .orElseThrow(() -> new EntityNotFoundException(
              "Subject is not found " + request.getSubject().getId()));
    }

    Set<Content> contents = new HashSet<>();
    if (request.getContents() != null) {
      List<Integer> contentIds = request.getContents().stream()
          .map(Content::getId)
          .collect(Collectors.toList());

      contents = new HashSet<>(contentRepository.findAllById(contentIds));
    }

    return ChapterResponse.builder()
        .id(request.getId())
        .title(request.getTitle())
        .subject(subject != null ? subjectMapper.toSubjectResponse(subject, studentMapper) : null)
        .contents( contents.stream().map(content ->contentMapper.toContentResponse(content) ).collect(
            Collectors.toSet()) )
        .build();
  }


}