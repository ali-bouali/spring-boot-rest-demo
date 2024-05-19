package org.alibou.demo.student;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static org.alibou.demo.student.StudentSpecification.withCreatedAt;
import static org.alibou.demo.student.StudentSpecification.withEmail;
import static org.alibou.demo.student.StudentSpecification.withFirstname;
import static org.alibou.demo.student.StudentSpecification.withLastname;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

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
