package org.alibou.demo.student.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Set;
import lombok.Builder;

@Builder
public record StudentRequest(
  Integer id,
  String firstname,
  String lastname,

  @NotNull(message = "Username cannot be null")
  @NotEmpty(message = "Username cannot be empty")
  String username,

  @Email(message = "Email should be valid")
  @NotNull(message = "Email cannot be null")
  @NotEmpty(message = "Email cannot be empty")
  String email,

  Integer addressId,
  Set<Integer> subjectIds

) {
}
