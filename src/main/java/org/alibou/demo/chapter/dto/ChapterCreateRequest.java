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
public class ChapterCreateRequest {

  @NotNull(message = "title cannot be null")
  @NotEmpty(message = "title cannot be empty")
  private String title;
  @NotNull(message = "ID cannot be null")
   private Integer subject;
 }
