package buildWeek.dao;

import buildWeek.entities.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ServiceDao {

    private final EntityManager em;

    public ServiceDao(EntityManager em) {
        this.em = em;
    }


public void save(Service service) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(service);
        tx.commit();
        System.out.println("Service " + service.getId() + " saved");
    }

    public Service getById(int id) {
        return em.find(Service.class, id);
    }

    public void delete(Service service) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(service);
        tx.commit();
        System.out.println("Service " + service.getId() + " deleted");
    }

    public void refresh(Service service) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.refresh(service);
        tx.commit();
        System.out.println("Service " + service.getId() + " refreshed");
    }

}
