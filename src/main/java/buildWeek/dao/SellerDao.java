package buildWeek.dao;

import buildWeek.entities.Seller;
import buildWeek.entities.Subscription;

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
            System.out.println("Nuovo venditore " + s.getId() + " salvato correttamente");
        } catch (Exception ex) {
            System.err.println("errore : \n" + ex.getMessage());
        }

    }

    public Seller getById(int id) {
        return em.find(Seller.class, id);
    }

    public Seller getRandomSeller() {
        TypedQuery<Seller> query = em.createQuery("SELECT s FROM Seller s ORDER BY RAND()", Seller.class);
        query.setMaxResults(1);
        return query.getSingleResult();
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

    public List<Seller> getAll() {
        TypedQuery<Seller> query = em.createQuery("SELECT s FROM Seller s", Seller.class);
        return query.getResultList();
    }

    public void setLicenceActiveAgain (Seller seller) {
        seller.setLicensed(true);
        try{
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(seller);
            transaction.commit();
            System.out.println("Il venditore " + seller.getId() + " ha di nuovo la licenza");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void revokeLicence (Seller seller) {
        seller.setLicensed(false);
        try{
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(seller);
            transaction.commit();
            System.out.println("La licenza del venditore " + seller.getId() +  "è stata revocata");
        } catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }

}
