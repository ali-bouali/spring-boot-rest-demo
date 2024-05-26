package org.alibou.demo.student.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.alibou.demo.address.Address;
import org.alibou.demo.address.AddressRequest;

import java.util.List;

public record StudentRequest(
        Integer id,
        String firstname,
        String lastname,
        @NotNull(message = "1")
        @NotEmpty(message = "1")
        String username,
        @Email(message = "3")
        @NotNull(message = "4")
        @NotEmpty(message = "4")
        String email,
        Integer addressId,
        List<Integer> subjectIds
        /*,
        @NotBlank
        String ss, // "" --> false | " " --> false
        @NotEmpty // --> " " -> true
        List<String> s, // new ArrayList() --> Not null with size = 0 || is empty
        @NotEmpty
        String[] s2*/
) {}
