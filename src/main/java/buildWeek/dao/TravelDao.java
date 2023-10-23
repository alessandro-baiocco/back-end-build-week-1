package buildWeek.dao;

<<<<<<< HEAD
import buildWeek.entities.Route;
=======
>>>>>>> 1161292cd224fe969c47d464bb80dd894fcef840
import buildWeek.entities.Travel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TravelDao {
<<<<<<< HEAD
    private final EntityManager entityManager;

    public TravelDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Travel travel) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(travel);
        transaction.commit();
        System.out.println("Il viaggio è stato correttamente inserito");
    }

    public Travel findById(int id) {
        return entityManager.find(Travel.class, id);
    }

    public void delete(int id) {
        Travel routeToBeDeleted = entityManager.find(Travel.class, id);

        if (routeToBeDeleted != null) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(routeToBeDeleted);
            transaction.commit();
            System.out.println("Questo viaggio è stato cancellato con successo!");
        } else {
            System.out.println("Viaggio non presente nel database");
        }
    }
=======

    private final EntityManager em;

    public TravelDao(EntityManager em) {
        this.em = em;
    }

    private void save(Travel travel) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(travel);
        tx.commit();
        System.out.println("Travels " + travel.getId() + " saved");
    }

    private Travel getById(int id) {
        return em.find(Travel.class, id);
    }

    private void delete(Travel travel) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(travel);
        tx.commit();
        System.out.println("Travels " + travel.getId() + " deleted");
    }

    private void refresh(Travel travel) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.refresh(travel);
        tx.commit();
        System.out.println("Travels " + travel.getId() + " refreshed");
    }

>>>>>>> 1161292cd224fe969c47d464bb80dd894fcef840
}
