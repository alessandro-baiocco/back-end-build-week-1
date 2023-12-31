package buildWeek.dao;

import buildWeek.entities.Service;
import buildWeek.entities.Transport;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class ServiceDao {

    private final EntityManager em;

    public ServiceDao(EntityManager em) {
        this.em = em;
    }

    public void save(Service service) {
        if (service.getTransport().isActive()) {
            try {
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                em.persist(service);
                System.out.println("Service " + service.getId() + " saved");
                Transport transport = service.getTransport();
                transport.setActive(false);
                em.persist(transport);
                tx.commit();
                System.out.println("Il mezzo " + transport.getId() + " è stato messo in manutenzione.");
            } catch (Exception ex) {
                System.err.println("errore : " + ex.getMessage());
            }
        } else {
            System.out.println("C'è già un servizio attivo per il mezzo " + service.getTransport().getId());
        }
    }

    public void endService(Transport transport) {
        if (transport.isActive()) {
            System.out.println("Il mezzo non è in manutenzione");
        } else {
            transport.getServices().forEach(service -> {
                if (service.getEnd_date() == null) {
                    service.setEnd_date(LocalDate.now());
                    try {
                        EntityTransaction tx = em.getTransaction();
                        tx.begin();
                        transport.setActive(true);
                        em.persist(transport);
                        em.persist(service);
                        tx.commit();
                        System.out.println("Il mezzo " + transport.getId() + " è di nuovo disponibile.");
                    } catch (Exception ex) {
                        System.err.println("errore : " + ex.getMessage());
                    }
                }
            });
        }
    }

    public List<Service> getAllServices() {
        TypedQuery<Service> query = em.createQuery("SELECT s FROM Service s", Service.class);
        return query.getResultList();
    }

    public List<Service> getAllServices(Transport transport) {
        TypedQuery<Service> query = em.createQuery("SELECT s FROM Service s WHERE s.transport = :transport", Service.class);
        query.setParameter("transport", transport);
        return query.getResultList();
    }

}
