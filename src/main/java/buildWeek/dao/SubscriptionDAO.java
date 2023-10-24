package buildWeek.dao;

import buildWeek.entities.Subscription;
import buildWeek.enums.TicketDuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SubscriptionDAO {
    private final EntityManager em;

    public SubscriptionDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Subscription subS) {
        EntityTransaction transaction = em.getTransaction();
        UserBadgeDao ud = new UserBadgeDao(em);
        boolean isActive = ud.isActive(subS.getUser());
        if (isActive) {
            try {
                transaction.begin();
                em.persist(subS);
                transaction.commit();
                System.out.println("nuovo abbonamento creato correttamente");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            System.out.println("Rinnova la tessera prima tirchio!");
        }
    }


    public Subscription getById(int id) {
        return em.find(Subscription.class, id);
    }

    public void delete(Subscription subscription) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(subscription);
        transaction.commit();
        System.out.println("l'abbonamento è stato cancellato correttamente");
    }


    public void reNew(int id) {
        Subscription found = em.find(Subscription.class, id);
        try {
            if (found != null) {
                found.setActivationDate(found.getActivationDate().plusYears(1));
                EntityTransaction transaction = em.getTransaction();
                transaction.begin();
                em.persist(found);
                transaction.commit();
                System.out.println("l'abbonamento è rinnovato correttamente");
            } else {
                System.err.println("l'abbonamento non è stato trovato");
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public LocalDate getExpirationDate(int id) {
        Subscription founded = em.find(Subscription.class, id);
        try {
            if (founded != null) {
                return founded.getActivationDate();
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.err.println("l'abbonamento non è stato trovato");
            System.err.println(ex.getMessage());
            return null;
        }
    }


    public Subscription getRandomSubscription() {
        TypedQuery<Subscription> query = em.createQuery("SELECT s FROM Subscription s ORDER BY RAND()", Subscription.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public boolean isActive(Subscription subscription) {

        if (subscription.getType() == TicketDuration.WEEKLY) {
            return ChronoUnit.DAYS.between(subscription.getActivationDate(), LocalDate.now()) < 8;
        } else if (subscription.getType() == TicketDuration.MONTHLY) {
            return ChronoUnit.DAYS.between(subscription.getActivationDate(), LocalDate.now()) < 31;
        } else {
            System.err.println("not found");
            return false;
        }
    }

}