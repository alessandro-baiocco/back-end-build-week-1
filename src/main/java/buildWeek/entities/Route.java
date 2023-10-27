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
    @OneToMany(mappedBy = "route")
    private List<Transport> transports;

    @Nullable
    @OneToMany(mappedBy = "route")
    private List<Travel> travels;

    public Route() {
    }

    public Route(String startRoutePlace, String endRoutePlace, int duration) {
        this.startRoutePlace = startRoutePlace;
        this.endRoutePlace = endRoutePlace;
        this.duration = duration;
        this.transports = null;
        this.travels = null;
    }

    public Route(String startRoutePlace, String endRoutePlace, int duration, List<Transport> transports, List<Travel> travels) {
        this.startRoutePlace = startRoutePlace;
        this.endRoutePlace = endRoutePlace;
        this.duration = duration;
        this.transports = transports;
        this.travels = travels;
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

    public List<Transport> getTransports() {
        return transports;
    }

    public void setTransports(List<Transport> transports) {
        this.transports = transports;
    }

    public List<Travel> getTravels() {
        return travels;
    }

    public void setTravels(List<Travel> travels) {
        this.travels = travels;
    }


    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", startRoutePlace='" + startRoutePlace + '\'' +
                ", endRoutePlace='" + endRoutePlace + '\'' +
                ", duration=" + duration +
                '}';
    }
}
