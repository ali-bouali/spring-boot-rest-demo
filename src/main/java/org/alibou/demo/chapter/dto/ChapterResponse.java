package org.alibou.demo.chapter.dto;

import java.util.HashSet;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.alibou.demo.content.dto.ContentResponse;
import org.alibou.demo.subject.dto.SubjectResponse;

@Setter
@Getter
@Builder
public class ChapterResponse {

  private Integer id;
  private String title;
  private SubjectResponse subject;
  private Set<ContentResponse> contents = new HashSet<>();
}
