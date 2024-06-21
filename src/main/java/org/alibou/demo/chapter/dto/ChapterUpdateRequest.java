package org.alibou.demo.chapter.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ChapterUpdateRequest {

  private Integer id;
  @NotNull(message = "title cannot be null")
  @NotEmpty(message = "title cannot be empty")
  private String title;
  @NotNull(message = "subject cannot be null")
  @NotEmpty(message = "subject cannot be empty")
  private Integer subject;
  @NotNull(message = "contents cannot be null")
  @NotEmpty(message = "contents cannot be empty")
  private Set<Integer> contents = new HashSet<>();
}
