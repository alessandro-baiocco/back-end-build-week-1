package buildWeek.dao;

import buildWeek.entities.Route;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class RouteDAO {
    private final EntityManager em;

    public RouteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Route route) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(route);
            transaction.commit();
            System.out.println("La tratta è stata correttamente inserita");
        } catch (Exception ex) {
            System.err.println("errore :\n" + ex.getMessage());
        }

    }

    public Route getById(int id) {
        return em.find(Route.class, id);
    }

    public Route getRandomRoute() {
        TypedQuery<Route> query = em.createQuery("SELECT r FROM Route r ORDER BY RAND()", Route.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public Route findTravelForThis(String start, String destination) {
        try {
            TypedQuery<Route> query = em.createQuery("SELECT t FROM Route t WHERE LOWER(t.startRoutePlace) LIKE LOWER(CONCAT(:start, '%')) AND LOWER(t.endRoutePlace) LIKE LOWER(CONCAT(:destination , '%'))", Route.class);
            query.setParameter("destination", destination);
            query.setParameter("start", start);
            query.setMaxResults(1);
            return query.getSingleResult();
        } catch (NoResultException ex) {
            System.err.println("rotta non trovata");
            return null;
        }
    }
    
    public Boolean transportIsActive(Route route) {
        if (!route.getTransports().isEmpty()) {
            Boolean work = route.getTransports().get(0).isActive();
            if (!work) System.out.println("mezzo in manutenzione");
            return work;
        } else {
            System.out.println("non ci sono mezzi disponibili");
            return false;
        }
    }

    public List<Route> getAll() {
        TypedQuery<Route> query = em.createQuery("SELECT r FROM Route r", Route.class);
        return query.getResultList();
    }

}
