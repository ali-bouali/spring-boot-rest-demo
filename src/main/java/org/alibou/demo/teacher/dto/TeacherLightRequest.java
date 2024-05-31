package org.alibou.demo.teacher.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TeacherLightRequest {


  private Integer id;

  @NotNull(message = "firstname cannot be null")
  @NotEmpty(message = "firstname cannot be empty")
  private String firstname;
  @NotNull(message = "lastname cannot be null")
  @NotEmpty(message = "lastname cannot be empty")
  private String lastname;


}
