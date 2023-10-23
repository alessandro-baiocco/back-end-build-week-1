package buildWeek.dao;

import buildWeek.entities.Route;
import buildWeek.entities.Transport;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

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

    public int timesTraveled(Transport transport) {
        TypedQuery<Transport> query = em.createQuery("SELECT r FROM Route r WHERE r.transport = :transport", Transport.class);
        query.setParameter("transport", transport);
        return query.getResultList().size();
    }


}
