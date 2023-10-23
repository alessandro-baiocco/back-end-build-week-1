package buildWeek.dao;

import buildWeek.entities.Route;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class RouteDAO {
        private final EntityManager entityManager;

        public RouteDAO(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        public void save(Route route) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(route);
            transaction.commit();
            System.out.println("Il prestito è stato correttamente inserito");
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

}
