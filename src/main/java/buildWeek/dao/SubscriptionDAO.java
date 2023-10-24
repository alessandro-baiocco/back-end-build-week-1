package buildWeek.dao;

import buildWeek.entities.Subscription;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.Period;

public class SubscriptionDAO {
    private final EntityManager em;

    public SubscriptionDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Subscription subS) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(subS);
            transaction.commit();
            System.out.println("nuovo abbonamento creato correttamente");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
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
        Subscription founded = em.find(Subscription.class, id);
        try {
            if (founded != null) {
                founded.setActivationDate(founded.getActivationDate().plusYears(1));
                EntityTransaction transaction = em.getTransaction();
                transaction.begin();
                em.persist(founded);
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

    public boolean isActive(int id) {
        Subscription founded = em.find(Subscription.class, id);
        if (founded != null) {
            return Period.between(LocalDate.now(), founded.getActivationDate()).getDays() < 365;
        } else {
            System.err.println("not found");
            return false;
        }
    }

}