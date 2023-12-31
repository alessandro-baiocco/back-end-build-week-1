package buildWeek.dao;

import buildWeek.entities.Subscription;
import buildWeek.enums.TicketDuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
                System.out.println("abbonamento n " + subS.getID() + " creato correttamente");
            } catch (Exception ex) {
                System.err.println("Errore : \n" + ex.getMessage());
            }
        } else {
            System.out.println("Rinnova la tessera prima tirchio!");
        }
    }


    public Subscription getById(int id) {
        return em.find(Subscription.class, id);
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
            try {
                EntityTransaction transaction = em.getTransaction();
                transaction.begin();
                em.persist(subscription);
                transaction.commit();
                System.out.println("l'abbonamento è stato rinnovato correttamente");
            } catch (Exception ex) {
                System.err.println("Errore : \n" + ex.getMessage());
            }
        } else {
            System.out.println("Devi rinnovare prima la tessera utente.");
        }
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

    public List<Subscription> getAll() {
        TypedQuery<Subscription> query = em.createQuery("SELECT s FROM Subscription s", Subscription.class);
        return query.getResultList();
    }

    public int soldSubscription(LocalDate date) {
        TypedQuery<Subscription> query = em.createQuery("SELECT s FROM Subscription s WHERE s.createdDate = :date", Subscription.class);
        query.setParameter("date", date);
        return query.getResultList().size();
    }

    public int soldSubscription(LocalDate date1, LocalDate date2) {
        TypedQuery<Subscription> query = em.createQuery("SELECT s FROM Subscription s WHERE s.createdDate BETWEEN :date1 AND :date2", Subscription.class);
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);
        return query.getResultList().size();
    }

}