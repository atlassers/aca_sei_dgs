package it.euris.academy.centroSportivo.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.euris.academy.centroSportivo.data.archetype.Dto;
import it.euris.academy.centroSportivo.data.model.Course;
import it.euris.academy.centroSportivo.data.model.Customer;
import it.euris.academy.centroSportivo.data.model.CustomerCourse;
import it.euris.academy.centroSportivo.utils.UT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerCourseDto implements Dto {

    @JsonIgnore
    private String id;
    private String customerId;
    private String courseId;
    private Boolean deleted;

    @Override
    public CustomerCourse toModel() {
    return CustomerCourse.builder()
        .customer(new Customer(UT.toLong(customerId)))
        .course(new Course(UT.toLong(courseId)))
        .deleted(deleted)
        .build();
    }
}
