package org.alibou.demo.address;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.alibou.demo.common.BaseEntity;
import org.alibou.demo.student.Student;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Address extends BaseEntity {

    private String street;
    private String houseNumber;
    private String city;
    @Embedded
    private ZipCode zipCode;

    @OneToOne
    private Student student;

}
