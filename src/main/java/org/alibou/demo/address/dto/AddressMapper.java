package org.alibou.demo.address.dto;


import org.alibou.demo.address.Address;
import org.alibou.demo.student.Student;
import org.alibou.demo.student.dto.StudentLightRequest;
import org.alibou.demo.student.dto.StudentResponse;
import org.springframework.stereotype.Service;

@Service
public class AddressMapper {



  public Address toAdress(AddressRequest request) {

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
