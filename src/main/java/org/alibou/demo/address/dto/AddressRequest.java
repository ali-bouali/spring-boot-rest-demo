package org.alibou.demo.address.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
 private  Integer id ;
 @NotEmpty( message = "The street should not be not null")
  private String street;
  @NotEmpty( message = "The city should not be not null")
  private String city;
  @NotEmpty( message = "The state should not be not null")
  private String state;
  @NotEmpty( message = "The country should not be not null")
  private String country;
  @NotEmpty( message = "The postalCode should not be not null")
  private String postalCode;
}
