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
  private String username;
  private String firstname;
  private String lastname;
  @Email(message = "Email should be valid")
  @NotNull(message = "Email cannot be null")
  @NotEmpty(message = "Email cannot be empty")
  String email;
}
