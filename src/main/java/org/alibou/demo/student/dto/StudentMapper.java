package org.alibou.demo.student.dto;


import java.util.stream.Collectors;
import org.alibou.demo.address.Address;
import org.alibou.demo.student.Student;
import org.alibou.demo.subject.Subject;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

  public Student toStudent(StudentRequest request) {

    return Student.builder()
      .id(request.id())
      .firstname(request.firstname())
      .username(request.username())
      .lastname(request.lastname())
      .email(request.email())
      .subjects(request.subjectIds().stream().map(id -> Subject.builder().id(id).build()).collect(Collectors.toSet()))
      .address(Address.builder().id(request.addressId()).build())
      .build();


  }


  public Student toStudent(StudentLightRequest request) {

    return Student.builder()
      .username(request.getUsername())
      .firstname(request.getFirstname())
      .lastname(request.getLastname())
      .email(request.getEmail())
      .build();
  }


  public StudentResponse toStudentResponse(Student request) {

    return StudentResponse.builder().username(request.getUsername())
      .firstname(request.getFirstname())
      .lastname(request.getLastname())
      .email(request.getEmail()).build();


  }

  public StudentLightRequest toStudentLightRequest(Student request) {

    return StudentLightRequest.builder()
      .username(request.getUsername())
      .firstname(request.getFirstname())

      .lastname(request.getLastname()) .build();



  }

}