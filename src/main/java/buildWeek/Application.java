package buildWeek;

import buildWeek.dao.*;
import buildWeek.entities.*;
import buildWeek.enums.SellerType;
import buildWeek.enums.TicketDuration;
import buildWeek.enums.TransportType;
import net.datafaker.Faker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("cosotransport");

    private static final EntityManager em = emf.createEntityManager();
    private static SubscriptionDAO subDao = new SubscriptionDAO(em);
    private static SellerDao sellDao = new SellerDao(em);
    private static UserBadgeDao userDAO = new UserBadgeDao(em);
    private static TransportDao transDao = new TransportDao(em);
    private static ServiceDao servDao = new ServiceDao(em);
    private static RouteDAO rouDao = new RouteDAO(em);
    private static Random rnd = new Random();
    private static LocalDate now = LocalDate.now();
    private static Faker faker = new Faker();
    private TravelDao travDao = new TravelDao(em);
    private LocalDateTime now2 = LocalDateTime.now();
    private int randomMinutes = 20 + rnd.nextInt(21);
    private LocalDateTime randomDateTime = now2.plus(randomMinutes, ChronoUnit.MINUTES);
    private TicketsDAO tickDao = new TicketsDAO(em);
    private ValidationDao valDao = new ValidationDao(em);
    private Supplier<Seller> SellerSupplier = () -> {
        return new Seller(true, SellerType.AUTO);
    };

    public static void main(String[] args) {

        em.close();
        emf.close();
    }

    public void createUsers() {
        Supplier<UserBadge> userSupplier = () -> {
            return new UserBadge(faker.name().firstName(), faker.name().lastName(), now.minusYears(rnd.nextInt(20, 50)), now.minusYears(rnd.nextInt(2, 5)));
        };
        for (int d = 0; d < 10; d++) {
            userDAO.save(userSupplier.get());
        }
    }

    public void createSellers() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                sellDao.save(new Seller(true, SellerType.AUTO));

            } else {
                sellDao.save(new Seller(false, SellerType.REAL));
            }
        }
    }

    private void createSubscriptions() {
        List<TicketDuration> lista = new ArrayList<>();
        lista.add(TicketDuration.WEEKLY);
        lista.add(TicketDuration.MONTHLY);
        for (int a = 0; a < 10; a++) {
            subDao.save(new Subscription(now.minusMonths(rnd.nextInt(5, 10)),
                    sellDao.getRandomSeller(),
                    lista.get(rnd.nextInt(0, 2)), now.minusMonths(rnd.nextInt(2, 5))
                    , userDAO.getRandomUserBadge()));
        }
    }

    private void createTransport() {
        for (int e = 0; e < 20; e++) {
            if (e <= 5) {
                transDao.save(new Transport(faker.name().title(), TransportType.BUS, 80, true));
            } else if (e > 5 && e <= 10) {
                transDao.save(new Transport(faker.name().title(), TransportType.TRAM, 80, true));
            } else if (e > 10 && e <= 15) {
                transDao.save(new Transport(faker.name().title(), TransportType.BUS, 80, false));
            } else {
                transDao.save(new Transport(faker.name().title(), TransportType.TRAM, 80, false));

            }
        }
    }

    private void createServiceAndRoutes() {
        for (int o = 0; o < 10; o++) {
            servDao.save(new Service(transDao.getById(rnd.nextInt(96, 115)), now.minusDays(rnd.nextInt(20, 30))));
        }
        for (int u = 0; u < 10; u++) {
            rouDao.save(new Route(faker.futurama().location(), faker.futurama().location(), rnd.nextInt(20, 40)));
        }
        for (int u = 0; u < 10; u++) {
            rouDao.save(new Route(faker.futurama().location(), faker.futurama().location(), rnd.nextInt(20, 40)));
        }
        for (int e = 0; e < 5; e++) {
            transDao.save(new Transport(faker.name().title(), TransportType.TRAM, 80, true));
        }
    }

    private void createTravelAndSetRouTransAndTravel() {
        for (int c = 0; c < 10; c++) {
            LocalDateTime coso = now2.minusDays(rnd.nextInt(20, 30));
            travDao.save(new Travel(coso,
                    coso.plus(randomMinutes, ChronoUnit.valueOf("MINUTES")),
                    rouDao.getById(rnd.nextInt(72, 82)),
                    transDao.getById(rnd.nextInt(57, 102))));
        }

        for (int w = 0; w < 10; w++) {
            rouDao.setTrans();
            rouDao.setTravel();
        }
    }

    private void createTicket() {
        for (int i = 0; i <= 30; i++) {
            tickDao.save(new Ticket(now.minusDays(rnd.nextInt(1, 30)), sellDao.getRandomSeller()));
        }
    }

    private void createValidation() {
        for (int i = 0; i < 10; i++) {
            valDao.save(new Validation(now.minusDays(rnd.nextInt(1, 50)),
                    transDao.getRandomTransport(),
                    travDao.getRandomTravel(),
                    tickDao.getRandomTicket()));
        }
        for (int i = 0; i < 10; i++) {
            tickDao.save(new Ticket(now.minusDays(rnd.nextInt(1, 20)), sellDao.getRandomSeller(), valDao.getRandomValidation()));
        }
    }

}









