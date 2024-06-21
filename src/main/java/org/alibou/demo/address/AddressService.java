package org.alibou.demo.address;

import jakarta.persistence.EntityExistsException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.alibou.demo.address.dto.AddressCreateRequest;
import org.alibou.demo.address.dto.AddressMapper;
import org.alibou.demo.address.dto.AddressResponse;
import org.alibou.demo.address.dto.AddressUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class AddressService {

  private final AddressRepository addressRepository;
  private final AddressMapper addressMapper;

  @Transactional
  public AddressResponse createAddress(AddressCreateRequest request) {
    Boolean existaddress = addressRepository.existsByStreetAndCityAndStateAndCountryAndPostalCode(
        request.getStreet(), request.getCity(), request.getState(), request.getCountry(),
        request.getPostalCode());
    if (existaddress) {
      throw new EntityExistsException("Adress already exists ");
    }
    Address address = addressRepository.save(addressMapper.toAdress(request));
    return addressMapper.toAddressResponse(address);
  }

  @Transactional
  public AddressResponse updateteAddress(AddressUpdateRequest request) {

    Optional<Address> adr = addressRepository.findById(request.getId());
    Boolean existaddress = addressRepository.existsByStreetAndCityAndStateAndCountryAndPostalCodeAndIdNot(
        request.getStreet(), request.getCity(), request.getState(), request.getCountry(),
        request.getPostalCode(), request.getId());
    if (existaddress) {

      throw new EntityExistsException("Adress already exists ");
    }
    Address address = addressRepository.save(addressMapper.toAdress(request));

    return addressMapper.toAddressResponse(address);
  }

  @Transactional
  public void deleteAddressById(Integer id) {
    addressRepository.deleteById(id);
  }

}
