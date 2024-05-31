package org.alibou.demo.subject.dto;

import java.util.HashSet;
import java.util.Set;
import org.alibou.demo.student.dto.StudentLightResponse;
import org.alibou.demo.student.dto.StudentResponse;

public class SubjectLightResponse {

  private Integer id;

  private String name;

  private Integer capacity;
  private String description;

  private Set<StudentLightResponse> students = new HashSet<>();

  //private Set<Integer> teachers = new HashSet<>();

 // private Set<Integer> chapters = new HashSet<>();
}
