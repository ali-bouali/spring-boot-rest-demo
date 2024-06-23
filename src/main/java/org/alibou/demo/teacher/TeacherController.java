package org.alibou.demo.teacher;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.alibou.demo.common.ErrorDetails;
import org.alibou.demo.teacher.dto.TeacherCreateRequest;
import org.alibou.demo.teacher.dto.TeacherLightRequest;
import org.alibou.demo.teacher.dto.TeacherResponse;
import org.alibou.demo.teacher.dto.TeacherUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TeacherController {

  private final TeacherService service;

  @Operation(
      summary = "Create a new teacher",
      description = "Creates a new teacher based on the provided details.",
      tags = {"Users"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Teacher created successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = TeacherResponse.class)
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
  @PostMapping("/teachers/register")
  public ResponseEntity<TeacherResponse> registerteTeacher(
      @RequestBody @Valid TeacherCreateRequest teacher
  ) {
    TeacherResponse teacherResponse = service.registerTeacher(teacher);
    return ResponseEntity.accepted().body(teacherResponse);
  }

  @Operation(
      summary = "Update an existing teacher",
      description = "Updates the details of an existing teacher based on the provided details.",
      tags = {"Teachers"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Teacher updated successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = TeacherResponse.class)
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
              description = "Teacher not found",
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
  @PutMapping("/teachers/{id}")
  public ResponseEntity<TeacherResponse> updateTeacher(
      @PathVariable Integer id,
      @RequestBody @Valid TeacherUpdateRequest teacher
  ) {
    TeacherResponse teacherResponse = service.updateTeacher(teacher);
    return ResponseEntity.accepted().body(teacherResponse);
  }

  @Operation(
      summary = "Create a new teacher with less information",
      description = "Creates a new teacher with less information based on the provided details.",
      tags = {"Users"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Teacher created successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = TeacherResponse.class)
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
  @PostMapping("/teachers/special/register")
  public ResponseEntity<TeacherResponse> registerTeacherWithLessInformation(
      @RequestBody TeacherLightRequest teacher
  ) {
    TeacherResponse teacherResponse = service.registerTeacherWithLessInformation(teacher);
    return ResponseEntity.accepted().body(teacherResponse);
  }

  @Operation(
      summary = "Get a teacher by ID",
      description = "Retrieves a teacher's details by their ID.",
      tags = {"Teacher Management"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Teacher retrieved successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = TeacherResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Teacher not found",
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
  @GetMapping("/teachers/{id}")
  public ResponseEntity<TeacherResponse> getTeacherById(@PathVariable Integer id) {
    TeacherResponse teacherResponse = service.findById(id);
    return ResponseEntity.ok(teacherResponse);
  }

  @Operation(
      summary = "Delete a teacher by ID",
      description = "Deletes a teacher by their ID.",
      tags = {"Teacher Management"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "204",
              description = "Teacher deleted successfully"
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Teacher not found",
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
  @DeleteMapping("/teachers/{id}")
  public ResponseEntity<Void> deleteTeacherById(@PathVariable Integer id) {
    service.deleteTeacherById(id);
    return ResponseEntity.noContent().build();
  }

  @Operation(
      summary = "Search for teachers",
      description = "Search for teachers based on the provided criteria.",
      tags = {"Teacher Management"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Teachers retrieved successfully",
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
  @GetMapping("/teachers/search")
  public Page<TeacherResponse> searchTeachers(
      @RequestParam(required = false) String firstname,
      @RequestParam(required = false) String lastname,
      @RequestParam(required = false) Integer id,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    return service.search(firstname, lastname, id, page, size);
  }
}
