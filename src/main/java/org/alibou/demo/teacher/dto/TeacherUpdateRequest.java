package org.alibou.demo.teacher.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TeacherUpdateRequest {

  private Integer id;
  @NotNull(message = "firstname cannot be null")
  @NotEmpty(message = "firstname cannot be empty")
  private String firstname;
  @NotNull(message = "lastname cannot be null")
  @NotEmpty(message = "lastname cannot be empty")
  private String lastname;
  private Integer subjectId;
  @NotNull(message = "speciality cannot be null")
  @NotEmpty(message = "speciality cannot be empty")
  private String speciality;
  @NotNull(message = "password cannot be null")
  @NotEmpty(message = "password cannot be empty")
  private String password;
  @NotNull(message = "email cannot be null")
  @NotEmpty(message = "email cannot be empty")
  @Email(message = "Email should be valid")
  private String email;

}
