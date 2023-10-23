package buildWeek.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Travels")
public class Travel {

    @Id
    @GeneratedValue
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn
    private Route route;

    @ManyToOne
    @JoinColumn(name = "transport_id")
    private Transport transport;

    public Travel() {}

    public Travel(LocalDate startDate, LocalDate endDate, Route route, Transport transport) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.route = route;
        this.transport = transport;
    }

    public int getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", route=" + route +
                ", transport=" + transport +
                '}';
    }
}
