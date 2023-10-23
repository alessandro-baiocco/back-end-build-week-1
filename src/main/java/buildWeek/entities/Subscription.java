package buildWeek.entities;


import buildWeek.enums.TicketDuration;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "subscriptions")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Subscription extends TicketsItem {
    @Enumerated(EnumType.STRING)
    private TicketDuration type;
    @Column(name = "activation_date")
    private LocalDate activationDate;
    @OneToOne
    private UserBadge userId;


    public Subscription() {
    }


    public Subscription(int ID, LocalDate createdDate, Seller sellerId, TicketDuration type, LocalDate activationDate, UserBadge userId) {
        super(ID, createdDate, sellerId);
        this.type = type;
        this.activationDate = activationDate;
        this.userId = userId;
    }

    public TicketDuration getType() {
        return type;
    }

    public void setType(TicketDuration type) {
        this.type = type;
    }


    public LocalDate getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDate activationDate) {
        this.activationDate = activationDate;
    }

    public UserBadge getUserId() {
        return userId;
    }

    public void setUserId(UserBadge userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "abbonamento di tipo " + type +
                " attivo da " + activationDate +
                " numero utente" + userId + super.toString();
    }


}
