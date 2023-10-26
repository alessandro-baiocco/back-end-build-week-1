package buildWeek.dao;

import buildWeek.entities.Route;
import buildWeek.entities.Transport;
import buildWeek.entities.Travel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO {
    private final EntityManager em;

    public RouteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Route route) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(route);
        transaction.commit();
        System.out.println("La tratta è stata correttamente inserita");
    }

    public Route getById(int id) {
        return em.find(Route.class, id);
    }

    public void delete(int id) {
        Route routeToBeDeleted = em.find(Route.class, id);

        if (routeToBeDeleted != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(routeToBeDeleted);
            transaction.commit();
            System.out.println("Questa tratta è stata cancellata con successo!");
        } else {
            System.out.println("Tratta non presente nel database");
        }
    }

    public Travel getRandomTravel() {
        TypedQuery<Travel> query = em.createQuery("SELECT t FROM Travel t ORDER BY RAND()", Travel.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public Transport getRandomTrans() {
        TypedQuery<Transport> query = em.createQuery("SELECT t FROM Transport t ORDER BY RAND()", Transport.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }


    public void setTravel(Route route, Travel travel) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<Travel> travelList = new ArrayList<>();
        if (route.getTravel() != null) {
            travelList.addAll(route.getTravel());
        }
        travelList.add(travel);
        try {
            if (travelList != null) {
                route.setTravel(travelList);
                em.persist(route);
                System.out.println("Viaggo aggiunto correttamente alla rotta : " + route.getId());
                transaction.commit();
            } else {
                System.out.println("E' successo qualcosa per cui non possiamo inserire il viaggio alla rotta.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void setTrans() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        TypedQuery<Route> getAllRoute = em.createQuery("SELECT t FROM Route t", Route.class); // Query JPQL

        List<Route> routeList = getAllRoute.getResultList();
        routeList.forEach(t -> t.setMeansOfTransport(getRandomTrans()));

        transaction.commit();

    }

    public int timesTraveled(Transport transport) {
        TypedQuery<Transport> query = em.createQuery("SELECT r FROM Route r WHERE r.transport = :transport", Transport.class);
        query.setParameter("transport", transport);
        return query.getResultList().size();
    }

    public Route getRandomRoute() {
        TypedQuery<Route> query = em.createQuery("SELECT r FROM Route r ORDER BY RAND()", Route.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }


    public Route findTravelForThis(String start, String destination) {
        TypedQuery<Route> query = em.createQuery("SELECT r FROM Route t WHERE LOWER(t.startRoutePlace) LIKE LOWER(CONCAT(:start, '%')) AND LOWER(t.endRoutePlace) LIKE LOWER(CONCAT(:destination , '%'))", Route.class);
        query.setParameter("destination", destination);
        query.setParameter("start", start);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public List<Route> getAll() {
        TypedQuery<Route> query = em.createQuery("SELECT r FROM Route r", Route.class);
        return query.getResultList();
    }

    public List<Route> getAll(Transport transport) {
        TypedQuery<Route> query = em.createQuery("SELECT r FROM Route r WHERE r.transport = :transport", Route.class);
        query.setParameter("transport", transport);
        return query.getResultList();
    }
}
