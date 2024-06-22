package org.alibou.demo.content;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.alibou.demo.common.ErrorDetails;
import org.alibou.demo.content.dto.ContentCreateRequest;
import org.alibou.demo.content.dto.ContentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/contents")
@RequiredArgsConstructor
public class ContentController {

  private final ContentService service;
  private final ContentRepository contentRepository;

  @Operation(
      summary = "Create a new content",
      description = "Creates a new content based on the provided details.",
      tags = {"Contents"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Content created successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ContentResponse.class)
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
  public ResponseEntity<ContentResponse> createContent(
      @RequestBody @Valid ContentCreateRequest content
  ) {
    ContentResponse contentResponse = service.createContent(content);
    return ResponseEntity.accepted().body(contentResponse);
  }

  @Operation(
      summary = "Update an existing content",
      description = "Updates the details of an existing content based on the provided details.",
      tags = {"Contents"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Content updated successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ContentResponse.class)
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
              description = "Content not found",
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
  public ResponseEntity<ContentResponse> updateContent(@PathVariable("id") Integer id,
      @RequestBody @Valid ContentCreateRequest content
  ) {
    contentRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("The content is not found with id " + id));
    ContentResponse contentResponse = service.createContent(content);
    return ResponseEntity.accepted().body(contentResponse);
  }

  @Operation(
      summary = "Get a content by ID",
      description = "Retrieves a content's details by their ID.",
      tags = {"Contents"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Content retrieved successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ContentResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Content not found",
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
  public ResponseEntity<ContentResponse> getContentById(@PathVariable Integer id) {
    ContentResponse contentResponse = service.findById(id);
    return ResponseEntity.ok(contentResponse);
  }

  @Operation(
      summary = "Delete a content by ID",
      description = "Deletes a content by their ID.",
      tags = {"Contents"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "204",
              description = "Content deleted successfully"
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Content not found",
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
  public ResponseEntity<Void> deleteContentById(@PathVariable Integer id) {
    service.deleteContentById(id);
    return ResponseEntity.noContent().build();
  }
}
