package buildWeek.entities;

import com.sun.istack.Nullable;

import javax.persistence.*;

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
    private Transport meansOfTransport;
   @ManyToOne
   private Travel travel;

    public Route() {}

    public Route(String startRoutePlace, String endRoutePlace, int duration, Transport meansOfTransport, Travel travel) {
        this.startRoutePlace = startRoutePlace;
        this.endRoutePlace = endRoutePlace;
        this.duration = duration;
        this.meansOfTransport = meansOfTransport;
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
        return meansOfTransport;
    }

    public void setMeansOfTransport(Transport meansOfTransport) {
        this.meansOfTransport = meansOfTransport;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", startRoutePlace='" + startRoutePlace + '\'' +
                ", endRoutePlace='" + endRoutePlace + '\'' +
                ", duration=" + duration +
                ", meansOfTransport=" + meansOfTransport +
                ", travel=" + travel +
                '}';
    }
}
