package org.alibou.demo.subject.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.alibou.demo.student.dto.StudentResponse;
@Getter
@Setter
@Builder
public class SubjectResponse {


  private Integer id;


  private String name;


  private Integer capacity;
  private String description;

  private Set<StudentResponse> students = new HashSet<>();

  //private Set<Integer> teachers = new HashSet<>();

 // private Set<Integer> chapters = new HashSet<>();
}
