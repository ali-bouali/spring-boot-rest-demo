package org.alibou.demo.teacher;

import static org.alibou.demo.teacher.TeacherSpecification.withLastname;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.alibou.demo.student.StudentRepository;
import org.alibou.demo.student.dto.StudentMapper;
import org.alibou.demo.subject.Subject;
import org.alibou.demo.subject.SubjectRepository;
import org.alibou.demo.subject.dto.SubjectMapper;
import org.alibou.demo.teacher.dto.TeacherCreateRequest;
import org.alibou.demo.teacher.dto.TeacherLightRequest;
import org.alibou.demo.teacher.dto.TeacherMapper;
import org.alibou.demo.teacher.dto.TeacherResponse;
import org.alibou.demo.teacher.dto.TeacherUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@AllArgsConstructor
@Service
public class TeacherService {

  private final TeacherRepository teacherRepository;
  private final StudentMapper studentMapper;
  private final TeacherMapper teacherMapper;
  private final StudentRepository studentRepository;
  private final SubjectRepository subjectRepository;
  private final SubjectMapper subjectMapper;
  private <T> T findEntityById(JpaRepository<T, Integer> repository, Integer id, String entityName) {
    return repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(entityName + " Not Found with Id : " + id));
  }
  @Transactional
  public TeacherResponse registerTeacher(TeacherCreateRequest request) {
   Optional<Teacher> exist= teacherRepository.findByEmail(request.getEmail());
     if(exist.isPresent())
     {
       throw new EntityExistsException("a user already exists with this mail " + request.getEmail());

     }
    Subject subject = findEntityById(subjectRepository,request.getSubjectId(),"Subject ");
    Teacher teacher  = teacherMapper.toTeacher(request) ;
    teacher.setSubject(subject);
    teacher= teacherRepository.save(teacher);
    return teacherMapper.toTeacherResponse(teacher,
        subjectMapper);

  }

  @Transactional
  public TeacherResponse updateTeacher(TeacherUpdateRequest request) {

    Optional<Teacher> exist= teacherRepository.findByEmailAndIdNot(request.getEmail(),
        request.getId());
    if(exist.isPresent())
    {
      throw new EntityExistsException("a user already exists with this mail " + request.getEmail());

    }
    Teacher teacher  = teacherMapper.toTeacher(request) ;
    if (request.getId() != null) {
      Subject subject = findEntityById(subjectRepository,request.getSubjectId(),"Subject ");
      teacher.setSubject(subject);
    }
       teacher= teacherRepository.save(teacher);
    return teacherMapper.toTeacherResponse(teacher,
        subjectMapper);

  }


  public TeacherResponse registerTeacherWithLessInformation(TeacherLightRequest request) {
    Optional<Teacher> exist= teacherRepository.findByEmail(request.getEmail());
    if(exist.isPresent())
    {
      throw new EntityExistsException("a user already exists with this mail " + request.getEmail());

    }
    return teacherMapper.toTeacherResponse(teacherRepository.save(teacherMapper.toTeacher(request)),
        subjectMapper);
  }

  public TeacherResponse findById(Integer id) {
    TeacherResponse teacherResponse = teacherRepository.findById(id)
        .map(teacher -> teacherMapper.toTeacherResponse(teacher, subjectMapper))
        .orElseThrow(() -> new EntityNotFoundException("The teacher is not found with Id: " + id));
    return teacherResponse;


  }

  @Transactional
  public void deleteTeacherById(Integer id) {
    teacherRepository.deleteById(id);
  }

  public Page<TeacherResponse> search(String firstname, String lastname, Integer subjectId,
      int page, int size) {
    Pageable pageable = PageRequest.of(page, size);

    Specification<Teacher> mySpec = withLastname(
        lastname);//(root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

    if (subjectId != null) {
      mySpec = mySpec.or(TeacherSpecification.withSubjectId(subjectId));
    }
    if (StringUtils.hasLength(lastname)) {
      mySpec = mySpec.or(withLastname(lastname));
    }
    if (StringUtils.hasLength(firstname)) {
      mySpec = mySpec.or(TeacherSpecification.withFirstname(firstname));
    }
    Page<Teacher> teachers;
    if (!StringUtils.hasLength(lastname) && !StringUtils.hasLength(firstname)
        && subjectId == null) {
      teachers = teacherRepository.findAll(pageable);
    } else {
      teachers = teacherRepository.findAll(mySpec, pageable);
    }
    Page<TeacherResponse> sbjectResponse = teachers.map(
        Teacher -> teacherMapper.toTeacherResponse(Teacher, subjectMapper));

    return sbjectResponse;
  }
}
