/**
 * @author Stefano De Giorgi
 * @since 2021-09-24
 */

package it.euris.academy.centroSportivo.data.dto;

import it.euris.academy.centroSportivo.data.archetype.Dto;
import it.euris.academy.centroSportivo.data.archetype.Model;
import it.euris.academy.centroSportivo.data.model.Contact;
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
public class ContactDto implements Dto{
  
  private String id;
  private String contactType;
  private String value;
  private String customerId;
  private Boolean deleted;
  
  @Override
  public Contact toModel() {
    return Contact.builder()
        .id(UT.toLong(id))
        .contactType(UT.stringToContactType(contactType))
        .value(value)
        .deleted(deleted)
        .customerId(Customer.builder().id(UT.toLong(customerId)).build())
        .build();
  }

}
