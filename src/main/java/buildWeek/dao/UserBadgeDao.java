package buildWeek.dao;

import buildWeek.entities.UserBadge;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UserBadgeDao {
    private final EntityManager em;

    public UserBadgeDao(EntityManager em) {
        this.em = em;
    }

    public void save(UserBadge u) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            em.persist(u);

            transaction.commit();
            System.out.println("Nuovo user salvato correttamente");
        } catch (Exception ex) {
            System.err.println("errore : ");
            System.err.println(ex.getMessage());
        }

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

    public boolean isActive(int id) {
        UserBadge found = em.find(UserBadge.class, id);
        if (found != null) {
            return ChronoUnit.DAYS.between(found.getActivationDate(), LocalDate.now()) < 365;
        } else {
            System.err.println("not found");
            return false;
        }
    }

    public boolean isActive(UserBadge userBadge) {
        return ChronoUnit.DAYS.between(userBadge.getActivationDate(), LocalDate.now()) < 365;
    }

    public void reNewUserBadge(UserBadge userBadge) {
        if (isActive(userBadge.getBadge_id())) {
            try {
                userBadge.setActivationDate(userBadge.getActivationDate().plusYears(1));
                EntityTransaction transaction = em.getTransaction();
                transaction.begin();
                em.persist(userBadge);
                transaction.commit();
                System.out.println("La tessera utente è stata rinnovata correttamente");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            try {
                userBadge.setActivationDate(LocalDate.now());
                EntityTransaction transaction = em.getTransaction();
                transaction.begin();
                em.persist(userBadge);
                transaction.commit();
                System.out.println("La tessera utente è stata rinnovata correttamente");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }

    }

}
