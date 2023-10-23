package buildWeek.dao;

import buildWeek.entities.Travel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TravelDao {
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


    public void refresh(Travel travel) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.refresh(travel);
        tx.commit();
        System.out.println("Viaggio " + travel.getId() + " refreshed");


}
