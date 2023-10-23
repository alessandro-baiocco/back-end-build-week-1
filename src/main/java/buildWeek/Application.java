package buildWeek;

import buildWeek.dao.SellerDao;
import buildWeek.dao.SubscriptionDAO;
import buildWeek.dao.UserBadgeDao;
import buildWeek.entities.Subscription;
import buildWeek.entities.UserBadge;
import buildWeek.enums.TicketDuration;
import net.datafaker.Faker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
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
        SubscriptionDAO subDao = new SubscriptionDAO(em);
        Faker faker = new Faker();
        Random rnd = new Random();


        LocalDate now = LocalDate.now();

        Supplier<UserBadge> userSupplier = () -> {
            return new UserBadge(faker.name().firstName(), faker.name().lastName(), now.minusYears(rnd.nextInt(20, 50)), now.minusYears(rnd.nextInt(2, 5)));
        };


//        Supplier<Seller> SellerSupplier = () -> {
//            return new Seller(true , SellerType.AUTO);
//        };


//        for (int i = 0; i < 10; i++) {
//            if (i % 2 == 0) {
//                sellDao.save(new Seller(true, SellerType.AUTO));
//
//            } else {
//                sellDao.save(new Seller(false, SellerType.REAL));
//            }

        List<TicketDuration> lista = new ArrayList<>();
        lista.add(TicketDuration.WEEKLY);
        lista.add(TicketDuration.MONTHLY);


        for (int i = 0; i < 10; i++) {

            subDao.save(new Subscription(now.minusMonths(rnd.nextInt(5, 10)),
                    sellDao.getRandomSeller(),
                    lista.get(rnd.nextInt(0, 2)), now.minusMonths(rnd.nextInt(2, 5))
                    , userDAO.getRandomUserBadge()));


        }


        em.close();
        emf.close();
    }


}






