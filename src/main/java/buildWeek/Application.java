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


    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        UserBadgeDao userDAO = new UserBadgeDao(em);
        SellerDao sellDao = new SellerDao(em);
        TransportDao transDao = new TransportDao(em);
        SubscriptionDAO subDao = new SubscriptionDAO(em);
        TravelDao travDao = new TravelDao(em);
        ServiceDao servDao = new ServiceDao(em);
        RouteDAO rouDao = new RouteDAO(em);

        ValidationDao valDao = new ValidationDao(em);
        TicketsDAO tickDao = new TicketsDAO(em);


        Faker faker = new Faker();
        Random rnd = new Random();


        LocalDate now = LocalDate.now();
        LocalDateTime now2 = LocalDateTime.now();
        int randomMinutes = 20 + rnd.nextInt(21);
        LocalDateTime randomDateTime = now2.plus(randomMinutes, ChronoUnit.MINUTES);

        Supplier<UserBadge> userSupplier = () -> {
            return new UserBadge(faker.name().firstName(), faker.name().lastName(), now.minusYears(rnd.nextInt(20, 50)), now.minusYears(rnd.nextInt(2, 5)));
        };


       Supplier<Seller> SellerSupplier = () -> {
            return new Seller(true , SellerType.AUTO);
       };

      /*  for (int d = 0; d < 10; d++) {
           userDAO.save(userSupplier.get());
        }



       for (int i = 0; i < 10; i++) {
           if (i % 2 == 0) {
                sellDao.save(new Seller(true, SellerType.AUTO));

            } else {
                sellDao.save(new Seller(false, SellerType.REAL));
            }

       List<TicketDuration> lista = new ArrayList<>();
        lista.add(TicketDuration.WEEKLY);
        lista.add(TicketDuration.MONTHLY);


        for (int a = 0; a < 10; a++) {

            subDao.save(new Subscription(now.minusMonths(rnd.nextInt(5, 10)),
                    sellDao.getRandomSeller(),
                   lista.get(rnd.nextInt(0, 2)), now.minusMonths(rnd.nextInt(2, 5))
                    , userDAO.getRandomUserBadge()));


        }

 for (int e = 0; e < 5; e++) {
            transDao.save(new Transport(faker.name().title(), TransportType.BUS, 80, true));
        }

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

for (int c = 0; c < 10; c++) {
            LocalDateTime coso = now2.minusDays(rnd.nextInt(20, 30));
            travDao.save(new Travel(coso,
                    coso.plus(randomMinutes, ChronoUnit.valueOf("MINUTES")),
                    rouDao.findById(rnd.nextInt(72, 82)),
                    transDao.getById(rnd.nextInt(57, 102))));
        }


 for (int w = 0; w < 10; w++) {
            rouDao.setTrans();
            rouDao.setTravel();
        }
        */

        for(int i=0; i<=20;i++){}



//        subDao.reNew(82);

//
//        for (int i = 0; i < 10; i++) {
//            valDao.save(new Validation(now.minusDays(rnd.nextInt(1, 50)),
//                    transDao.getById(rnd.nextInt(96, 115)),
//                    travDao.findById(rnd.nextInt(156, 165))));
//
//        }
//        for (int i = 0; i < 10; i++) {
//            tickDao.save(new Ticket(now.minusDays(rnd.nextInt(1, 20)), sellDao.getRandomSeller(), valDao.getRandomValidation()));
//        }

        em.close();
        emf.close();
           }

       }









