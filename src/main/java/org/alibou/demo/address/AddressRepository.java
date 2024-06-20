package org.alibou.demo.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
Boolean existsByStreetAndCityAndStateAndCountryAndPostalCodeAndIdNot(
    String street, String  city, String state, String country, String postalCode ,Integer id );
}
