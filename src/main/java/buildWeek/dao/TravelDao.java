package buildWeek.dao;

import buildWeek.entities.Travel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TravelDao {

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

}
