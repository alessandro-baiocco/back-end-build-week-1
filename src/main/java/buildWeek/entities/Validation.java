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
    private LocalDate validationDate;

    @ManyToOne
    @JoinColumn(name="transport_id")
    Transport transport;

    @ManyToOne
    @JoinColumn(name="travel_id")
    Travel travel;

    @OneToOne
    @JoinColumn(name="ticket_id")
    Ticket ticket;


    public Validation() {
    }

    public Validation(LocalDate validationDate, Transport transport, Travel travel,Ticket ticket) {
        this.validationDate = validationDate;
        this.transport = transport;
        this.travel = travel;
        this.ticket = ticket;
    }

    public int getId() {
        return id;
    }

    public LocalDate getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(LocalDate validationDate) {
        this.validationDate = validationDate;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "Validation{" +
                "id=" + id +
                ", validationDate=" + validationDate +
                ", transport=" + transport +
                ", travel=" + travel +
                ", ticket=" + ticket +
                '}';
    }
}
