package org.alibou.demo.chapter;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.alibou.demo.chapter.dto.ChapterCreateRequest;
import org.alibou.demo.chapter.dto.ChapterResponse;
import org.alibou.demo.common.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/chapters")
@RequiredArgsConstructor
public class ChapterController {

  private final ChapterService service;
  private final ChapterRepository chapterRepository;

  @Operation(
      summary = "Create a new chapter",
      description = "Creates a new chapter based on the provided details.",
      tags = {"Chapters"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Chapter created successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ChapterResponse.class)
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
  public ResponseEntity<ChapterResponse> createChapter(
      @RequestBody @Valid ChapterCreateRequest chapter
  ) {
    ChapterResponse chapterResponse = service.createChapter(chapter);
    return ResponseEntity.accepted().body(chapterResponse);
  }

  @Operation(
      summary = "Update an existing chapter",
      description = "Updates the details of an existing chapter based on the provided details.",
      tags = {"Chapters"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Chapter updated successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ChapterResponse.class)
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
              description = "Chapter not found",
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
  public ResponseEntity<ChapterResponse> updateChapter(@PathVariable("id") Integer id,
      @RequestBody @Valid ChapterCreateRequest chapter
  ) {
    chapterRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("The chapter is not found with id " + id));
    ChapterResponse chapterResponse = service.createChapter(chapter);
    return ResponseEntity.accepted().body(chapterResponse);
  }

  @Operation(
      summary = "Get a chapter by ID",
      description = "Retrieves a chapter's details by their ID.",
      tags = {"Chapters"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Chapter retrieved successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ChapterResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Chapter not found",
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
  public ResponseEntity<ChapterResponse> getChapterById(@PathVariable Integer id) {
    ChapterResponse chapterResponse = service.findById(id);
    return ResponseEntity.ok(chapterResponse);
  }

  @Operation(
      summary = "Delete a chapter by ID",
      description = "Deletes a chapter by their ID.",
      tags = {"Chapters"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "204",
              description = "Chapter deleted successfully"
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Chapter not found",
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
  public ResponseEntity<Void> deleteChapterById(@PathVariable Integer id) {
    service.deleteChapterById(id);
    return ResponseEntity.noContent().build();
  }
}
