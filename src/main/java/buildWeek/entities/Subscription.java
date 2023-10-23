package buildWeek.entities;


import enums.TicketDuration;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Entity
public class Subscription extends Tickets {
    @Enumerated(EnumType.STRING)
    private TicketDuration type;
    private LocalDate activationDate;
    private UserBadge userId;


    public Subscription() {
    }

    public Subscription(LocalDate createDate, TicketDuration type, Seller sellerId, LocalDate activationDate, UserBadge userId) {
        this.createDate = createDate;
        this.type = type;
        this.sellerId = sellerId;
        this.activationDate = activationDate;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public TicketDuration getType() {
        return type;
    }

    public void setType(TicketDuration type) {
        this.type = type;
    }

    public Seller getSellerId() {
        return sellerId;
    }

    public void setSellerId(Seller sellerId) {
        this.sellerId = sellerId;
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
        return "biglietto N." + id +
                " creato il " + createDate +
                " tipo" + type;
        " venduto da " + sellerId +
                " attivato il" + activationDate +
                " numero utente" + userId;
    }
}
