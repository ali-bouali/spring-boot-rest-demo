package org.alibou.demo.subject;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.alibou.demo.common.ErrorDetails;
import org.alibou.demo.subject.dto.SubjectCreateRequest;
import org.alibou.demo.subject.dto.SubjectLightRequest;
import org.alibou.demo.subject.dto.SubjectResponse;
import org.alibou.demo.subject.dto.SubjectUpdateRequest;
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
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {

  private final SubjectService service;

  @Operation(
      summary = "Create a new subject",
      description = "Creates a new subject based on the provided details.",
      tags = {"Subjects"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Subject created successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = SubjectResponse.class)
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
  @PostMapping
  public ResponseEntity<SubjectResponse> createSubject(
      @RequestBody @Valid SubjectCreateRequest subject
  ) {
    SubjectResponse subj = service.createSubject(subject);
    return ResponseEntity.accepted().body(subj);
  }

  @Operation(
      summary = "Update an existing subject",
      description = "Updates the details of an existing subject based on the provided details.",
      tags = {"Subjects"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Subject updated successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = SubjectResponse.class)
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
              description = "Subject not found",
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
  public ResponseEntity<SubjectResponse> updateSubject(
      @RequestBody @Valid SubjectUpdateRequest subject
  ) {
    SubjectResponse subj = service.updateSubject(subject);
    return ResponseEntity.accepted().body(subj);
  }

  @Operation(
      summary = "Create a new subject with less information",
      description = "Creates a new subject with less information based on the provided details.",
      tags = {"Subjects"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Subject created successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = SubjectResponse.class)
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
  @PostMapping("/special")
  public ResponseEntity<SubjectResponse> createSubjectWithLessInformation(
      @RequestBody SubjectLightRequest subject
  ) {
    SubjectResponse subj = service.createSubjectWithLessInformation(subject);
    return ResponseEntity.accepted().body(subj);
  }

  @Operation(
      summary = "Get a subject by ID",
      description = "Retrieves a subject's details by their ID.",
      tags = {"Subjects"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Subject retrieved successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = SubjectResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Subject not found",
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
  public ResponseEntity<SubjectResponse> getSubjectById(@PathVariable Integer id) {
    SubjectResponse subjectResponse = service.findById(id);
    return ResponseEntity.ok(subjectResponse);
  }

  @Operation(
      summary = "Delete a subject by ID",
      description = "Deletes a subject by their ID.",
      tags = {"Subjects"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "204",
              description = "Subject deleted successfully"
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Subject not found",
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
  public ResponseEntity<Void> deleteSubjectById(@PathVariable Integer id) {
    service.deleteSubjectById(id);
    return ResponseEntity.noContent().build();
  }

  @Operation(
      summary = "Search for subjects",
      description = "Search for subjects based on the provided criteria.",
      tags = {"Subjects"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Subjects retrieved successfully",
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
  public Page<SubjectResponse> searchSubjects(
      @RequestParam(required = false) Integer capacity,
      @RequestParam(required = false) String name,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    return service.search(capacity, name, page, size);
  }
}
