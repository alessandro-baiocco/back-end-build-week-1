package buildWeek.dao;

import buildWeek.entities.Route;
import buildWeek.entities.Transport;
import buildWeek.entities.Travel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Random;

public class RouteDAO {
    private final EntityManager entityManager;
    Random rnd = new Random();

    public RouteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Route route) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(route);
        transaction.commit();
        System.out.println("La tratta è stata correttamente inserita");
    }

    public Route findById(int id) {
        return entityManager.find(Route.class, id);
    }

    public void delete(int id) {
        Route routeToBeDeleted = entityManager.find(Route.class, id);

        if (routeToBeDeleted != null) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(routeToBeDeleted);
            transaction.commit();
            System.out.println("Questa tratta è stata cancellata con successo!");
        } else {
            System.out.println("Tratta non presente nel database");
        }
    }

    public Travel getRandomTravel() {
        TypedQuery<Travel> query = entityManager.createQuery("SELECT t FROM Travel t ORDER BY RAND()", Travel.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public Transport getRandomTrans() {
        TypedQuery<Transport> query = entityManager.createQuery("SELECT t FROM Transport t ORDER BY RAND()", Transport.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }


    public void setTravel() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<Route> getAllRoute = entityManager.createQuery("SELECT t FROM Route t", Route.class); // Query JPQL
        List<Route> boh = getAllRoute.getResultList();
        boh.forEach(t -> t.setTravel(getRandomTravel()));
        // 2. Eseguo la query
        // 3. Termino la transazione col salvataggio effettivo di una nuova riga nella tabella students
        transaction.commit();

    }

    public void setTrans() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<Route> getAllRoute = entityManager.createQuery("SELECT t FROM Route t", Route.class); // Query JPQL
        List<Route> boh = getAllRoute.getResultList();
        boh.forEach(t -> t.setMeansOfTransport(getRandomTrans()));
        // 2. Eseguo la query
        // 3. Termino la transazione col salvataggio effettivo di una nuova riga nella tabella students
        transaction.commit();

    }

}
