package buildWeek.dao;

import buildWeek.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class ValidationDao {

    private final EntityManager em;

    public ValidationDao(EntityManager em) {
        this.em = em;
    }

    public void save(Validation validation) {
        if (validation.getTicket().getValidation() == null) {
            try {
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                Ticket ticket = validation.getTicket();
                ticket.setValidation(validation);
                em.persist(validation);
                em.persist(ticket);
                tx.commit();
                System.out.println("Il biglietto " + validation.getTicket().getID() + " è stato timbrato");
            } catch (Exception ex) {
                System.err.println("errore : ");
                System.err.println(ex.getMessage());
            }
        } else {
            System.out.println("Il biglietto " + validation.getTicket().getID() + " è stato già validato!");
        }
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

        Validation validation = new Validation(LocalDate.now(), transport, travel, ticket);
        ticket.setValidation(validation);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(validation);
        em.persist(ticket);
        tx.commit();
        System.out.println("Validation " + validation.getId() + " saved");
    }

    public Validation getRandomValidation() {
        TypedQuery<Validation> query = em.createQuery("SELECT v FROM Validation v ORDER BY RAND()", Validation.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public List<Validation> getAll() {
        TypedQuery<Validation> query = em.createQuery("SELECT v FROM Validation v", Validation.class);
        return query.getResultList();
    }

    public List<Validation> getAll(LocalDate date) {
        TypedQuery<Validation> query = em.createQuery("SELECT v FROM Validation v WHERE v.validationDate = :date", Validation.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    public List<Validation> getAll(LocalDate date1, LocalDate date2) {
        TypedQuery<Validation> query = em.createQuery("SELECT v FROM Validation v WHERE v.validationDate BETWEEN :date1 AND :date2", Validation.class);
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);
        return query.getResultList();
    }

    public List<Validation> getAll(Transport transport) {
        TypedQuery<Validation> query = em.createQuery("SELECT v FROM Validation v WHERE v.transport = :transport", Validation.class);
        query.setParameter("transport", transport);
        return query.getResultList();
    }

    public List<Validation> getAll(Transport transport, LocalDate date) {
        TypedQuery<Validation> query = em.createQuery("SELECT v FROM Validation v WHERE v.transport = :transport AND v.validationDate = :date", Validation.class);
        query.setParameter("transport", transport);
        query.setParameter("date", date);
        return query.getResultList();
    }

    public List<Validation> getAll(Transport transport, LocalDate date1, LocalDate date2) {
        TypedQuery<Validation> query = em.createQuery("SELECT v FROM Validation v WHERE v.transport = :transport AND v.validationDate BETWEEN :date1 AND :date2", Validation.class);
        query.setParameter("transport", transport);
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);
        return query.getResultList();
    }

    public List<Validation> getAll(Route route) {
        TypedQuery<Validation> query = em.createQuery("SELECT v FROM Validation v WHERE v.travel.route = :route", Validation.class);
        query.setParameter("route", route);
        return query.getResultList();
    }

    public List<Validation> getAll(Route route, LocalDate date) {
        TypedQuery<Validation> query = em.createQuery("SELECT v FROM Validation v WHERE v.travel.route = :route AND v.validationDate = :date", Validation.class);
        query.setParameter("route", route);
        query.setParameter("date", date);
        return query.getResultList();
    }

    public List<Validation> getAll(Route route, LocalDate date1, LocalDate date2) {
        TypedQuery<Validation> query = em.createQuery("SELECT v FROM Validation v WHERE v.travel.route = :route AND v.validationDate BETWEEN :date1 AND :date2", Validation.class);
        query.setParameter("route", route);
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);
        return query.getResultList();
    }

}
