package org.alibou.demo.student;

import org.alibou.demo.address.Address;
import org.alibou.demo.student.dto.StudentLightRequest;
import org.alibou.demo.student.dto.StudentRequest;
import org.alibou.demo.subject.Subject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentMapper {

    public Student toStudent(StudentRequest request) {
        /*List<Subject> subjects = new ArrayList<>();
        for (int id : request.subjectIds()) {
            subjects.add(
                    Subject
                            .builder()
                            .id(id)
                            .build()
            );
        }*/
        return Student.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .username(request.username())
                .email(request.email())
                .address(
                        // create a new address object
                        Address.builder()
                                .id(request.addressId())
                                .build()
                )
                .subjects(
                        request.subjectIds()
                                .stream()
                                .map(id -> Subject.builder().id(id).build())
                                .collect(Collectors.toList())
                )
                .build();
    }

    public Student toStudent(StudentLightRequest request) {

        return null;
    }
}
