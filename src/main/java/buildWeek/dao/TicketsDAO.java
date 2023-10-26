package buildWeek.dao;

import buildWeek.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


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
            System.out.println(tick.getID());
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

    public List<Ticket> getAllSoldTicket(Seller seller) {
        TypedQuery<Ticket> query = em.createQuery("SELECT z FROM Ticket z WHERE z.seller = :seller", Ticket.class);
        query.setParameter("seller", seller);
        return query.getResultList();
    }

    public List<TicketsItem> getAllSoldTicket(LocalDate selectedDate, String userInput) {

        LocalDate finalDate = selectedDate;
        if (Objects.equals(userInput, "1")) finalDate = selectedDate.plusDays(1);
        else if (Objects.equals(userInput, "2")) finalDate = selectedDate.plusWeeks(1);
        else if (Objects.equals(userInput, "3")) finalDate = selectedDate.plusMonths(1);

        TypedQuery<TicketsItem> query = em.createQuery("SELECT z FROM TicketsItem z WHERE z.createdDate BETWEEN :selectedDate AND :secondDate", TicketsItem.class);
        query.setParameter("selectedDate", selectedDate);
        query.setParameter("secondDate", finalDate);
        return query.getResultList();
    }

    public List<TicketsItem> getAllSoldTicket(LocalDate selectedDate, Seller seller, String userInput) {
// brividi
        LocalDate finalDate = selectedDate;
        if (Objects.equals(userInput, "1")) finalDate = selectedDate.plusDays(1);
        else if (Objects.equals(userInput, "2")) finalDate = selectedDate.plusWeeks(1);
        else if (Objects.equals(userInput, "3")) finalDate = selectedDate.plusMonths(1);

        TypedQuery<TicketsItem> query = em.createQuery("SELECT z FROM TicketsItem z WHERE (z.createdDate BETWEEN :selectedDate AND :secondDate) AND z.seller = :seller", TicketsItem.class);
        query.setParameter("selectedDate", selectedDate);
        query.setParameter("secondDate", finalDate);
        query.setParameter("seller", seller);
        return query.getResultList();
    }

    public List<Ticket> getAll() {
        TypedQuery<Ticket> query = em.createQuery("SELECT z FROM Ticket z", Ticket.class);
        return query.getResultList();
    }

    public List<TicketsItem> getAllSoldTicket(LocalDate selectedDate, LocalDate secondDate) {

        TypedQuery<TicketsItem> query = em.createQuery("SELECT z FROM TicketsItem z WHERE z.createdDate BETWEEN :selectedDate AND :secondDate", TicketsItem.class);
        query.setParameter("selectedDate", selectedDate);
        query.setParameter("secondDate", secondDate);
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
    public boolean tickIsNotValidated(Ticket tick) {
        return tick.getValidation() == null;
    }


}








