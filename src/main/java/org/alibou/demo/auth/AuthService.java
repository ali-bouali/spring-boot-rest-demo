package org.alibou.demo.auth;

import lombok.RequiredArgsConstructor;
import org.alibou.demo.security.JwtService;
import org.alibou.demo.student.Student;
import org.alibou.demo.student.StudentRepository;
import org.alibou.demo.teacher.Teacher;
import org.alibou.demo.teacher.TeacherRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public LoginResponse login(LoginRequest request) {
        Authentication user = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        Map<String, Object> claims = new HashMap<>();
        claims.put("attr", "some value");

        String token = jwtService.generateToken(claims, (UserDetails)user.getPrincipal());
        return new LoginResponse(token);
    }

    public void register(RegisterRequest request) {
        // transformation men user lel student wella teacher bech tsir hna
        if (StringUtils.hasLength(request.getStudentLevel())) {
            Student s = toStudent(request);
            registerStudent(s);
        } else if (StringUtils.hasLength(request.getTeacherSpeciality())) {
            Teacher t = toTeacher(request);
            registerTeacher(t);
        }
    }

    private void registerStudent(Student s) {
        studentRepository.save(s);
    }

    private void registerTeacher(Teacher t) {
        teacherRepository.save(t);
    }

    private Student toStudent(RegisterRequest request) {
        return Student.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(true)
                .level(request.getStudentLevel())
                .build();
    }

    private Teacher toTeacher(RegisterRequest request) {
        return Teacher.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(false)
                .speciality(request.getTeacherSpeciality())
                .build();
    }
}
