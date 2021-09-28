/**
 * @author Stefano De Giorgi
 * @since ${d:date('yyyy-MM-dd', 'en_EN')}
 */

package it.euris.academy.centroSportivo.data.model;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.euris.academy.centroSportivo.data.archetype.Dto;
import it.euris.academy.centroSportivo.data.archetype.Model;
import it.euris.academy.centroSportivo.data.dto.CustomerDto;
import it.euris.academy.centroSportivo.utils.UT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "customer")
@Where(clause = "deleted=false")
public class Customer implements Model{
  
  @Id
  @Column(name="id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name="name")
  private String name;
  
  @Column(name="surname")
  private String surname;
  
  @Column(name="birth_date")
  private Instant birthDate;
  
  @Column(name="tax_code")
  private String taxCode;

  @Column(name="deleted")
  @Builder.Default
  private Boolean deleted=false;
  
  @OneToOne(mappedBy = "customerId")
  private Address address;

  @OneToMany(mappedBy = "customerId")
  @JsonIgnore
  private List<Contact> contacts;

  @OneToMany(mappedBy = "customer")
  @JsonIgnore
  private Set<CustomerCourse> customerCourses;
  //TODO valuto gli anni di iscrizione come conteggio dei corsi fatti

  public Customer(Long id) {
    this.id = id;
  }

  //TODO decidere come gestire le entità collegate tra Dto e model, per ora non le passo nella trasformazione usando piuttosto delle query ad hoc
  @Override
  public CustomerDto toDto() {
    return CustomerDto.builder()
            .id(UT.numberToString(id))
            .name(name)
            .surname(surname)
            .birthDate(UT.fromInstant(birthDate))
            .taxCode(taxCode)
            .deleted(deleted)
            .build();
  }
}
