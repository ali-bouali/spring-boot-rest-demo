package org.alibou.demo.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alibou.demo.address.Address;
import org.springframework.transaction.annotation.Transactional;

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

    @OneToOne
    @JsonManagedReference
    private Address address;

    // @Transactional
    /**
     * Just create an object that holds the information
     * and return the object.
     * @Transactional is mandatory when we need to fetch
     * sub data (expl: Address) when the fetch type is LAZY
     */
    void test() {
        Student s = new Student(); // from DB
        // return s;
        /*

        StudentResponse res = new StudentResponse();
        // set values
        res.setFirstname(s.getFirstname());
         */

    }
}
