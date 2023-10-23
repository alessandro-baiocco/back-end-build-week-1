package buildWeek.dao;

import buildWeek.entities.Transport;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TransportDao {
    private final EntityManager em;

    public TransportDao(EntityManager em) {
        this.em = em;
    }

    public void save(Transport transport) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(transport);
        tx.commit();
        System.out.println("Transport " + transport.getId() + " saved");
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

}
