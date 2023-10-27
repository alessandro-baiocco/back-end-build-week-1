package buildWeek.dao;

import buildWeek.entities.Seller;
import buildWeek.entities.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;


public class TicketsDAO {
    private final EntityManager em;

    public TicketsDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Ticket tick) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(tick);
            transaction.commit();
            System.out.println("ticket n " + tick.getID() + " creato correttamente");
        } catch (Exception ex) {
            System.err.println("Errore : \n" + ex.getMessage());
        }
    }


    public Ticket getById(int id) {
        return em.find(Ticket.class, id);
    }

    public List<Ticket> getAllSoldTicket(Seller seller) {
        TypedQuery<Ticket> query = em.createQuery("SELECT z FROM Ticket z WHERE z.seller = :seller", Ticket.class);
        query.setParameter("seller", seller);
        return query.getResultList();
    }

    public List<Ticket> getAll() {
        TypedQuery<Ticket> query = em.createQuery("SELECT z FROM Ticket z", Ticket.class);
        return query.getResultList();
    }

    public List<Ticket> getAllSoldTicket(Seller seller, LocalDate date) {
        TypedQuery<Ticket> query = em.createQuery("SELECT z FROM Ticket z WHERE z.createdDate = :date AND z.seller = :seller", Ticket.class);
        query.setParameter("date", date);
        query.setParameter("seller", seller);
        return query.getResultList();
    }

    public List<Ticket> getAllSoldTicket(Seller seller, LocalDate date1, LocalDate date2) {
        TypedQuery<Ticket> query = em.createQuery("SELECT t FROM Ticket t WHERE t.createdDate BETWEEN :date1 AND :date2 AND t.seller = :seller", Ticket.class);
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);
        query.setParameter("seller", seller);
        return query.getResultList();
    }

    public Ticket getRandomTicket() {
        TypedQuery<Ticket> query = em.createQuery("SELECT t FROM Ticket t ORDER BY RAND()", Ticket.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public boolean tickIsNotValidated(Ticket tick) {
        return tick.getValidation() == null;
    }

}








