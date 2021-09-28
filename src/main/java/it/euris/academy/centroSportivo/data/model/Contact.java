/**
 * @author Stefano De Giorgi
 * @since 2021-09-24
 */

package it.euris.academy.centroSportivo.data.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.euris.academy.centroSportivo.data.archetype.Model;
import it.euris.academy.centroSportivo.data.dto.ContactDto;
import it.euris.academy.centroSportivo.data.enums.ContactType;
import it.euris.academy.centroSportivo.utils.UT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="contact")
@Where(clause = "deleted=false")
public class Contact implements Model{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private Long id;
  
  @Column(name="contact_type")
  @Enumerated(EnumType.STRING)
  private ContactType contactType;
  
  @Column(name="value")
  private String value;

  @Column(name="deleted")
  @Builder.Default
  private Boolean deleted=false;

  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customerId;
  
  @Override
  public ContactDto toDto() {
    return ContactDto.builder()
        .id(UT.numberToString(id))
        .contactType(UT.contactTypeToString(contactType))
        .value(value)
        .customerId(customerId.getId().toString())
        .build();
  }

}
