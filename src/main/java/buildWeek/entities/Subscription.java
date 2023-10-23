package buildWeek.entities;


import buildWeek.enums.TicketDuration;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "subscriptions")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Subscription extends Tickets {
    @Enumerated(EnumType.STRING)
    private TicketDuration type;
    private LocalDate activationDate;
    @OneToOne
    private UserBadge userId;


    public Subscription() {
    }


    public Subscription(LocalDate created_date, Seller sellerId, Validation validationId, TicketDuration type, LocalDate activationDate, UserBadge userId) {
        super(created_date, sellerId, validationId);
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
