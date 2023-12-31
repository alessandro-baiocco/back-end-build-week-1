package buildWeek.dao;

import buildWeek.entities.Route;
import buildWeek.entities.Transport;
import buildWeek.entities.Travel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import java.util.List;

public class TravelDao {
    private final EntityManager em;

    public TravelDao(EntityManager em) {
        this.em = em;
    }

    public void save(Travel travel) {
        try {
            EntityTransaction transaction = em.getTransaction();
            Transport newTran = travel.getTransport();
            newTran.setRoute(travel.getRoute());
            transaction.begin();
            em.persist(travel);
            em.persist(newTran);
            transaction.commit();
            System.out.println("Il viaggio è stato correttamente inserito");
        } catch (RollbackException ex) {
            System.err.println("errore di creazione");
        } catch (Exception ex) {
            System.err.println("Errore :\n" + ex.getMessage());
        }

    }

    public Travel getById(int id) {
        return em.find(Travel.class, id);
    }

    public int timesTraveled(Route route, Transport transport) {
        TypedQuery<Travel> query = em.createQuery("SELECT t FROM Travel t WHERE t.route = :route AND t.transport = : transport", Travel.class);
        query.setParameter("transport", transport);
        query.setParameter("route", route);
        return query.getResultList().size();
    }

    public Travel getRandomTravel() {
        TypedQuery<Travel> query = em.createQuery("SELECT t FROM Travel t ORDER BY RAND()", Travel.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public List<Travel> getAll() {
        TypedQuery<Travel> query = em.createQuery("SELECT t FROM Travel t", Travel.class);
        return query.getResultList();
    }

    public List<Travel> getAllSpecifyTravels(Route route, Transport transport) {
        TypedQuery<Travel> query = em.createQuery("SELECT t FROM Travel t WHERE t.route = :route AND t.transport = :transport ", Travel.class);
        query.setParameter("route", route);
        query.setParameter("transport", transport);
        return query.getResultList();
    }

}

