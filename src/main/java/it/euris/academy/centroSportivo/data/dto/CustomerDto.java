/**
 * @author Stefano De Giorgi
 * @since 2021-09-24
 */

package it.euris.academy.centroSportivo.data.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.euris.academy.centroSportivo.data.archetype.Dto;
import it.euris.academy.centroSportivo.data.archetype.Model;

import it.euris.academy.centroSportivo.data.model.Customer;
import it.euris.academy.centroSportivo.utils.UT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerDto implements Dto{
  
  private String id;
  private String name;
  private String surname;
  private String birthDate;
  private String taxCode;
  private String address;
  @JsonIgnore
  private List<ContactDto> contacts;
  @JsonIgnore
  private List<CourseDto> courses;
  private Boolean deleted;
  
  @Override
  public Customer toModel() {
    return Customer.builder()
        .id(UT.toLong(id))
        .name(name)
        .surname(surname)
        .birthDate(UT.toInstant(birthDate))
        .taxCode(taxCode)
        .deleted(deleted)
        .build();
  }

}
