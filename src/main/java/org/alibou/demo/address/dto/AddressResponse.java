package org.alibou.demo.address.dto;

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
public class AddressResponse {
  private  Integer id ;
  private String street;
  private String city;
  private String state;
  private String country;
  private String postalCode;
}
