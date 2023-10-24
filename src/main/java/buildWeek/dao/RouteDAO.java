package buildWeek.dao;

import buildWeek.entities.Route;
import buildWeek.entities.Transport;
import buildWeek.entities.Travel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
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

    public Route findById(int id) {
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


    public void setTravel() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        TypedQuery<Route> getAllRoute = em.createQuery("SELECT t FROM Route t", Route.class); // Query JPQL
        List<Route> routeList = getAllRoute.getResultList();
        routeList.forEach(t -> t.setTravel(getRandomTravel()));
        // 2. Eseguo la query
        // 3. Termino la transazione col salvataggio effettivo di una nuova riga nella tabella students
        transaction.commit();

    }

    public void setTrans() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        TypedQuery<Route> getAllRoute = em.createQuery("SELECT t FROM Route t", Route.class); // Query JPQL
        List<Route> routeList = getAllRoute.getResultList();
        routeList.forEach(t -> t.setMeansOfTransport(getRandomTrans()));
        // 2. Eseguo la query
        // 3. Termino la transazione col salvataggio effettivo di una nuova riga nella tabella students
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


}
