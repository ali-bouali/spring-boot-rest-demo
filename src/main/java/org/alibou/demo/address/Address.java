package org.alibou.demo.address;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.alibou.demo.student.Student;

@Entity
@Data
public class Address {
  @Id
  @SequenceGenerator(
      name = "address_sequence",
      sequenceName = "address_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      generator = "address_sequence",
      strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false)
  @NotNull(message="The street should not be null")
  private String street;

  @Column(nullable = false)
  @NotNull(message="The city should not be null")
  private String city;

  @Column(nullable = false)
  @NotNull(message="The state should not be null")
  private String state;

  @Column(nullable = false)
  @NotNull(message="The country should not be null")
  private String country;

  @Column(nullable = false)
  @NotNull(message="The postalCode should not be null")
  private String postalCode;
  @JsonIgnore
  @OneToOne(mappedBy = "address")
  private Student student;



}
