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

    public Ticket(int ID, LocalDate createdDate, Seller sellerId) {
        super(ID, createdDate, sellerId);
        this.validation = null;
    }


    public Ticket(int ID, LocalDate createdDate, Seller sellerId, Validation validation) {
        super(ID, createdDate, sellerId);
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
        return " validato? " + validation;
    }
}
