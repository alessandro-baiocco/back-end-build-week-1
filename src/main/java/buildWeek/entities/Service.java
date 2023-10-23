package buildWeek.entities;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "transport_id")
    private Transport transport;

    private LocalDate start_date;
    private LocalDate end_date;

    public Service() {
    }

    public Service(Transport transport, LocalDate start_date) {
        this.transport = transport;
        this.start_date = start_date;
    }

    public int getId() {
        return id;
    }


    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }


}
