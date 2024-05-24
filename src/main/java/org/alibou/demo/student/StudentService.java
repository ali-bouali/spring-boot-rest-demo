package org.alibou.demo.student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional; //askali
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;

  @Transactional
  public void updateAllStudents() {
    studentRepository.updateAllStudents("--");
    //repository.findBySubjectsTeachersId(1);
  }

  public Student findStudentByFirstnameContainingIgnoreCase(String s) {
    Optional<Student> optionalStudent = studentRepository.findTop1StudentByFirstnameContainingIgnoreCase(
        s);

    optionalStudent.ifPresent(student -> System.out.println(student.getFirstname()));

    return optionalStudent.orElse(null);
  }

  public List<Student> findStudentByLastnameContainingIgnoreCase(String s) {
    return studentRepository.findStudentByLastnameContainingIgnoreCase(s)
        .stream()
        .collect(Collectors.toList());
  }

  public List<StudentSubjectResponseProjection> findByComplexQueryString(String s) {
    return studentRepository.findByComplexQuery(s)
        .stream()
        .collect(Collectors.toList());
  }

  public void printStudentDetails(String param) {
    List<StudentSubjectResponseProjection> results = studentRepository.findByComplexQuery(param);

    results.forEach(student -> {
      System.out.println("First Name: " + student.getStudentFn());
      System.out.println("Last Name: " + student.getStudentLn());
      System.out.println("Subject Name: " + student.getSubName());
      System.out.println("Subject Description: " + student.getSubDesc());
      System.out.println("Teacher First Name: " + student.getTeacherFn());
      System.out.println("Teacher Last Name: " + student.getTeacherLn());
      System.out.println("-----");
    });
  }
  @Transactional(readOnly = true)
  public void findBySubjectsTeachersId(Integer id) {
    List<Student> results = studentRepository.findBySubjectsTeachersId(id);

    results.forEach(student -> {
      System.out.println("First Name: " + student.getFirstname());
      System.out.println("Last Name: " + student.getLastname());

      student.getSubjects().forEach(subject -> {
        System.out.println("Subject Name: " + subject.getName());
        System.out.println("Subject Description: " + subject.getDescription());
      });

      student.getSubjects().forEach(subject -> {
        subject.getTeachers().forEach(teacher -> {
          System.out.println("Teacher First Name: " + teacher.getFirstname());
          System.out.println("Teacher Last Name: " + teacher.getLastname());
        });
      });

      System.out.println("-----");
    });
  }

  public Page<Student> findAll(int page, int size) {
    Pageable pageable = PageRequest.of(page, size,
        Sort.by("username").descending()
            .and(
                Sort.by("lastname").ascending()
            )
            .and(
                Sort.by("email").descending()
            ));
    Page<Student> students = studentRepository.findAll(pageable);
    return students;
  }
}