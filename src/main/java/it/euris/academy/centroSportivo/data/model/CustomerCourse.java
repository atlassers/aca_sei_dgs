package it.euris.academy.centroSportivo.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.euris.academy.centroSportivo.data.archetype.Model;
import it.euris.academy.centroSportivo.data.dto.CustomerCourseDto;
import it.euris.academy.centroSportivo.data.model.key.CustomerCourseKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="customer_course")
@Where(clause = "deleted=false")
public class CustomerCourse implements Model {

    @JsonIgnore
    @EmbeddedId
    private CustomerCourseKey id;

    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name="deleted")
    @Builder.Default
    private Boolean deleted=false;
//TODO come implementare i totali da pagare per corso frequentato, faccio classe pagamenti in cui tengo conto di totale pagato dal cliente nella sua vita, totale ancora dovuto

    @Override
    public CustomerCourseDto toDto() {
        return CustomerCourseDto.builder()
            .customerId(customer==null ? null : customer.getId().toString())
            .courseId(course==null ? null :course.getId().toString())
            .deleted(deleted)
            .build();
    }
}
