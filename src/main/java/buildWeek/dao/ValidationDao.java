package buildWeek.dao;

import buildWeek.entities.Ticket;
import buildWeek.entities.Transport;
import buildWeek.entities.Travel;
import buildWeek.entities.Validation;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;

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

    public void validate(Ticket ticket, Transport transport, Travel travel) {

        Validation validation = new Validation(LocalDate.now(), transport, travel,ticket);
        ticket.setValidation(validation);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(validation);
        em.persist(ticket);
        tx.commit();
        System.out.println("Validation " + validation.getId() + " saved");
    }
}
