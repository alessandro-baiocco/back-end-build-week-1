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
}
