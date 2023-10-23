package buildWeek.dao;

import buildWeek.entities.Seller;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class SellerDao {
    private final EntityManager em;

    public SellerDao(EntityManager em) {
        this.em = em;
    }

    public void save(Seller s) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(s);

        transaction.commit();
        System.out.println("Nuovo seller salvato correttamente");
    }

    public Seller getById(int id) {
        return em.find(Seller.class, id);
    }


    public void getByIsbnAndDelete(int id) {

        Seller found = em.find(Seller.class, id);

        if (found != null) {
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("Il seller è stato cancellato correttamente");
        } else {
            System.err.println("Il seller con l'id " + id + " non è stato trovato");
        }
    }



        public void getByIdAndDelete(int id) {

            Seller found = em.find(Seller.class, id);

            if (found != null) {
                EntityTransaction transaction = em.getTransaction();

                transaction.begin();
                em.remove(found);
                transaction.commit();
                System.out.println("Il seller è stato cancellato correttamente");
            } else {
                System.err.println("Il seller con l'id " + id + " non è stato trovato");
            }

        }

            public Seller getRandomSeller() {
        TypedQuery<Seller> query = em.createQuery("SELECT s FROM Seller s ORDER BY RAND()", Seller.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }



    }
