package buildWeek.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class TicketsItem {
    @Id
    @GeneratedValue
    private int ID;
    @Column(name = "create_date")
    private LocalDate createdDate;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;


    public TicketsItem() {
    }

    public TicketsItem(LocalDate createdDate, Seller seller) {
        this.createdDate = createdDate;
        this.seller = seller;
    }

    public int getID() {
        return ID;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }


    @Override
    public String toString() {
        return "biglietto N." + ID +
                " creato il " + createdDate +
                " venduto da " + seller;
    }
}
