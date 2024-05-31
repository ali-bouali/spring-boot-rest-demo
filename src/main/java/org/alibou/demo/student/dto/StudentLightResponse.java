package org.alibou.demo.student.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class StudentLightResponse {
  private String firstname;
  private String lastname;
}
