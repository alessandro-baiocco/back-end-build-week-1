package buildWeek.dao;

import buildWeek.entities.Service;
import buildWeek.entities.Transport;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.Duration;
import java.util.List;

public class ServiceDao {

    private final EntityManager em;

    public ServiceDao(EntityManager em) {
        this.em = em;
    }


public void save(Service service) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(service);
        tx.commit();
        System.out.println("Service " + service.getId() + " saved");
    }

    public Service getById(int id) {
        return em.find(Service.class, id);
    }

    public void delete(Service service) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(service);
        tx.commit();
        System.out.println("Service " + service.getId() + " deleted");
    }

    public void refresh(Service service) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.refresh(service);
        tx.commit();
        System.out.println("Service " + service.getId() + " refreshed");
    }

    public List<Service> getAllServices(Transport transport) {
        TypedQuery<Service> query = em.createQuery("SELECT s FROM Service s WHERE s.transport = :transport", Service.class);
        query.setParameter("transport", transport);
        return query.getResultList();
    }

    public Duration getServiceDuration(Service service) {
        return Duration.between(service.getStart_date(), service.getEnd_date());
    }

}
