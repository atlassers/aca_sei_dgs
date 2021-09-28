/**
 * @author Stefano De Giorgi
 * @since 2021-09-24
 */

package it.euris.academy.centroSportivo.data.model;

import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.euris.academy.centroSportivo.data.archetype.Dto;
import it.euris.academy.centroSportivo.data.archetype.Model;
import it.euris.academy.centroSportivo.data.dto.CourseDto;
import it.euris.academy.centroSportivo.data.enums.Difficulty;
import it.euris.academy.centroSportivo.data.enums.Sport;
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
@Table(name="course")
@Where(clause = "deleted=false")
public class Course implements Model{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private Long id;
  
  @Column(name="denomination")
  private String denomination;
  
  @Column(name="price")
  private Double price;
  
  @Column(name="sport")
  private Sport sport;
  
  @Column(name="difficulty")
  private Difficulty difficulty;

  @Column(name="deleted")
  @Builder.Default
  private Boolean deleted=false;

  @OneToMany(mappedBy = "course")
  @JsonIgnore
  private List<CustomerCourse> customerCourses;

  public Course(Long id) {
    this.id= id;
  }
  
  @Override
  public CourseDto toDto() {
    return CourseDto.builder()
            .id(UT.numberToString(id))
            .denomination(denomination)
            .price(UT.numberToString(price))
            .deleted(deleted)
            .sport(UT.sportToString(sport))
            .difficulty(UT.difficultyToString(difficulty))
            .build();
  }
}
