package buildWeek.dao;

import buildWeek.entities.UserBadge;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

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


    public void delete(UserBadge userBadge) {

            EntityTransaction transaction = em.getTransaction();

            transaction.begin();
            em.remove(userBadge);
            transaction.commit();
            System.out.println("Lo user è stato cancellato correttamente");

    }

    public UserBadge getRandomUserBadge() {
        TypedQuery<UserBadge> query = em.createQuery("SELECT u FROM UserBadge u ORDER BY RAND()", UserBadge.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
