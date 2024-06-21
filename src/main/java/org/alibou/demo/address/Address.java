package org.alibou.demo.address;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(name = "address", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"street", "city", "state", "country", "postalCode"})
})
public class Address extends BaseEntity {


  @Column(nullable = false)

  private String street;

  @Column(nullable = false)
  private String city;

  @Column(nullable = false)
  private String state;

  @Column(nullable = false)
  private String country;

  @Column(nullable = false)
  private String postalCode;
  @OneToOne(mappedBy = "address")
  //@JsonBackReference
  @JsonIgnore
  private Student student;


}
