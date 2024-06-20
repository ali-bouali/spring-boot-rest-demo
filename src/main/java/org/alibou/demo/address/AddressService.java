package org.alibou.demo.address;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.alibou.demo.address.dto.AddressMapper;
import org.alibou.demo.address.dto.AddressRequest;
import org.alibou.demo.address.dto.AddressResponse;
import org.alibou.demo.common.exception.SubjectMaxLimitExceeded;


import org.alibou.demo.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@AllArgsConstructor
@Service
public class AddressService {

  private final AddressRepository addressRepository ;
  private final AddressMapper addressMapper ;
  @Transactional
  public AddressResponse createAddress(AddressRequest request) {

    Optional<Address> adr =addressRepository.findById(request.getId());
    Boolean existaddress =addressRepository.existsByStreetAndCityAndStateAndCountryAndPostalCodeAndIdNot(
        request.getStreet(), request.getCity(), request.getState(), request.getCountry(), request.getPostalCode() ,request.getId() );
    if(existaddress){

      throw new EntityExistsException("Adress already exists " );
    }
    Address address = addressRepository.save( addressMapper.toAdress(request)) ;

    return addressMapper.toAddressResponse(address) ;
  }
}
