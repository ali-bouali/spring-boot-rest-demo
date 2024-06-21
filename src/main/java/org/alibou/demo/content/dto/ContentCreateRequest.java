package org.alibou.demo.content.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.alibou.demo.chapter.Chapter;

@Setter
@Getter
@Builder
public class ContentCreateRequest {
  @NotNull(message = "content cannot be null")
  @NotEmpty(message = "content cannot be empty")
  private String content;
  @NotNull(message = "chapter cannot be null")
  @NotEmpty(message = "chapter cannot be empty")
  private Integer chapter;
}
