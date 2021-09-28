/**
 * @author Stefano De Giorgi
 * @since 2021-09-24
 */

package it.euris.academy.centroSportivo.data.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.euris.academy.centroSportivo.data.archetype.Dto;
import it.euris.academy.centroSportivo.data.archetype.Model;
import it.euris.academy.centroSportivo.data.model.Course;
import it.euris.academy.centroSportivo.utils.UT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CourseDto implements Dto{

  private String id;
  private String denomination;
  private String price;
  private String sport;
  private String difficulty;
  @JsonIgnore
  private List<CustomerDto> customers;
  private Boolean deleted;
  
  @Override
  public Course toModel() {
    return Course.builder()
        .id(UT.toLong(id))
        .denomination(denomination)
        .price(UT.toDouble(price))
        .sport(UT.stringToSport(sport))
        .difficulty(UT.stringToDifficulty(difficulty))
        .build();
  }
}
