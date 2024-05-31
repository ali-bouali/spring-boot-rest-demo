package org.alibou.demo.subject.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SubjectRequest {


  private Integer id;
  @NotNull(message = "name cannot be null")
  @NotEmpty(message = "name cannot be empty")
  private String name;

  private Integer capacity;
  private String description;

  private Set<Integer> students = new HashSet<>();

  private Set<Integer> teachers = new HashSet<>();

  private Set<Integer> chapters = new HashSet<>();
}
