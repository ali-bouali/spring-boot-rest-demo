package org.alibou.demo.address;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ZipCode {

    private String code;
    private Integer district;

    public String getParsedZipCode() {
        return code + "-" + district;
    }

    // 5 other logic methods
}
