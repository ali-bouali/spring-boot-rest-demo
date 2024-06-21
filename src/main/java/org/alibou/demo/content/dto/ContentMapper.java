package org.alibou.demo.content.dto;

import lombok.AllArgsConstructor;
import org.alibou.demo.chapter.Chapter;
import org.alibou.demo.chapter.dto.ChapterMapper;
import org.alibou.demo.content.Content;
import org.alibou.demo.student.dto.StudentMapper;
import org.alibou.demo.subject.SubjectRepository;
import org.alibou.demo.subject.dto.SubjectMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ContentMapper {
  private final ChapterMapper chapterMapper ;
  private final SubjectRepository subjectRepository;
  private final SubjectMapper subjectMapper;
  private final StudentMapper studentMapper;

  public Content toContent(ContentCreateRequest request) {
    Content.ContentBuilder contentBuilder =
        Content.builder()
            .content(request.getContent())
            .chapter(Chapter.builder().id(request.getChapter()).build());

    return contentBuilder.build();
  }


  public Content toContent(ContentUpdateRequest request) {
    Content.ContentBuilder contentBuilder =
        Content.builder()
            .content(request.getContent())
            .id(request.getId())
            .chapter(request.getChapter());

    return contentBuilder.build();
  }


  public ContentResponse toContentResponse(Content request) {
    ContentResponse.ContentResponseBuilder contentBuilder =
        ContentResponse.builder()
            .content(request.getContent())
            .id(request.getId())
            .chapter(request.getChapter());

    return contentBuilder.build();

  }
}