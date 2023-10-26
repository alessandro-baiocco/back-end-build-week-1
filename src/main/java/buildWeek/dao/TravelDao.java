package buildWeek.dao;

import buildWeek.entities.Route;
import buildWeek.entities.Transport;
import buildWeek.entities.Travel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;

public class TravelDao {
    private final EntityManager em;

    public TravelDao(EntityManager em) {
        this.em = em;
    }

    public void save(Travel travel) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(travel);
        transaction.commit();
        System.out.println("Il viaggio è stato correttamente inserito");
    }

    public Travel getById(int id) {
        return em.find(Travel.class, id);
    }

    public void delete(Travel travel) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(travel);
        transaction.commit();
        System.out.println("Questo viaggio è stato cancellato con successo!");
    }


    public void refresh(Travel travel) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.refresh(travel);
        tx.commit();
        System.out.println("Viaggio " + travel.getId() + " refreshed");
    }

    public int timesTraveled(Route route, Transport transport) {
        TypedQuery<Travel> query = em.createQuery("SELECT t FROM Travel t WHERE t.route = :route AND t.transport = : transport", Travel.class);
        query.setParameter("transport", transport);
        query.setParameter("route", route);
        return query.getResultList().size();
    }

    public int timesTraveledDay(Route route, Transport transport, LocalDate date) {
        TypedQuery<Transport> query = em.createQuery("SELECT t FROM Travel t WHERE t.route = :route AND t.transport = : transport AND t.date = :date", Transport.class);
        query.setParameter("transport", transport);
        query.setParameter("date", date);
        query.setParameter("route", route);
        return query.getResultList().size();
    }

    public int timesTraveledPeriod(Route route, Transport transport, LocalDate date1, LocalDate date2) {
        TypedQuery<Transport> query = em.createQuery("SELECT t FROM Travel t WHERE t.route = :route AND t.transport = : transport AND t.date BETWEEN :date1 AND :date2", Transport.class);
        query.setParameter("route", route);
        query.setParameter("transport", transport);
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);
        return query.getResultList().size();
    }


    public Travel getRandomTravel() {
        TypedQuery<Travel> query = em.createQuery("SELECT t FROM Travel t ORDER BY RAND()", Travel.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

}

