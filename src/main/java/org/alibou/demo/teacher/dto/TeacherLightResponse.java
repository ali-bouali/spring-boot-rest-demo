package org.alibou.demo.teacher.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TeacherLightResponse {

  private Integer id;


  private String firstname;

  private String lastname;


}
