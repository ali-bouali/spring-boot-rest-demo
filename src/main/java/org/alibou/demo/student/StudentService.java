package org.alibou.demo.student;

import static org.alibou.demo.student.StudentSpecification.withEmail;
import static org.alibou.demo.student.StudentSpecification.withFirstname;
import static org.alibou.demo.student.StudentSpecification.withLastname;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.alibou.demo.address.AddressRepository;
import org.alibou.demo.common.exception.SubjectMaxLimitExceeded;
import org.alibou.demo.student.dto.StudentCreateRequest;
import org.alibou.demo.student.dto.StudentLightRequest;
import org.alibou.demo.student.dto.StudentMapper;
import org.alibou.demo.student.dto.StudentResponse;
import org.alibou.demo.student.dto.StudentUpdateRequest;
import org.alibou.demo.subject.Subject;
import org.alibou.demo.subject.SubjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;
  private final AddressRepository addressRepository;
  private final SubjectRepository subjectRepository;
  private final StudentMapper mapper;

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

  @Transactional
  public StudentResponse createStudent(StudentCreateRequest request) {
    Boolean existuser = studentRepository.existsByEmailOrUsername(request.email(),
        request.username());
    System.out.println("\n la variable  ++++++++++++++++++++++ " + existuser);
    if (existuser) {

      throw new EntityExistsException(
          "User already exists with email  " + request.email() + " or  username"
              + request.username());
    }
    addressRepository.findById(request.addressId()).orElseThrow(() -> new EntityNotFoundException(
        "The address is not find with id :" + request.addressId()));
    List<Subject> allSubjects = subjectRepository.findAllById(request.subjectIds());
    if (allSubjects.size() != request.subjectIds().size()) {
      throw new EntityNotFoundException("Not all the subjects are available...");
    }
    List<Subject> availableSubjects = allSubjects.stream()
        .peek(subject -> {
          if (subject.getCapacity() <= 0) {
            throw new SubjectMaxLimitExceeded(
                "Subject with ID " + subject.getId() + " has exceeded its capacity.");
          }
          subject.setCapacity(subject.getCapacity() - 1);
        })
        .collect(Collectors.toList());

    if (availableSubjects.size() != request.subjectIds().size()) {
      throw new EntityNotFoundException("Not all the subjects are available...");
    }

    Student student = mapper.toStudent(request);
    System.out.println("\n le student est ::::::" + student);
    Student savedStudent = studentRepository.save(student);
    subjectRepository.saveAll(availableSubjects);
    return mapper.toStudentResponse(savedStudent);
  }

  @Transactional
  public StudentResponse updateStudent(StudentUpdateRequest request) {
    Boolean existuser = studentRepository.existsByEmailOrUsernameAndIdNot(request.email(),
        request.username(), request.id());
    System.out.println("\n la variable  ++++++++++++++++++++++ " + existuser);
    if (existuser) {

      throw new EntityExistsException(
          "User already exists with email  " + request.email() + " or  username"
              + request.username());
    }
    addressRepository.findById(request.addressId()).orElseThrow(() -> new EntityNotFoundException(
        "The address is not find with id :" + request.addressId()));
    List<Subject> allSubjects = subjectRepository.findAllById(request.subjectIds());
    if (allSubjects.size() != request.subjectIds().size()) {
      throw new EntityNotFoundException("Not all the subjects are available...");
    }
    List<Subject> availableSubjects = allSubjects.stream()
        .peek(subject -> {
          if (subject.getCapacity() <= 0) {
            throw new SubjectMaxLimitExceeded(
                "Subject with ID " + subject.getId() + " has exceeded its capacity.");
          }
          subject.setCapacity(subject.getCapacity() - 1);
        })
        .collect(Collectors.toList());

    if (availableSubjects.size() != request.subjectIds().size()) {
      throw new EntityNotFoundException("Not all the subjects are available...");
    }

    Student student = mapper.toStudent(request);
    System.out.println("\n le student est ::::::" + student);
    Student savedStudent = studentRepository.save(student);
    subjectRepository.saveAll(availableSubjects);
    return mapper.toStudentResponse(savedStudent);
  }

  @Transactional
  public StudentResponse createStudentWithLessInformation(StudentLightRequest request) {
    Boolean existuser = studentRepository.existsByEmailOrUsername(request.getEmail(),
        request.getUsername());
    System.out.println("\n la variable  ++++++++++++++++++++++ " + existuser);
    if (existuser) {

      throw new EntityExistsException(
          "User already exists with email  " + request.getEmail() + " or  username"
              + request.getUsername());
    }
    Student student = mapper.toStudent(request);
    System.out.println("\n le student est ::::::" + student);
    Student savedStudent = studentRepository.save(student);
    return mapper.toStudentResponse(savedStudent);

  }


  StudentResponse findById(Integer id) {
    Student student = studentRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("the student not found with id  :" + id));
    return mapper.toStudentResponse(student);
  }

  @Transactional
  public void deleteStudentById(Integer id) {
    studentRepository.deleteById(id);
  }

  public Page<StudentResponse> search(String fn, String ln, String email, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Specification<Student> mySpec = withEmail(email);
    if (StringUtils.hasLength(fn)) {
      mySpec = mySpec.or(withFirstname(fn));
    }
    if (StringUtils.hasLength(ln)) {
      mySpec = mySpec.or(withLastname(ln));
    }
    Page<Student> students;

    if (!StringUtils.hasLength(fn) && !StringUtils.hasLength(ln) && !StringUtils.hasLength(email)) {
      students = studentRepository.findAll(pageable);

    } else {
      students = studentRepository.findAll(
          mySpec,
          pageable
      );
    }
    Page<StudentResponse> studentResponses = students.map(
        student -> mapper.toStudentResponse(student));

    return studentResponses;
  }


}