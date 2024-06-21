package org.alibou.demo.teacher.dto;


import org.alibou.demo.subject.Subject;
import org.alibou.demo.subject.dto.SubjectMapper;
import org.alibou.demo.teacher.Teacher;
import org.springframework.stereotype.Service;

@Service
public class TeacherMapper {

  public Teacher toTeacher(TeacherCreateRequest request) {

    Teacher.TeacherBuilder teacherBuilder = Teacher.builder()
        .lastname(request.getLastname())
        .firstname(request.getFirstname())

        .subject(Subject.builder().id(request.getSubjectId()).build());

    return teacherBuilder.build();
  }

  public Teacher toTeacher(TeacherUpdateRequest request) {

    Teacher.TeacherBuilder teacherBuilder = Teacher.builder()
        .lastname(request.getLastname())
        .firstname(request.getFirstname())

        .subject(Subject.builder().id(request.getSubjectId()).build());

    if (request.getId() != null) {
      teacherBuilder.id(request.getId());
    }

    return teacherBuilder.build();
  }


  public Teacher toTeacher(TeacherLightRequest request) {

    return Teacher.builder()
        .id(request.getId())
        .lastname(request.getLastname())
        .username(request.getUsername())
        .firstname(request.getFirstname())
        .build();
  }


  public TeacherResponse toTeacherResponse(Teacher request, SubjectMapper mapper) {
    TeacherResponse.TeacherResponseBuilder responseBuilder = TeacherResponse.builder()

        .id(request.getId())
        .lastname(request.getLastname())
        .firstname(request.getFirstname());
    if (request.getSubject() != null) {
      responseBuilder.subject(mapper.toSubjectLightRequest(request.getSubject()))
      ;

    }
    return responseBuilder.build();
  }

  public TeacherLightRequest toTeacherLightRequest(Teacher request) {

    return TeacherLightRequest.builder()

        .id(request.getId())
        .lastname(request.getLastname())
        .firstname(request.getFirstname())
        .build();


  }


}
