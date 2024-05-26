package org.alibou.demo.student;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.alibou.demo.student.dto.StudentRequest;
import org.alibou.demo.student.dto.StudentResponse;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.alibou.demo.student.StudentSpecification.withCreatedAt;
import static org.alibou.demo.student.StudentSpecification.withEmail;
import static org.alibou.demo.student.StudentSpecification.withFirstname;
import static org.alibou.demo.student.StudentSpecification.withLastname;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;
    private final SubjectRepository subjectRepository;


    /**
     * Save or update a student
     * @param request student object
     */
    @Transactional
    public void createStudent(StudentRequest request) {
        // validation is already done by the spring boot starter validation (nothing to do here)
        // check if address exists
        // addressRepository.findById(request.addressId()).orElseThrow(() -> new EntityNotFoundException("my message));

        // convert / map the student request to student entity
        Student student = mapper.toStudent(request);

        // save & return
        Student savedStudent = repository.save(student);

        List<Subject> availableSubjects = subjectRepository.findAllById(request.subjectIds());
        if (availableSubjects.size() != request.subjectIds().size()) {
            throw new EntityNotFoundException("Not all the subjects are available...");
        }

        for(Subject sub: availableSubjects) {
            List<Student> students = new ArrayList<>();
            students.add(savedStudent);
            sub.setStudents(students);
        }
        subjectRepository.saveAll(availableSubjects);
    }

    public Student findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("12"));
    }

    @Transactional
    public void updateAllStudents() {
        //repository.updateAllStudents("--");
        repository.findBySubjectsTeachersId(1);
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
        Page<Student> students = repository.findAll(pageable);
        return students;
    }

    public Page<Student> findAllByCreatedDate(LocalDateTime createdDate) {
        Pageable pageable = PageRequest.of(0, 5);
        Specification<Student> mySpec = withCreatedAt(createdDate)
                .and(
                        withEmail("sdsd")
                );
        Page<Student> students = repository.findAll(
                mySpec,
                pageable
        );
        return students;
    }

    public Page<Student> search(String fn, String ln, String email) {
        Pageable pageable = PageRequest.of(0, 5);
        Specification<Student> mySpec = withEmail(email);
        if (StringUtils.hasLength(fn)) {
            mySpec = mySpec.or(withFirstname(fn));
        }
        if (StringUtils.hasLength(ln)) {
            mySpec = mySpec.or(withLastname(ln));
        }
        Page<Student> students = repository.findAll(
                mySpec,
                pageable
        );
        return students;
    }
}
