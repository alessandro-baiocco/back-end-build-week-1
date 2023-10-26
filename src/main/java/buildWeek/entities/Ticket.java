package buildWeek.entities;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tickets")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Ticket extends TicketsItem {
    @Nullable
    @ManyToOne
    @JoinColumn(name = "validation_id")
    private Validation validation;


    public Ticket() {
    }

    public Ticket(LocalDate createdDate, Seller seller) {
        super(createdDate, seller);
        this.validation = null;
    }


    public Ticket(LocalDate createdDate, Seller seller, Validation validation) {
        super(createdDate, seller);
        this.validation = validation;
    }

    public Validation getValidation() {
        return validation;
    }

    public void setValidation(Validation validation) {
        this.validation = validation;
    }
    


    @Override
    public String toString() {
        return "validato ? " + (this.validation != null ? "si" : "no")
                + " " + super.toString();
    }
}
