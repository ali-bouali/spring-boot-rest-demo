package org.alibou.demo.address;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.alibou.demo.common.BaseEntity;
import org.alibou.demo.student.Student;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Address  extends BaseEntity {

  @Id
  @SequenceGenerator(
      name = "address_sequence",
      sequenceName = "address_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      generator = "address_sequence",
      strategy = GenerationType.SEQUENCE)
  private Integer id;

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
  @JsonBackReference
  //@JsonIgnore
  private Student student;


}
