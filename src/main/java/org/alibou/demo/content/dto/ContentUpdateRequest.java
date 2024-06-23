package org.alibou.demo.content.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.alibou.demo.chapter.Chapter;
@Setter
@Getter
@Builder
public class ContentUpdateRequest {
  private Integer id ;
  @NotNull(message = "content cannot be null")
  @NotEmpty(message = "content cannot be empty")
  private String content;
  @NotNull(message = "chapter cannot be null")
  private Chapter chapter;
}



