package org.alibou.demo.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    @Transactional
    public void updateAllStudents() {
        //repository.updateAllStudents("--");
        repository.findBySubjectsTeachersId(1);
    }
}
