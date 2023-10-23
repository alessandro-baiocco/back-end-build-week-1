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
    private Seller sellerId;


    public TicketsItem() {
    }

    public TicketsItem(int ID, LocalDate createdDate, Seller sellerId) {
        this.ID = ID;
        this.createdDate = createdDate;
        this.sellerId = sellerId;
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

    public Seller getSellerId() {
        return sellerId;
    }

    public void setSellerId(Seller sellerId) {
        this.sellerId = sellerId;
    }


    @Override
    public String toString() {
        return "biglietto N." + ID +
                " creato il " + createdDate +
                " venduto da " + sellerId;
    }
}
