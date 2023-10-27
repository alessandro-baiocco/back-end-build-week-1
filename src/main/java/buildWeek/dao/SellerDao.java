package buildWeek.dao;

import buildWeek.entities.Seller;
import buildWeek.entities.Subscription;
import buildWeek.entities.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class SellerDao {
    private final EntityManager em;

    public SellerDao(EntityManager em) {
        this.em = em;
    }

    public void save(Seller s) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            em.persist(s);

            transaction.commit();
            System.out.println("Nuovo seller salvato correttamente");
        } catch (Exception ex) {
            System.err.println("errore :");
            System.err.println(ex.getMessage());
        }

    }

    public Seller getById(int id) {
        return em.find(Seller.class, id);
    }

    public void delete(Seller seller) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(seller);
        transaction.commit();
        System.out.println("Il seller è stato cancellato correttamente");
    }

    public Seller getRandomSeller() {
        TypedQuery<Seller> query = em.createQuery("SELECT s FROM Seller s ORDER BY RAND()", Seller.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public boolean isActive(Seller seller) {
        return seller.isLicensed();
    }

    public int soldTickets(Seller seller) {
        TypedQuery<Ticket> query = em.createQuery("SELECT t FROM Ticket t WHERE t.seller = :seller", Ticket.class);
        query.setParameter("seller", seller);
        return query.getResultList().size();
    }

    public int soldTicketsDay(Seller seller, LocalDate date) {
        TypedQuery<Ticket> query = em.createQuery("SELECT t FROM Ticket t WHERE t.seller = :seller AND t.createdDate = :date", Ticket.class);
        query.setParameter("seller", seller);
        query.setParameter("date", date);
        return query.getResultList().size();
    }

    public int soldTicketsPeriod(Seller seller, LocalDate date1, LocalDate date2) {
        TypedQuery<Ticket> query = em.createQuery("SELECT t FROM Ticket t WHERE t.seller = :seller AND t.createdDate BETWEEN :date1 AND :date2", Ticket.class);
        query.setParameter("seller", seller);
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);
        return query.getResultList().size();
    }

    public int soldSubscriptions(Seller seller) {
        TypedQuery<Subscription> query = em.createQuery("SELECT s FROM Subscription s WHERE s.seller = :seller", Subscription.class);
        query.setParameter("seller", seller);
        return query.getResultList().size();
    }

    public int soldSubscriptions(Seller seller, LocalDate date) {
        TypedQuery<Subscription> query = em.createQuery("SELECT s FROM Subscription s WHERE s.seller = :seller AND s.createdDate = :date", Subscription.class);
        query.setParameter("seller", seller);
        query.setParameter("date", date);
        return query.getResultList().size();
    }

    public int soldSubscriptions(Seller seller, LocalDate date1, LocalDate date2) {
        TypedQuery<Subscription> query = em.createQuery("SELECT s FROM Subscription s WHERE s.seller = :seller AND s.createdDate BETWEEN :date1 AND :date2", Subscription.class);
        query.setParameter("seller", seller);
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);
        return query.getResultList().size();
    }

    public Seller getRandomSell() {
        TypedQuery<Seller> query = em.createQuery("SELECT s FROM Seller s ORDER BY RAND()", Seller.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public List<Seller> distributorsActive() {
        TypedQuery<Seller> query = em.createQuery("SELECT s FROM Seller s WHERE s.licensed is true", Seller.class);
        return query.getResultList();
    }


    public List<Seller> getAll() {
        TypedQuery<Seller> query = em.createQuery("SELECT s FROM Seller s", Seller.class);
        return query.getResultList();
    }

    public List<Seller> getAllAuto() {
        TypedQuery<Seller> query = em.createQuery("SELECT s FROM Seller s WHERE s.", Seller.class);
        return query.getResultList();
    }

}
