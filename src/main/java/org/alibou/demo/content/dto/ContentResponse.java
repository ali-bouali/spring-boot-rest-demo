package org.alibou.demo.content.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.alibou.demo.chapter.Chapter;

@Setter
@Getter
@Builder
public class ContentResponse {
  private Integer id ;
  private String content;
  private Chapter chapter;
}

