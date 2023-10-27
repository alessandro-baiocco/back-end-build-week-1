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
            System.err.println("errore : ");
            System.err.println(ex.getMessage());
        }

    }

    public Transport getById(int id) {
        return em.find(Transport.class, id);
    }

    public void delete(Transport transport) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(transport);
        tx.commit();
        System.out.println("Transport " + transport.getId() + " deleted");
    }

    public void refresh(Transport transport) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.refresh(transport);
        tx.commit();
        System.out.println("Transport " + transport.getId() + " refreshed");
    }

    public boolean isAvailable(Transport transport) {
        return transport.isActive();
    }

    public void toggleActive(Transport transport) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        transport.setActive(!transport.isActive());
        tx.commit();
        System.out.println("Transport " + transport.getName() + " status changed in " + transport.isActive());
    }

    public List<Transport> getAllAvailable() {
        TypedQuery<Transport> query = em.createQuery("SELECT t FROM Transport t WHERE t.active = true", Transport.class);
        return query.getResultList();
    }

    public List<Transport> getAll() {
        TypedQuery<Transport> query = em.createQuery("SELECT t FROM Transport t", Transport.class);
        return query.getResultList();
    }

    public List<Transport> getAllInService() {
        TypedQuery<Transport> query = em.createQuery("SELECT t FROM Transport t WHERE t.active = false", Transport.class);
        return query.getResultList();
    }

    public Transport getRandomTransport() {
        TypedQuery<Transport> query = em.createQuery("SELECT t FROM Transport t ORDER BY RAND()", Transport.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }


}


