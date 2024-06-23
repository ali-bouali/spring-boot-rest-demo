package org.alibou.demo.student;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.alibou.demo.common.ErrorDetails;
import org.alibou.demo.student.dto.StudentCreateRequest;
import org.alibou.demo.student.dto.StudentLightRequest;
import org.alibou.demo.student.dto.StudentResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

  private final StudentService service;
  private final StudentRepository studentRepository;

  @Operation(
      summary = "Create a new student",
      description = "Creates a new student based on the provided details.",
      tags = {"Users"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Student created successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = StudentResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid request parameters - Parameters are incorrect or missing",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorDetails.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal Server Error - An unexpected error occurred",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorDetails.class)
              )
          )
      }
  )
  @PostMapping("/register")
  public ResponseEntity<StudentResponse> registerStudent(
      @RequestBody @Valid StudentCreateRequest student
  ) {
    StudentResponse studentResponse = service.registerStudent(student);
    return ResponseEntity.accepted().body(studentResponse);
  }

  @Operation(
      summary = "Update an existing student",
      description = "Updates the details of an existing student based on the provided details.",
      tags = {"Students"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Student updated successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = StudentResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid request parameters - Parameters are incorrect or missing",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorDetails.class)
              )
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Student not found",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorDetails.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal Server Error - An unexpected error occurred",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorDetails.class)
              )
          )
      }
  )
  @PutMapping("/{id}")
  public ResponseEntity<StudentResponse> updateStudent(@PathVariable("id") Integer id,
      @RequestBody @Valid StudentCreateRequest student
  ) {
    studentRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("The student is not found with id " + id));
    StudentResponse studentResponse = service.registerStudent(student);
    return ResponseEntity.accepted().body(studentResponse);
  }

  @Operation(
      summary = "Create a new student with less information",
      description = "Creates a new student with less information based on the provided details.",
      tags = {"Users"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Student created successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = StudentResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid request parameters - Parameters are incorrect or missing",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorDetails.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal Server Error - An unexpected error occurred",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorDetails.class)
              )
          )
      }
  )
  @PostMapping("/special/register")
  public ResponseEntity<StudentResponse> createStudentWithLessInformation(
      @RequestBody StudentLightRequest student
  ) {
    StudentResponse resStudent = service.registerStudentWithLessInformation(student);
    return ResponseEntity.accepted().body(resStudent);
  }

  @Operation(
      summary = "Get a student by ID",
      description = "Retrieves a student's details by their ID.",
      tags = {"Students"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Student retrieved successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = StudentResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Student not found",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorDetails.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal Server Error - An unexpected error occurred",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorDetails.class)
              )
          )
      }
  )
  @GetMapping("/{id}")
  public ResponseEntity<StudentResponse> getStudentById(@PathVariable Integer id) {
    StudentResponse studentResponse = service.findById(id);
    return ResponseEntity.ok(studentResponse);
  }

  @Operation(
      summary = "Delete a student by ID",
      description = "Deletes a student by their ID.",
      tags = {"Student Management"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "204",
              description = "Student deleted successfully"
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Student not found",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorDetails.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal Server Error - An unexpected error occurred",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorDetails.class)
              )
          )
      }
  )
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteStudentById(@PathVariable Integer id) {
    service.deleteStudentById(id);
    return ResponseEntity.noContent().build();
  }

  @Operation(
      summary = "Search for students",
      description = "Search for students based on the provided criteria.",
      tags = {"Student Management"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Students retrieved successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Page.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid request parameters - Parameters are incorrect or missing",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorDetails.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal Server Error - An unexpected error occurred",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorDetails.class)
              )
          )
      }
  )
  @GetMapping("/search")
  public Page<StudentResponse> searchStudents(
      @RequestParam(required = false) String firstname,
      @RequestParam(required = false) String lastname,
      @RequestParam(required = false) String email,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    return service.search(firstname, lastname, email, page, size);
  }
}

