package org.alibou.demo.subject.dto;

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
public class SubjectLightRequest {


  private  Integer id;
  @NotNull(message = "name cannot be null")
  @NotEmpty(message = "name cannot be empty")
  private String name;
  @NotNull(message = "capacity cannot be null")
  @NotEmpty(message = "capacity cannot be empty")
  private Integer capacity;
  private String description;

 }
