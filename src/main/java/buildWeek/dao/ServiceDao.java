package buildWeek.dao;

import buildWeek.entities.Service;
import buildWeek.entities.Transport;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServiceDao {

    private final EntityManager em;

    public ServiceDao(EntityManager em) {
        this.em = em;
    }


    public void save(Service service) {
        if (service.getTransport() != null) {
            TransportDao transportDao = new TransportDao(em);
            List<Service> services = new ArrayList<>();
            TypedQuery<Service> query = em.createNamedQuery("getAllServices", Service.class);
            services = query.getResultList();
            AtomicBoolean transportHasAlreadyService = new AtomicBoolean(false);
            services.forEach(service1 -> {
                if (service1.getTransport().getId() == service.getTransport().getId()) {
                    if (service1.getEnd_date() == null) {
                        transportHasAlreadyService.set(true);
                    }
                }
            });
            if (!transportHasAlreadyService.get()) {
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                em.persist(service);

                System.out.println("Service " + service.getId() + " saved");

                Transport transport = transportDao.getById(service.getTransport().getId());
                transport.setActive(false);
                em.persist(transport);
                System.out.println("Il mezzo " + service.getTransport().getId() + " è stato messo in manutenzione.");
                tx.commit();
            } else {
                System.out.println("C'è già un servizio attivo per il mezzo " + service.getTransport().getId());
            }
        } else {
            System.out.println("Il transporto che hai inserito nel servizio non esiste.");
        }

    }

    public void endService(Transport transport) {
        List<Service> services = new ArrayList<>();
        services = transport.getServices();
        if (services.isEmpty()) {
            System.out.println("Non è stato trovato nessun servizio in corso per questo mezzo.");
        } else {
            services.forEach(service1 -> {
                if (service1.getEnd_date() == null) {
                    EntityTransaction tx = em.getTransaction();
                    tx.begin();
                    service1.setEnd_date(LocalDate.now());
                    transport.setActive(true);
                    em.persist(transport);
                    em.persist(service1);
                    tx.commit();
                    System.out.println("Il mezzo " + transport.getId() + " è di nuovo disponibile.");
                }
            });
        }
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

    public void getAllManteinanceOfTransport(Transport transport) {
        if (transport.getServices().isEmpty()) {
            System.out.println("Non ci sono periodi di manutenzione per il mezzo " + transport.getId());
        } else {
            System.out.println(transport.getServices());
        }
    }
}
