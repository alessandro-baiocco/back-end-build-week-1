package buildWeek.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tickets")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Tickets {
    @Id
    @GeneratedValue
    private int ID;
    private LocalDate created_date;
    private Seller sellerId;
    private Validation validationId;


    public Tickets() {
    }

    public Tickets(LocalDate created_date, Seller sellerId, Validation validationId) {
        this.created_date = created_date;
        this.sellerId = sellerId;
        this.validationId = validationId;
    }

    public int getID() {
        return ID;
    }

    public LocalDate getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDate created_date) {
        this.created_date = created_date;
    }

    public Seller getSellerId() {
        return sellerId;
    }

    public void setSellerId(Seller sellerId) {
        this.sellerId = sellerId;
    }

    public Validation getValidationId() {
        return validationId;
    }

    public void setValidationId(Validation validationId) {
        this.validationId = validationId;
    }


    @Override
    public String toString() {
        return "biglietto N" + ID +
                " creato il " + created_date;
        " venduto da " + sellerId +
                " validato? " + validationId;
    }
}
