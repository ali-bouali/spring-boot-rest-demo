package org.alibou.demo.teacher.dto;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.alibou.demo.subject.Subject;
import org.alibou.demo.subject.dto.SubjectMapper;
import org.alibou.demo.teacher.Teacher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class TeacherMapper {
  private final PasswordEncoder passwordEncoder;
  public Teacher toTeacher(TeacherCreateRequest request) {

    Teacher.TeacherBuilder teacherBuilder = Teacher.builder()
        .lastname(request.getLastname())
        .firstname(request.getFirstname())
        .speciality(request.getSpeciality())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .subject(Subject.builder().id(request.getSubjectId()).build());

    return teacherBuilder.build();
  }

  public Teacher toTeacher(TeacherUpdateRequest request) {

    Teacher.TeacherBuilder teacherBuilder = Teacher.builder()
        .lastname(request.getLastname())
        .firstname(request.getFirstname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .speciality(request.getSpeciality())
        .subject(Subject.builder().id(request.getSubjectId()).build());

    if (request.getId() != null) {
      teacherBuilder.id(request.getId());
    }

    return teacherBuilder.build();
  }


  public Teacher toTeacher(TeacherLightRequest request) {

    return Teacher.builder()
        .lastname(request.getLastname())
        .username(request.getUsername())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .firstname(request.getFirstname())
        .build();
  }


  public TeacherResponse toTeacherResponse(Teacher request, SubjectMapper mapper) {
    TeacherResponse.TeacherResponseBuilder responseBuilder = TeacherResponse.builder()
        .speciality(request.getSpeciality())
        .id(request.getId())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .firstname(request.getFirstname());
    if (request.getSubject() != null) {
      responseBuilder.subject(mapper.toSubjectLightRequest(request.getSubject()))
      ;

    }
    return responseBuilder.build();
  }

  public TeacherLightResponse toTeacherLightRequest(Teacher request) {

    return TeacherLightResponse.builder()
        .id(request.getId())
        .lastname(request.getLastname())
        .firstname(request.getFirstname())
        .build();
  }


}
