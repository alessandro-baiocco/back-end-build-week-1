package buildWeek.dao;

import buildWeek.entities.Transport;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class TransportDao {
    private final EntityManager em;

    public TransportDao(EntityManager em) {
        this.em = em;
    }

    public void save(Transport transport) {
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(transport);
            tx.commit();
            System.out.println("Transport " + transport.getId() + " saved");
        } catch (Exception ex) {
            System.err.println("errore : " + ex.getMessage());
        }

    }

    public Transport getById(int id) {
        return em.find(Transport.class, id);
    }

    public List<Transport> getAll() {
        TypedQuery<Transport> query = em.createQuery("SELECT t FROM Transport t", Transport.class);
        return query.getResultList();
    }

    public Transport getRandomTransport() {
        TypedQuery<Transport> query = em.createQuery("SELECT t FROM Transport t ORDER BY RAND()", Transport.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

}


