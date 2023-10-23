package buildWeek.entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="validations")
public class Validation {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "validation_date")
    private LocalDate valitadionDate;

    @ManyToOne
    @JoinColumn(name="transport_id")
    Transport transport;

    @ManyToOne
    @JoinColumn(name="travel_id")
    Travel travel;







}
