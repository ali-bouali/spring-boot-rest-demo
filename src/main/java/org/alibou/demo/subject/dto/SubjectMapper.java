package org.alibou.demo.subject.dto;

import java.util.stream.Collectors;
import org.alibou.demo.student.Student;
import org.alibou.demo.student.dto.StudentMapper;
import org.alibou.demo.subject.Subject;
import org.springframework.stereotype.Service;

@Service
public class SubjectMapper {


  public Subject toSubject(SubjectRequest request) {

    Subject.SubjectBuilder subjectBuilder = Subject.builder()
      .name(request.getName())
      .capacity(request.getCapacity())
      .description(request.getDescription())
      .students(request.getStudents().stream()
        .map(studentId -> Student.builder().id(studentId).build())
        .collect(Collectors.toSet()));

    if (request.getId() != null) {
      subjectBuilder.id(request.getId());
    }

    return subjectBuilder.build();
  }






  public Subject toSubject(SubjectLightRequest request) {

    return Subject.builder()
      .id(request.getId())
      .name(request.getName())
      .capacity(request.getCapacity())
      .description(request.getDescription())
      .build();
  }


  public SubjectResponse toSubjectResponse(Subject request, StudentMapper mapper) {

    return SubjectResponse.builder()

      .id(request.getId())
      .name(request.getName())
      .capacity(request.getCapacity())
      .description(request.getDescription())
      .students(request.getStudents().stream().map(student -> mapper.toStudentResponse(student)).collect(Collectors.toSet()))
      .build();


  }

  public SubjectLightRequest toSubjectLightRequest(Subject request) {

    return SubjectLightRequest.builder()

      .id(request.getId())
      .name(request.getName())
      .capacity(request.getCapacity())
      .description(request.getDescription())
      .build();


  }

}
