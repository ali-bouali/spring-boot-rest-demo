package org.alibou.demo.subject.dto;

import java.util.Set;
import java.util.stream.Collectors;
import org.alibou.demo.student.Student;
import org.alibou.demo.student.dto.StudentMapper;
import org.alibou.demo.subject.Subject;
import org.springframework.stereotype.Service;

@Service
public class SubjectMapper {


  public Subject toSubject(SubjectCreateRequest request) {
    Subject.SubjectBuilder subjectBuilder = Subject.builder()
        .name(request.getName())
        .capacity(request.getCapacity())
        .description(request.getDescription())
        .students((Set<Student>) request.getStudents().stream()
            .map(studentId -> Student.builder().id(studentId).build())
            .collect(Collectors.toSet()));
    return subjectBuilder.build();
  }


  public Subject toSubject(SubjectUpdateRequest request) {
    Subject.SubjectBuilder subjectBuilder = Subject.builder()
        .name(request.getName())
        .capacity(request.getCapacity())
        .description(request.getDescription())
        .students((Set<Student>) request.getStudents().stream()
            .map(studentId -> Student.builder().id(studentId).build())
            .collect(Collectors.toSet()));

    if (request.getId() != null) {
      subjectBuilder.id(request.getId());
    }

    return subjectBuilder.build();
  }


  public Subject toSubject(SubjectLightRequest request) {

    return Subject.builder()
               .name(request.getName())
        .capacity(request.getCapacity())
        .description(request.getDescription())
        .build();
  }


  public SubjectResponse toSubjectResponse(Subject request, StudentMapper mapper) {

    SubjectResponse.SubjectResponseBuilder responseBuilder = SubjectResponse.builder()
        .id(request.getId())
        .name(request.getName())
        .capacity(request.getCapacity())
        .description(request.getDescription());

    if (request.getStudents() != null) {
      responseBuilder.students(
          request.getStudents().stream()
              .map(mapper::toStudentResponse)
              .collect(Collectors.toSet())
      );
    }

    return responseBuilder.build();
  }


  public SubjectLightRequest toSubjectLightRequest(Subject request) {

    return SubjectLightRequest.builder()

       // .id(request.getId())
        .name(request.getName())
        .capacity(request.getCapacity())
        .description(request.getDescription())
        .build();


  }

}
