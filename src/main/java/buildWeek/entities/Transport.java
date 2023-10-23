package buildWeek.entities;

<<<<<<< Updated upstream
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
=======
import buildWeek.enums.TransportType;

import javax.persistence.*;
import java.util.List;
>>>>>>> Stashed changes

@Entity
@Table(name = "transports")
public class Transport {

    @OneToMany(mappedBy = "transport", cascade = CascadeType.REMOVE)
    List<Travel> travels;
    @OneToMany(mappedBy = "transport", cascade = CascadeType.REMOVE)
    List<Validation> validations;
    @OneToMany(mappedBy = "transport", cascade = CascadeType.REMOVE)
    List<Service> services;
    @Id
    @GeneratedValue
<<<<<<< Updated upstream
    private int id;



=======
    private int Id;
    private TransportType type;
    private int capacity;
    private boolean active;

    public Transport() {
    }

    public Transport(TransportType type, int capacity, boolean active) {
        this.type = type;
        this.capacity = capacity;
        this.active = active;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public int getId() {
        return Id;
    }

    public TransportType getType() {
        return type;
    }

    public void setType(TransportType type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Travel> getTravels() {
        return travels;
    }

    public void setTravels(List<Travel> travels) {
        this.travels = travels;
    }

    public List<Validation> getValidations() {
        return validations;
    }

    public void setValidations(List<Validation> validations) {
        this.validations = validations;
    }
>>>>>>> Stashed changes
}
