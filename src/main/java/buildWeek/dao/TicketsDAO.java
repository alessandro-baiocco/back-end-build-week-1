package buildWeek.dao;

import buildWeek.entities.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
            System.out.println("nuovo abbonamento creato correttamente");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }


    public Ticket getById(int id) {
        return em.find(Ticket.class, id);
    }

    public void delete(Ticket ticket) {
                EntityTransaction transaction = em.getTransaction();
                transaction.begin();
                em.remove(ticket);
                transaction.commit();
                System.out.println("l'abbonamento è stato cancellato correttamente");
    }


}