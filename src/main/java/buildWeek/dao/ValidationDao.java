package buildWeek.dao;

import buildWeek.entities.Validation;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ValidationDao {

    private final EntityManager em;

    public ValidationDao(EntityManager em) {
        this.em = em;
    }

    public void save(Validation validation) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(validation);
        tx.commit();
        System.out.println("Validation " + validation.getId() + " saved");
    }


    public Validation getById(int id) {
        return em.find(Validation.class, id);
    }

    public void delete(Validation validation) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(validation);
        tx.commit();
        System.out.println("Validation " + validation.getId() + " deleted");
    }

    public void refresh(Validation validation) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.refresh(validation);
        tx.commit();
        System.out.println("Validation " + validation.getId() + " refreshed");
    }
}
