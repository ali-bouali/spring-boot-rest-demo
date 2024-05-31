package org.alibou.demo.teacher;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.alibou.demo.student.StudentRepository;
import org.alibou.demo.student.dto.StudentMapper;
import org.alibou.demo.subject.SubjectRepository;
import org.alibou.demo.subject.dto.SubjectMapper;
import org.alibou.demo.teacher.dto.TeacherLightRequest;
import org.alibou.demo.teacher.dto.TeacherMapper;
import org.alibou.demo.teacher.dto.TeacherRequest;
import org.alibou.demo.teacher.dto.TeacherResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import static org.alibou.demo.teacher.TeacherSpecification.withLastname;

@AllArgsConstructor
@Service
public class TeacherService {
  private final TeacherRepository teacherRepository;
  private final StudentMapper studentMapper;
  private final TeacherMapper teacherMapper;
  private final StudentRepository studentRepository;
  private final SubjectRepository subjectRepository;
  private final SubjectMapper subjectMapper;

  @Transactional
  public void createTeacher(TeacherRequest request) {
    if (request.getId() != null) {
      subjectRepository.findById(request.getSubjectId()).orElseThrow(() -> new EntityNotFoundException("Subjectis not found :" + (request.getSubjectId())));
    }

    teacherRepository.save(teacherMapper.toTeacher(request));

  }


  public void createTeacherWithLessInformation(TeacherLightRequest request) {


    teacherRepository.save(teacherMapper.toTeacher(request));
  }

  public Optional<TeacherResponse> findById(Integer id) {
    Optional<TeacherResponse> TeacherResponse = teacherRepository.findById(id).map(teacher -> teacherMapper.toTeacherResponse(teacher, subjectMapper));
    return TeacherResponse;


  }


  public Page<TeacherResponse> search(String firstname, String lastname, Integer subjectId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);

    Specification<Teacher> mySpec = withLastname(lastname);//(root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

    if (subjectId != null) {
      mySpec = mySpec.or(TeacherSpecification.withSubjectId(subjectId));
    }
    if (StringUtils.hasLength(lastname)) {
      mySpec = mySpec.or(withLastname(lastname));
    }
    if (StringUtils.hasLength(firstname)) {
      mySpec = mySpec.or(TeacherSpecification.withFirstname(firstname));
    }
    System.out.println("\n tttttttttttttttttttttttt       " +mySpec);
    Page<Teacher> Teachers = teacherRepository.findAll(
      mySpec,
      pageable
    );
    Page<TeacherResponse> sbjectResponse = Teachers.map(Teacher -> teacherMapper.toTeacherResponse(Teacher, subjectMapper));

    return sbjectResponse;
  }
}
