package buildWeek.dao;

import buildWeek.entities.Subscription;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

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


    public Subscription get(int id) {
        return em.find(Subscription.class, id);
    }

    public void delete(int id) {
        Subscription founded = em.find(Subscription.class, id);
        try {
            if (founded != null) {
                EntityTransaction transaction = em.getTransaction();
                transaction.begin();
                em.remove(founded);
                transaction.commit();
                System.out.println("l'abbonamento è stato cancellato correttamente");
            } else {
                System.err.println("l'abbonamento non è stato trovato");
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }


    public Subscription getRandomSubscription() {
        TypedQuery<Subscription> query = em.createQuery("SELECT s FROM Subscription s ORDER BY RAND()", Subscription.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }


}