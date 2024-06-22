package org.alibou.demo.teacher.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.alibou.demo.subject.dto.SubjectLightRequest;

@Setter
@Getter
@Builder
public class TeacherResponse {

  private Integer id;
  private String firstname;
  private String lastname;
  private SubjectLightRequest subject;
  private String speciality;
  private String email;
}
