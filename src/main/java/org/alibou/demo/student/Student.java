package org.alibou.demo.student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alibou.demo.address.Address;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Student {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(updatable = false, nullable = false, unique = true)
    private String username;
    private String email;

    @Column(length = 1000)
    private String firstname;
    private String lastname;

    @OneToOne(mappedBy = "student")
    private Address address;
}
