package buildWeek.dao;

import buildWeek.entities.Seller;
import buildWeek.entities.UserBadge;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserBadgeDao {
    private final EntityManager em;

    public UserBadgeDao(EntityManager em) {
        this.em = em;
    }

    public void save(UserBadge u) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(u);

        transaction.commit();
        System.out.println("Nuovo user salvato correttamente");
    }

    public UserBadge getById(int id) {
        return em.find(UserBadge.class, id);
    }


    public void getByIdAndDelete(int id) {

        UserBadge found = em.find(UserBadge.class, id);

        if (found != null) {
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("Lo user è stato cancellato correttamente");
        } else {
            System.err.println("Lo user con id " + id + " non è stato trovato");
        }

    }
}
