package org.alibou.demo.address.dto;


import org.alibou.demo.address.Address;
import org.springframework.stereotype.Service;

@Service
public class AddressMapper {

  public Address toAdress(AddressCreateRequest request) {
    return Address.builder()
        .postalCode(request.getPostalCode())
        .city(request.getCity())
        .street(request.getStreet())
        .state(request.getState())
        .country(request.getCountry())
        .build();


  }


  public Address toAdress(AddressUpdateRequest request) {
    return Address.builder()
        .id(request.getId())
        .postalCode(request.getPostalCode())
        .city(request.getCity())
        .street(request.getStreet())
        .state(request.getState())
        .country(request.getCountry())
        .build();


  }


  public AddressResponse toAddressResponse(Address request) {
    return AddressResponse.builder()
        .id(request.getId())
        .postalCode(request.getPostalCode())
        .city(request.getCity())
        .street(request.getStreet())
        .state(request.getState())
        .country(request.getCountry())
        .build();
  }


}
