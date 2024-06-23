package org.alibou.demo.user;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.alibou.demo.student.Student;
import org.alibou.demo.student.dto.StudentMapper;
import org.alibou.demo.subject.dto.SubjectMapper;
import org.alibou.demo.teacher.Teacher;
import org.alibou.demo.teacher.dto.TeacherMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Endpoints for managing users")
public class UserController {

  private final StudentMapper studentMapper;
  private final TeacherMapper teacherMapper;
  private final UserRepository userRepository;
  private final SubjectMapper mapper;


  @Operation(summary = "Update user enable status", description = "Enable or disable a user by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully updated user status",
          content = @Content(mediaType = "application/json"
          )),
      @ApiResponse(responseCode = "404", description = "User not found",
          content = @Content)
  })
  @PutMapping("/{id}/enable")
  public ResponseEntity<?> updateUserEnableStatus(
      @Parameter(description = "ID of the user to update", required = true)
      @PathVariable Integer id,
      @Parameter(description = "Enable status to set", required = true)
      @RequestParam boolean enable) {

    User usr = userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("user not found with id : " + id));
    usr.setEnabled(true);
    userRepository.save(usr);
    if (usr instanceof Student) {
      return ResponseEntity.ok(studentMapper.toStudentResponse((Student) usr));

    }
    {
      Teacher teacher = (Teacher) usr;

      return ResponseEntity.ok(teacherMapper.toTeacherResponse(teacher, mapper));
    }


  }
}
