package org.alibou.demo.student.dto;


import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.alibou.demo.address.Address;
import org.alibou.demo.student.Student;
import org.alibou.demo.subject.Subject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class StudentMapper {
  private final PasswordEncoder passwordEncoder;
  public Student toStudent(StudentCreateRequest request) {

    return Student.builder()
        .firstname(request.firstname())
        .username(request.username())
        .lastname(request.lastname())
        .password(passwordEncoder.encode(request.password()))
        .level(request.level())
        .email(request.email())
        .subjects(request.subjectIds().stream().map(id -> Subject.builder().id(id).build())
            .collect(Collectors.toSet()))
        .address(Address.builder().id(request.addressId()).build())
        .build();
  }

  public Student toStudent(StudentUpdateRequest request) {

    return Student.builder()
        .id(request.id())
        .level(request.level())
        .firstname(request.firstname())
        .username(request.username())
        .lastname(request.lastname())
        .password(passwordEncoder.encode(request.password()))        .email(request.email())
        .subjects(request.subjectIds().stream().map(id -> Subject.builder().id(id).build())
            .collect(Collectors.toSet()))
        .address(Address.builder().id(request.addressId()).build())
        .build();
  }

  public Student toStudent(StudentLightRequest request) {

    return Student.builder()
        .username(request.getUsername())
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .password(passwordEncoder.encode(request.getPassword()))
        .email(request.getEmail())
        .build();
  }


  public StudentResponse toStudentResponse(Student request) {

    return StudentResponse.builder().username(request.getUsername())
        .id(request.getId())
        .level(request.getLevel())
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail()).build();


  }

  public StudentLightRequest toStudentLightRequest(Student request) {

    return StudentLightRequest.builder()
        .username(request.getUsername())
        .firstname(request.getFirstname())

        .lastname(request.getLastname()).build();


  }

}