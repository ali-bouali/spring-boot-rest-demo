package org.alibou.demo.address;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.alibou.demo.address.dto.AddressCreateRequest;
import org.alibou.demo.address.dto.AddressMapper;
import org.alibou.demo.address.dto.AddressResponse;
import org.alibou.demo.address.dto.AddressUpdateRequest;
import org.alibou.demo.common.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RequestMapping("/api/v1/addresses")
@RestController
@AllArgsConstructor
public class AddressController {

  private final AddressService addressService;
  private final AddressRepository addressRepository;
  private final AddressMapper addressMapper;

  @Operation(
      summary = "Create a new address",
      description = "Creates a new address based on the provided details.",
      tags = {"Addresses"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Address created successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = AddressResponse.class)
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
  public ResponseEntity<AddressResponse> createAddress(
      @RequestBody @Valid AddressCreateRequest address
  ) {
    AddressResponse addressResponse = addressService.createAddress(address);
    return ResponseEntity.accepted().body(addressResponse);
  }

  @Operation(
      summary = "Update an existing address",
      description = "Updates the details of an existing address based on the provided details.",
      tags = {"Addresses"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Address updated successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = AddressResponse.class)
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
              description = "Address not found",
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
  public ResponseEntity<AddressResponse> updateAddress(@PathVariable("id") Integer id,
      @RequestBody @Valid AddressUpdateRequest address
  ) {
    addressRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("The address is not found with id " + id));
    AddressResponse addressResponse = addressService.updateteAddress(address);
    return ResponseEntity.accepted().body(addressResponse);
  }

  @Operation(
      summary = "Get an address by ID",
      description = "Retrieves an address's details by their ID.",
      tags = {"Addresses"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Address retrieved successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = AddressResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Address not found",
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
  public ResponseEntity<AddressResponse> getAddressById(@PathVariable Integer id) {
    return addressRepository.findById(id)
        .map(address -> ResponseEntity.ok(addressMapper.toAddressResponse(address)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Operation(
      summary = "Delete an address by ID",
      description = "Deletes an address by their ID.",
      tags = {"Addresses"},
      security = @SecurityRequirement(name = "WorkspaceUserSessionToken"),
      responses = {
          @ApiResponse(
              responseCode = "204",
              description = "Address deleted successfully"
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Address not found",
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
  public ResponseEntity<Void> deleteAddressById(@PathVariable Integer id) {
    addressService.deleteAddressById(id);
    return ResponseEntity.noContent().build();
  }
}
