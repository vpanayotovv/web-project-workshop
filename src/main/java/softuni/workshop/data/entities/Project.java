package softuni.workshop.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
public class Project extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name = "is_finished")
    private boolean isFinished;

    @Column(nullable = false)
    private BigDecimal payment;

    @Column
    private String startDate;

    @ManyToOne
    private Company company;
}
