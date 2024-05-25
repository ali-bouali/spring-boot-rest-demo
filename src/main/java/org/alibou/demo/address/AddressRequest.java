package org.alibou.demo.address;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class AddressRequest {

    @NotNull
    private String street;
    @NotNull
    private String houseNumber;
    @NotNull
    private String city;
}
