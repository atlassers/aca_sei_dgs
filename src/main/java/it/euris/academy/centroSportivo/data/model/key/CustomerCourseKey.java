package it.euris.academy.centroSportivo.data.model.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class CustomerCourseKey implements Serializable {

    @Column(name="customer_id")
    private Long customerId;

    @Column(name="course_id")
    private Long courseId;
}
