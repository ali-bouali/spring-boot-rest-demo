package org.alibou.demo.teacher.dto;


import org.alibou.demo.subject.Subject;
import org.alibou.demo.subject.dto.SubjectMapper;
import org.alibou.demo.teacher.Teacher;
import org.springframework.stereotype.Service;

@Service
public class TeacherMapper {
  public Teacher toTeacher(TeacherRequest request) {

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
      .firstname(request.getFirstname())
      .build();
  }


  public TeacherResponse toTeacherResponse(Teacher request, SubjectMapper mapper) {

    return TeacherResponse.builder()

      .id(request.getId())
      .lastname(request.getLastname())
      .firstname(request.getFirstname())
      .subject(mapper.toSubjectLightRequest(request.getSubject()))
      .build();


  }

  public TeacherLightRequest toTeacherLightRequest(Teacher request) {

    return TeacherLightRequest.builder()

      .id(request.getId())
      .lastname(request.getLastname())
      .firstname(request.getFirstname())
      .build();


  }


}
