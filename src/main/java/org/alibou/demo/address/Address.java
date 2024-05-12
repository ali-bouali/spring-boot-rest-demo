package org.alibou.demo.address;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import org.alibou.demo.student.Student;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
  @Id
  @SequenceGenerator(
      name = "address_sequence",
      sequenceName = "address_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      generator = "address_sequence ",
      strategy = GenerationType.SEQUENCE)
  private Long id;

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
