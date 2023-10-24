package buildWeek.dao;

import buildWeek.entities.Ticket;
import buildWeek.entities.Transport;
import buildWeek.entities.Validation;

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
            System.out.println("nuovo ticket creato correttamente");
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
        System.out.println("Il ticket è stato cancellato correttamente");
    }

    public Ticket getRandomTicket() {
        TypedQuery<Ticket> query = em.createQuery("SELECT t FROM Ticket t ORDER BY RAND()", Ticket.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public void getValidatedTicketsOnTransport(Transport transport) {
        TypedQuery<Validation> query = em.createQuery("SELECT v FROM Validation v WHERE v.transport = :transport", Validation.class);
        query.setParameter("transport", transport);
        if (!query.getResultList().isEmpty()) {
            query.getResultList().forEach(v -> {
                System.out.println("Validation : " + v.getId());
                System.out.println("Transport : " + v.getTransport().getId());
                System.out.println("Ticket : " + v.getTicket().getID());
            });
            System.out.println("numero ticket vidimati sul mezzo  " + transport.getId() + " :" + query.getResultList().size());
        } else {
            System.out.println("Non ci sono tickets vidimati su questo mezzo");
        }
    }

    public void getValidatedTicketByDate(LocalDate date1, LocalDate date2) {
        TypedQuery<Ticket> query = em.createQuery("SELECT t FROM Ticket t WHERE t.validation IS NOT NULL " +
                "AND t.validation.validationDate BETWEEN :date1 AND :date2", Ticket.class);
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);
        List<Ticket> tickets = query.getResultList();
        tickets.forEach(t ->
                {
                    System.out.println(t);
                    System.out.println("Data di validazione: " + t.getValidation().getValidationDate());
                }
        );

    }

}