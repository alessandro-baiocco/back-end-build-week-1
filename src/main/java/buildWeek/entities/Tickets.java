package buildWeek.entities;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tickets")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Tickets extends TicketsItem {


    @Nullable
    @ManyToOne
    private Validation validationId;


    public Tickets() {
    }

    public Tickets(int ID, LocalDate createdDate, Seller sellerId) {
        super(ID, createdDate, sellerId);
        this.validationId = null;
    }


    public Tickets(int ID, LocalDate createdDate, Seller sellerId, Validation validationId) {
        super(ID, createdDate, sellerId);
        this.validationId = validationId;
    }

    public Validation getValidationId() {
        return validationId;
    }

    public void setValidationId(Validation validationId) {
        this.validationId = validationId;
    }


    @Override
    public String toString() {
        return " validato? " + validationId;
    }
}
