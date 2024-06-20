package org.alibou.demo.address;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.alibou.demo.address.dto.AddressMapper;
import org.alibou.demo.address.dto.AddressRequest;
import org.alibou.demo.address.dto.AddressResponse;
import org.alibou.demo.student.dto.StudentRequest;
import org.alibou.demo.student.dto.StudentResponse;
import org.apache.catalina.mapper.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/addresses")
@RestController
@AllArgsConstructor
public class Addresscontroller {

  private final AddressService addressService;
  private final AddressRepository addressRepository;
  private final AddressMapper  addressMapper ;

  @PostMapping
  public ResponseEntity<AddressResponse> createAddress(
      @RequestBody @Valid AddressRequest address
  ) {

    AddressResponse addressResponse = addressService.createAddress(address);
    return ResponseEntity.accepted().body(addressResponse);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AddressResponse> updateAddress( @PathVariable("id")  Integer id,
      @RequestBody @Valid AddressRequest address
  ) {
    addressRepository.findById(id).orElseThrow(()->new EntityNotFoundException(" The address  is not found with  " + id));
    AddressResponse addressResponse = addressService.createAddress(address);
    return ResponseEntity.accepted().body(addressResponse);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AddressResponse> getAddressById(@PathVariable Integer id) {

    return addressRepository.findById(id)
        .map(addresse -> ResponseEntity.ok(addressMapper.toAddressResponse(addresse)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
