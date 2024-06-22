package org.alibou.demo.student.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
public record StudentCreateRequest(

    @NotNull(message = "firstname cannot be null")
    @NotEmpty(message = "firstname cannot be empty")
    String firstname,
    @NotNull(message = "lastname cannot be null")
    @NotEmpty(message = "lastname cannot be empty")
    String lastname,

    @NotNull(message = "Username cannot be null")
    @NotEmpty(message = "Username cannot be empty")
    String username,
    @NotNull(message = "password cannot be null")
    @NotEmpty(message = "password cannot be empty")
    String password,

    @Email(message = "Email should be valid")
    @NotNull(message = "Email cannot be null")
    @NotEmpty(message = "Email cannot be empty")
    String email,
    @NotNull(message = "level cannot be null")
    @NotEmpty(message = "level cannot be empty")
    String level,
    Integer addressId,
    Set<Integer> subjectIds

) {

}
