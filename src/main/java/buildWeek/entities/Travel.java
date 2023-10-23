package buildWeek.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Travels")
public class Travel {

    @Id
    @GeneratedValue
    private int id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @ManyToOne
    @JoinColumn
    private Route route;

    @ManyToOne
    @JoinColumn(name = "transport_id")
    private Transport transport;

    public Travel() {
    }

    public Travel(LocalDateTime startDate, LocalDateTime endDate, Route route, Transport transport) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.route = route;
        this.transport = transport;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
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
