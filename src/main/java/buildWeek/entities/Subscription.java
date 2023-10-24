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
    @JoinColumn(name = "user_id")
    private UserBadge user;

    public Subscription() {
    }


    public Subscription(LocalDate createdDate, Seller seller, TicketDuration type, LocalDate activationDate, UserBadge user) {
        super(createdDate, seller);
        this.type = type;
        this.activationDate = activationDate;
        this.user = user;
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

    public UserBadge getUser() {
        return user;
    }

    public void setUser(UserBadge user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "abbonamento di tipo " + type +
                " attivo da " + activationDate +
                " numero utente" + user + super.toString()
                + super.toString();
    }


}
