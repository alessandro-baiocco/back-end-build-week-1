package buildWeek.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transports")
public class Transport {

    @Id
    @GeneratedValue
    private int id;



}
