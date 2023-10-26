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
                System.out.println("abbonamento n " + subS.getID() + "creato correttamente");
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


    public void reNew(Subscription subscription, TicketDuration ticketDuration) {
        UserBadgeDao userBadgeDao = new UserBadgeDao(em);
        if (userBadgeDao.isActive(subscription.getUser())) {
            if (isActive(subscription)) {
                if (ticketDuration == TicketDuration.WEEKLY) {
                    subscription.setActivationDate(subscription.getActivationDate().plusDays(7));
                } else {
                    subscription.setActivationDate(subscription.getActivationDate().plusDays(30));
                }
            } else {
                subscription.setActivationDate(LocalDate.now());
            }
            subscription.setType(ticketDuration);
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(subscription);
            transaction.commit();
            System.out.println("l'abbonamento è rinnovato correttamente");
        } else {
            System.out.println("Devi rinnovare prima la tessera utente.");
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

    public boolean isBadgeActive(Subscription subscription) {
        UserBadgeDao userBadgeDao = new UserBadgeDao(em);
        return userBadgeDao.isActive(subscription.getUser());
    }

}