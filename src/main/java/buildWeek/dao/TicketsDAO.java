package buildWeek.dao;

import buildWeek.entities.Seller;
import buildWeek.entities.Ticket;
import buildWeek.entities.TicketsItem;

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

    public List<TicketsItem> getAllSoldTicket(LocalDate selectedDate, LocalDate secondDate) {

        TypedQuery<TicketsItem> query = em.createQuery("SELECT z FROM TicketsItem z WHERE z.createdDate BETWEEN :selectedDate AND :secondDate", TicketsItem.class);
        query.setParameter("selectedDate", selectedDate);
        query.setParameter("secondDate", secondDate);
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




