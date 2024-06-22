package org.alibou.demo.student.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentLightRequest {

  @Email(message = "Email should be valid")
  @NotEmpty(message = "Email cannot be empty")
   String email;
  @NotEmpty(message = "username cannot be empty")
  private String username;
  @NotEmpty(message = "firstname cannot be empty")
  private String firstname;
  @NotEmpty(message = "lastname cannot be empty")
  private String lastname;
  @NotEmpty(message = "password cannot be empty")
  private String password;
}
