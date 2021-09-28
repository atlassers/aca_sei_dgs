/**
 * @author Stefano De Giorgi
 * @since 2021-09-24
 */

package it.euris.academy.centroSportivo.data.model;

import javax.persistence.*;
import it.euris.academy.centroSportivo.data.archetype.Model;
import it.euris.academy.centroSportivo.data.dto.AddressDto;
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
@Table(name="address")
@Where(clause = "deleted=false")
public class Address implements Model{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private Long id;
  
  @Column(name="address")
  private String address;
  
  @Column(name="postal_code")
  private Integer postalCode;

  @Column(name="deleted")
  @Builder.Default
  private Boolean deleted=false;

  @OneToOne
  @JoinColumn(name = "customer_id")
  private Customer customerId;
  
  //TODO da decidere come implementare questi campi
  private String city;
  private String province;
  private String nation;
  
  @Override
  public String toString() {
    return String.format(address + ", " + postalCode + ", " + city + ", " + province + ", " + nation);
  }
  
  @Override
  public AddressDto toDto() {
    return AddressDto.builder()
            .id(UT.numberToString(id))
            .address(address)
            .postalCode(UT.numberToString(postalCode))
            .deleted(deleted)
            .city(city)
            .province(province)
            .nation(nation)
            .build();
  }

}
