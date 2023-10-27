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
    @ManyToOne
    @JoinColumn(name = "user_badge_id", nullable = false)
    private UserBadge userBadge;

    public Subscription() {
    }


    public Subscription(LocalDate createdDate, Seller seller, TicketDuration type, LocalDate activationDate, UserBadge userBadge) {
        super(createdDate, seller);
        this.type = type;
        this.activationDate = activationDate;
        this.userBadge = userBadge;
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
        return userBadge;
    }

    public void setUser(UserBadge userBadge) {
        this.userBadge = userBadge;
    }

    @Override
    public String toString() {
        return "abbonamento di tipo " + type +
                " attivo da " + activationDate +
                " numero utente" + super.toString();

    }


}
