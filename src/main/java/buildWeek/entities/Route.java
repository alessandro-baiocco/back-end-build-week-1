package buildWeek.entities;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Routes")
public class Route {
    @Id
    @GeneratedValue
    private int id;
    private String startRoutePlace;
    private String endRoutePlace;
    private int duration;
    @Nullable
    @OneToOne
    @JoinColumn(name = "transport_id")
    private Transport transport;

    @Nullable
    @OneToMany
    private List<Travel> travel;

    public Route() {
    }

    public Route(String startRoutePlace, String endRoutePlace, int duration) {
        this.startRoutePlace = startRoutePlace;
        this.endRoutePlace = endRoutePlace;
        this.duration = duration;
        this.transport = null;
        this.travel = null;
    }

    public Route(String startRoutePlace, String endRoutePlace, int duration, Transport transport, List<Travel> travel) {
        this.startRoutePlace = startRoutePlace;
        this.endRoutePlace = endRoutePlace;
        this.duration = duration;
        this.transport = transport;
        this.travel = travel;
    }

    public int getId() {
        return id;
    }


    public String getStartRoutePlace() {
        return startRoutePlace;
    }

    public void setStartRoutePlace(String startRoutePlace) {
        this.startRoutePlace = startRoutePlace;
    }

    public String getEndRoutePlace() {
        return endRoutePlace;
    }

    public void setEndRoutePlace(String endRoutePlace) {
        this.endRoutePlace = endRoutePlace;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Transport getMeansOfTransport() {
        return transport;
    }

    public void setMeansOfTransport(Transport transportId) {
        this.transport = transport;
    }

    public List<Travel> getTravel() {
        return travel;
    }

    public void setTravel(List<Travel> travel) {
        this.travel = travel;
    }


    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", startRoutePlace='" + startRoutePlace + '\'' +
                ", endRoutePlace='" + endRoutePlace + '\'' +
                ", duration=" + duration +
                ", transport=" + transport +
                ", travel=" + travel +
                '}';
    }
}
