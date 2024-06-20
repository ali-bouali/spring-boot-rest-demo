package org.alibou.demo.subject;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.alibou.demo.student.Student;
import org.alibou.demo.student.StudentRepository;
import org.alibou.demo.student.dto.StudentMapper;
import org.alibou.demo.subject.dto.SubjectLightRequest;
import org.alibou.demo.subject.dto.SubjectMapper;
import org.alibou.demo.subject.dto.SubjectRequest;
import org.alibou.demo.subject.dto.SubjectResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import static org.alibou.demo.subject.SubjectSpecification.withCapacity;
import static org.alibou.demo.subject.SubjectSpecification.withName;

@AllArgsConstructor
@Service
public class SubjectService {
  private final SubjectRepository subjectRepository;
  private final StudentMapper studentMapper;
  private final SubjectMapper subjectMapper;
  private final StudentRepository studentRepository;


  @Transactional
  public SubjectResponse createSubject(SubjectRequest request) {
    if (request.getId() != null) {
      studentRepository.findById(request.getId()).orElseThrow(() -> new EntityNotFoundException("Student is not found :" + (request.getId())));
    }
    Set<Student> availableStudents = studentRepository.findAllById(request.getStudents()).stream().collect(Collectors.toSet());
    if (availableStudents.size() != request.getStudents().size()) {
      throw new EntityNotFoundException("Not all the students are available...");
    }

    return subjectMapper.toSubjectResponse(subjectRepository.save(subjectMapper.toSubject(request)),studentMapper);

  }


  public SubjectResponse createSubjectWithLessInformation(SubjectLightRequest request) {


    if (request.getId() != null) {
      studentRepository.findById(request.getId()).orElseThrow(() -> new EntityNotFoundException("Student is not found :" + (request.getId())));
    }

    return subjectMapper.toSubjectResponse(subjectRepository.save(subjectMapper.toSubject(request)),studentMapper);
  }

  public Optional<SubjectResponse> findById(Integer id) {
    Optional<SubjectResponse> subjectResponse = subjectRepository.findById(id).map(subject -> subjectMapper.toSubjectResponse(subject, studentMapper));
    return subjectResponse;


  }


  public Page<SubjectResponse> search(Integer capacity, String name, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);

    Specification<Subject> mySpec = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

    if (capacity != null) {
      mySpec = mySpec.or(SubjectSpecification.withCapacity(capacity));
    }
    if (StringUtils.hasLength(name)) {
      mySpec = mySpec.or(SubjectSpecification.withName(name));
    }
    Page<Subject> subjects ;
    if(!StringUtils.hasLength(name)&&capacity == null) {
      subjects = subjectRepository.findAll(pageable);
    }
      else
      {
       subjects = subjectRepository.findAll(
          mySpec,
          pageable
      );
    }
    Page<SubjectResponse> sbjectResponse = subjects.map(subject -> subjectMapper.toSubjectResponse(subject, studentMapper));

    return sbjectResponse;
  }
}
