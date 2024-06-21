package org.alibou.demo.student.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class StudentResponse {

  private Integer id;
  private String username;
  private String email;
  private String firstname;
  private String lastname;
}
