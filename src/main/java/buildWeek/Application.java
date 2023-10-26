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
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("cosotransport");

    private static final EntityManager em = emf.createEntityManager();
    private static final SubscriptionDAO subDao = new SubscriptionDAO(em);
    private static final SellerDao sellDao = new SellerDao(em);
    private static final UserBadgeDao userDAO = new UserBadgeDao(em);
    private static final TransportDao transDao = new TransportDao(em);
    private static final ServiceDao servDao = new ServiceDao(em);
    private static final RouteDAO rouDao = new RouteDAO(em);
    private static final TravelDao travDao = new TravelDao(em);
    private static final TicketsDAO tickDao = new TicketsDAO(em);
    private static final ValidationDao valDao = new ValidationDao(em);
    private static final Random rnd = new Random();
    private static final LocalDate now = LocalDate.now();
    private static final Faker faker = new Faker();
    private static final LocalDateTime nowTime = LocalDateTime.now();

    private static LocalDateTime now2 = LocalDateTime.now();
    private final int randomMinutes = 20 + rnd.nextInt(21);


    public static void main(String[] args) {
        menu();

        em.close();
        emf.close();
    }

    private static void menu() {
        int menu01 = -1;
        while (menu01 < 0) {
            System.out.println();
            System.out.println("Benvenuto nel sistema di coso trasporti");
            System.out.println();
            System.out.println("seleziona un'opzione");
            System.out.println("1 - Area utenti");
            System.out.println("2 - Area amministrativa");
            System.out.println("3 - area ospiti");
            System.out.println("0 - Esci");
            System.out.println();

            menu01 = scanInt();

            switch (menu01) {
                case 1:
                    userMenuLogin();
                    menu01 = -1;
                    break;
                case 2:
                    adminMenu();
                    menu01 = -1;
                    break;
                case 3:
                    noIdMenu();
                    menu01 = -1;
                    break;
                case 0:
                    System.out.println("Arrivederci");
                    break;
                default:
                    System.out.println("Opzione non valida");
                    menu();
            }
        }
    }

    private static void adminMenu() {
        int menu01 = -1;
        while (menu01 < 0) {
            System.out.println();
            System.out.println("Area amministrativa");
            System.out.println();
            System.out.println("1 - gestisci mezzi");
            System.out.println("2 - gestisci tratte");
            System.out.println("3 - visualizza statistiche viaggi");
            System.out.println("0 - indietro");
            menu01 = scanInt();
            switch (menu01) {
                case 1:
                    manageTransports();
                    menu01 = -1;
                    break;
                case 2:
                    manageRoutes();
                    menu01 = -1;
                    break;
                case 3:
                    statistic();
                    menu01 = -1;
                case 0:
                    System.out.println("Arrivederci");
                    break;
                default:
                    System.out.println("Opzione non valida");
                    menu01 = -1;
            }
        }
    }

    private static void statistic() {
        int menu01 = -1;
        while (menu01 < 0) {
            System.out.println();
            System.out.println("Statistiche di viaggio");
            System.out.println();
            System.out.println("1 - biglietti venduti");
            System.out.println("2 - abbonamenti venduti");
            System.out.println("3 - biglietti vidimati");
            System.out.println("4 - percorrenze tratte");
            menu01 = scanInt();
            switch (menu01) {
                case 1:
                    soldTickets();
                    menu01 = -1;
                    break;
                case 2:
                    soldServices();
                    menu01 = -1;
                    break;
                case 3:
                    validatedTickets();
                    menu01 = -1;
                    break;
                case 4:
                    routesStat();
                    menu01 = -1;
                    break;
                case 0:
                    System.out.println("indietro");
                    break;
                default:
                    System.out.println("Opzione non valida");
                    menu01 = -1;
            }
        }
    }

    private static void routesStat() {
    }

    private static void validatedTickets() {
        int menu01 = -1;
        while (menu01 < 0) {
            System.out.println();
            System.out.println("Statistiche di viaggio");
            System.out.println();
            System.out.println("1 - biglietti vidimati");
            System.out.println("2 - biglietti vidimati in un giorno");
            System.out.println("3 - biglietti vidimati tra due date");
            System.out.println("4 - biglietti vidimati su un trasporto");
            System.out.println("5 - biglietti vidimati su un trasporto in un giorno");
            System.out.println("6 - biglietti vidimati su un trasporto tra due date");
            System.out.println("7 - biglietti vidimati su una tratta");
            System.out.println("8 - biglietti vidimati su una tratta in un giorno");
            System.out.println("9 - biglietti vidimati su una tratta tra due date");
            System.out.println("0 - indietro");
            menu01 = scanInt();
            switch (menu01) {
                case 1:
                    System.out.println("\nsono stati vidimati " + valDao.getAll().size() + " biglietti in totale\n");
                    pauseReturn();
                    menu01 = -1;
                    break;
                case 2:
                    validatedTicketsByDate();
                    pauseReturn();
                    menu01 = -1;
                    break;
                case 3:
                    validatedTicketsBy2Date();
                    pauseReturn();
                    menu01 = -1;
                    break;
                case 4:
                    validatedTicketsByTransport();
                    pauseReturn();
                    menu01 = -1;
                    break;
                case 5:
                    validatedTicketsByTransportAndDate();
                    pauseReturn();
                    menu01 = -1;
                    break;
                case 6:
                    validatedTicketsByTransportAnd2Date();
                    pauseReturn();
                    menu01 = -1;
                    break;
                case 7:
                    validatedTicketsByRoute();
                    pauseReturn();
                    menu01 = -1;
                    break;
                case 8:
                    validatedTicketsByRouteAndDate();
                    pauseReturn();
                    menu01 = -1;
                    break;
                case 9:
                    validatedTicketsByRouteAnd2Date();
                    pauseReturn();
                    menu01 = -1;
                    break;
                case 0:
                    System.out.println("indietro");
                    break;
                default:
                    System.out.println("Opzione non valida");
                    menu01 = -1;
            }
        }
    }

    private static void validatedTicketsByRouteAnd2Date() {
        rouDao.getAll().forEach(System.out::println);
        Route route = getRoute();
        System.out.println("\ninserisci la prima data\n");
        LocalDate date1 = getDate();
        System.out.println("\ninserisci la seconda data\n");
        LocalDate date2 = getDate();
        System.out.println("sono stati vidimati " + valDao.getAll(route, date1, date2).size() + " biglietti sulla tratta " + route.getStartRoutePlace() + " - " + route.getEndRoutePlace() + " tra il " + date1 + " e il " + date2);
    }

    private static void validatedTicketsByRouteAndDate() {
        rouDao.getAll().forEach(System.out::println);
        Route route = getRoute();
        System.out.println("\ninserisci una data\n");
        LocalDate date = getDate();
        System.out.println("il giorno " + date + " sono stati vidimati " + valDao.getAll(route, date).size() + " biglietti sulla tratta " + route.getStartRoutePlace() + " - " + route.getEndRoutePlace());
    }

    private static void validatedTicketsByRoute() {
        rouDao.getAll().forEach(System.out::println);
        Route route = getRoute();
        System.out.println("sono stati vidimati " + valDao.getAll(route).size() + " biglietti sulla tratta " + route.getStartRoutePlace() + " - " + route.getEndRoutePlace());
    }

    private static void validatedTicketsByTransportAnd2Date() {
        Transport transport = getTransport();
        System.out.println("\ninserisci la prima data\n");
        LocalDate date1 = getDate();
        System.out.println("\ninserisci la seconda data\n");
        LocalDate date2 = getDate();
        System.out.println("sono stati vidimati " + valDao.getAll(transport, date1, date2).size() + " biglietti sul trasporto " + transport.getName() + " tra il " + date1 + " e il " + date2);
    }

    private static void validatedTicketsByTransportAndDate() {
        Transport transport = getTransport();
        System.out.println("\ninserisci una data\n");
        LocalDate date = getDate();
        System.out.println("il giorno " + date + " sono stati vidimati " + valDao.getAll(transport, date).size() + " biglietti sul trasporto " + transport.getName());
    }

    private static void validatedTicketsByTransport() {
        Transport transport = getTransport();
        System.out.println("sono stati vidimati " + valDao.getAll(transport).size() + " biglietti sul trasporto " + transport.getName());
    }

    private static void validatedTicketsBy2Date() {
        System.out.println("\ninserisci la prima data\n");
        LocalDate date1 = getDate();
        System.out.println("\ninserisci la seconda data\n");
        LocalDate date2 = getDate();
        System.out.println("\nsono stati vidimati " + valDao.getAll(date1, date2).size() + " biglietti tra il " + date1 + " e il " + date2);
    }

    private static void validatedTicketsByDate() {
        LocalDate date = getDate();
        System.out.println("il giorno " + date + " sono stati vidimati " + valDao.getAll(date).size() + " biglietti");
    }

    private static void soldServices() {
        int menu01 = -1;
        while (menu01 < 0) {
            System.out.println();
            System.out.println("Statistiche di viaggio");
            System.out.println();
            System.out.println("1 - abbonamenti venduti");
            System.out.println("2 - abbonamenti venduti per venditore");
            System.out.println("3 - abbonamenti venduti per data e venditore");
            System.out.println("4 - abbonamenti venduti tra due date da un venditore");
            System.out.println("5 - abbonamenti venduti in un giorno");
            System.out.println("6 - abbonamenti venduti tra due date");
            System.out.println("0 - indietro");
            menu01 = scanInt();
            switch (menu01) {
                case 1:
                    System.out.println("\nsono stati venduti " + subDao.getAll().size() + " abbonamenti in totale\n");
                    pauseReturn();
                    menu01 = -1;
                    break;
                case 2:
                    soldServicesBySeller();
                    pauseReturn();
                    menu01 = -1;
                    break;
                case 3:
                    soldServicesByDateAndSeller();
                    pauseReturn();
                    menu01 = -1;
                    break;
                case 4:
                    soldServicesBy2DateAndSeller();
                    pauseReturn();
                    menu01 = -1;
                    break;
                case 5:
                    soldServicesByDate();
                    pauseReturn();
                    menu01 = -1;
                    break;
                case 6:
                    soldServicesBy2Date();
                    pauseReturn();
                    menu01 = -1;
                    break;
                case 0:
                    System.out.println("indietro");
                    break;
                default:
                    System.out.println("Opzione non valida");
                    menu01 = -1;
            }
        }
    }

    private static void soldServicesBy2Date() {
        System.out.println("\ninserisci la prima data\n");
        LocalDate date1 = getDate();
        System.out.println("\ninserisci la seconda data\n");
        LocalDate date2 = getDate();
        System.out.println("\nsono stati venduti " + subDao.soldSubscription(date1, date2) + " abbonamenti tra il " + date1 + " e il " + date2);
    }

    private static void soldServicesByDate() {
        LocalDate date = getDate();
        System.out.println("il giorno " + date + " sono stati venduti " + subDao.soldSubscription(date) + " abbonamenti");
    }

    private static void soldServicesBy2DateAndSeller() {
        sellDao.getAll().forEach(System.out::println);
        Seller seller = getSeller();
        System.out.println("\ninserisci la prima data\n");
        LocalDate date1 = getDate();
        System.out.println("\ninserisci la seconda data\n");
        LocalDate date2 = getDate();
        System.out.println("\nIl punto vendita n. " + seller.getId() + " ha venduto " + sellDao.soldSubscriptions(seller, date1, date2) + " abbonamenti tra il " + date1 + " e il " + date2);
    }

    private static void soldServicesByDateAndSeller() {
        sellDao.getAll().forEach(System.out::println);
        Seller seller = getSeller();
        LocalDate date = getDate();
        System.out.println("Il punto vendita n. " + seller.getId() + " ha venduto " + sellDao.soldSubscriptions(seller, date) + " abbonamenti in data " + date);
    }

    private static void soldServicesBySeller() {
        sellDao.getAll().forEach(System.out::println);
        Seller seller = getSeller();
        System.out.println("Il punto vendita n. " + seller.getId() + " ha venduto " + sellDao.soldSubscriptions(seller) + " abbonamenti");
    }

    private static void soldTickets() {
        int menu01 = -1;
        while (menu01 < 0) {
            System.out.println();
            System.out.println("Statistiche di viaggio");
            System.out.println();
            System.out.println("1 - biglietti venduti");
            System.out.println("2 - biglietti venduti per venditore");
            System.out.println("3 - biglietti venduti per data e venditore");
            System.out.println("4 - biglietti venduti tra due date da un venditore");
            System.out.println("0 - indietro");
            menu01 = scanInt();
            switch (menu01) {
                case 1:
                    System.out.println("\nsono stati venduti " + tickDao.getAll().size() + " in totale\n");
                    pauseReturn();
                    menu01 = -1;
                    break;
                case 2:
                    soldTicketsBySeller();
                    pauseReturn();
                    menu01 = -1;
                    break;
                case 3:
                    soldTicketsByDateAndSeller();
                    pauseReturn();
                    menu01 = -1;
                    break;
                case 4:
                    soldTicketsBy2DateAndSeller();
                    pauseReturn();
                    menu01 = -1;
                    break;
                case 0:
                    System.out.println("indietro");
                    break;
                default:
                    System.out.println("Opzione non valida");
                    menu01 = -1;
            }
        }
    }

    private static void soldTicketsBy2DateAndSeller() {
        sellDao.getAll().forEach(System.out::println);
        Seller seller = getSeller();
        System.out.println("\ninserisci la prima data\n");
        LocalDate date1 = getDate();
        System.out.println("\ninserisci la seconda data\n");
        LocalDate date2 = getDate();
        System.out.println("\nIl punto vendita n. " + seller.getId() + " ha venduto " + tickDao.getAllSoldTicket(seller, date1, date2).size() + " biglietti tra il " + date1 + " e il " + date2);
    }

    private static void soldTicketsByDateAndSeller() {
        sellDao.getAll().forEach(System.out::println);
        Seller seller = getSeller();
        LocalDate date = getDate();
        System.out.println("Il punto vendita n. " + seller.getId() + " ha venduto " + tickDao.getAllSoldTicket(seller, date).size() + " biglietti in data " + date);
    }

    private static LocalDate getDate() {
        LocalDate date = null;
        int menu01 = -1;
        while (menu01 < 0) {
            System.out.println("inserisci il giorno");
            int day = scanInt();
            System.out.println("inserisci il mese");
            int month = scanInt();
            System.out.println("inserisci l'anno");
            int year = scanInt();
            if (1 <= day && day <= 31 && 1 <= month && month <= 12 && 1900 <= year && year <= 2100) {
                date = LocalDate.of(year, month, day);
                menu01 = 0;
            } else {
                System.out.println("data non valida");
            }
        }
        return date;
    }

    private static void soldTicketsBySeller() {
        sellDao.getAll().forEach(System.out::println);
        Seller seller = getSeller();
        System.out.println("Il punto vendita n. " + seller.getId() + " ha venduto " + tickDao.getAllSoldTicket(seller).size() + " biglietti");
    }


    private static void pauseReturn() {
        System.out.println("premi invio per tornare al menu");
        Scanner input = new Scanner(System.in);
        input.nextLine();
    }

    private static void manageRoutes() {
        int menu01 = -1;
        while (menu01 < 0) {
            System.out.println();
            System.out.println("Gestione tratte");
            System.out.println();
            System.out.println("1 - visualizza il numero di volte che un mezzo percorre una tappa");
            System.out.println("2 - visualizza il tempo effettivo di percorrenza di ogni tratta");
            System.out.println("3 - inserisci una corsa");
            System.out.println("4 - crea una tratta");
            System.out.println("0 - indietro");
            menu01 = scanInt();
            switch (menu01) {

                case 1:
                    travelNumber();
                    menu01 = -1;
                    break;
                case 2:
                    servDao.getAllServices().forEach(System.out::println);
                    menu01 = -1;
                case 3:
                    servDao.getAllServices(getTransport()).forEach(System.out::println);
                    menu01 = -1;
                    break;
                case 4:
                    putTransportInService();
                    menu01 = -1;
                    break;
                case 5:
                    setTransportActive();
                    menu01 = -1;
                    break;
                case 6:
                    getTransportRoute();
                    menu01 = -1;
                    break;
                case 0:
                    System.out.println("Arrivederci");
                    break;
                default:
                    System.out.println("Opzione non valida");
                    menu01 = -1;
            }
        }
    }

    //Prendiamo il numero di volte che un mezzo percorre una tappa
    private static void travelNumber() {
        transDao.getAll().forEach(System.out::println);
        Transport transport = getTransport();
        rouDao.getAll(transport).forEach(System.out::println);
        Route route = getRoute();
        int number = travDao.timesTraveled(route, transport);
        System.out.println("Il mezzo " + transport.getName() + "ha percorso la tratta "
                + route.getStartRoutePlace() + " - " + route.getEndRoutePlace() + " " + number +
                (number == 1 ? " volta" : " volte"));
    }


    private static void manageTransports() {
        int menu01 = -1;
        while (menu01 < 0) {
            System.out.println();
            System.out.println("Gestione trasporti");
            System.out.println();
            System.out.println("1 - visualizza tutti i trasporti");
            System.out.println("2 - visualizza tutti i trasporti in manutenzioni");
            System.out.println("3 - visualizza manutenzioni trasporto");
            System.out.println("4 - metti mezzo in manutenzione");
            System.out.println("5 - metti mezzo in servizio");
            System.out.println("6 - visualizza tratta trasporto");
            System.out.println("0 - indietro");
            menu01 = scanInt();
            switch (menu01) {

                case 1:
                    transDao.getAll().forEach(System.out::println);
                    menu01 = -1;
                    break;
                case 2:
                    servDao.getAllServices().forEach(System.out::println);
                    menu01 = -1;
                case 3:
                    servDao.getAllServices(getTransport()).forEach(System.out::println);
                    menu01 = -1;
                    break;
                case 4:
                    putTransportInService();
                    menu01 = -1;
                    break;
                case 5:
                    setTransportActive();
                    menu01 = -1;
                    break;
                case 6:
                    getTransportRoute();
                    menu01 = -1;
                    break;
                case 0:
                    System.out.println("Arrivederci");
                    break;
                default:
                    System.out.println("Opzione non valida");
                    menu01 = -1;
            }
        }
    }

    //Visualizza tratta trasporto
    private static void getTransportRoute() {
        int menu01 = -1;
        while (menu01 < 0) {
            System.out.println("\nScegli un mezzo.");
            System.out.println("0 - indietro");
            System.out.println();
            menu01 = scanInt();
            if (menu01 == 0) {
                System.out.println("indietro");
            } else {
                Transport transport = transDao.getById(menu01);
                if (transport != null) {
                    List<Travel> travelList = transport.getTravels();
                    if (!travelList.isEmpty()) {
                        System.out.println("Le rotte per il mezzo " + transport.getId() + " sono : ");
                        travelList.forEach(travel -> {
                            System.out.println(travDao.getById(travel.getId()).getRoute());
                        });

                    } else {
                        System.out.println("Non ci sono rotte per questo mezzo");
                    }
                    menu01 = 0;
                } else {
                    System.out.println("Il mezzo non esiste. Riprova.");
                }
                if (menu01 != 0) {
                    menu01 = -1;
                }
            }
        }
    }

    //Metti un mezzo in manutenzione lato amministrazione
    private static void putTransportInService() {
        int menu01 = -1;
        while (menu01 < 0) {
            System.out.println("\nScegli un mezzo da mettere in manutenzione.");
            System.out.println("0 - indietro");
            System.out.println();
            menu01 = scanInt();
            if (menu01 == 0) {
                System.out.println("indietro");
            } else {
                Transport transport = transDao.getById(menu01);
                if (transport != null) {
                    Service service = new Service(transport, now);
                    servDao.save(service);
                    menu01 = 0;
                } else {
                    System.out.println("Il mezzo non esiste. Riprova.");
                }
                if (menu01 != 0) {
                    menu01 = -1;
                }
            }
        }
    }

    //Metti un mezzo in servizio lato amministrazione
    private static void setTransportActive() {
        int menu01 = -1;
        while (menu01 < 0) {
            System.out.println("\nScegli un mezzo da mettere in circolazione.");
            System.out.println("0 - indietro");
            System.out.println();
            menu01 = scanInt();
            if (menu01 == 0) {
                System.out.println("indietro");
            } else {
                Transport transport = transDao.getById(menu01);
                if (transport != null) {
                    servDao.endService(transport);
                    menu01 = 0;
                } else {
                    System.out.println("Il mezzo non esiste. Riprova.");
                }
                if (menu01 != 0) {
                    menu01 = -1;
                }
            }
        }
    }

    private static Transport getTransport() {
        Transport transport = null;
        while (true) {
            System.out.println("\ninserisci l'id del trasporto");
            System.out.println("0 - indietro");
            transport = transDao.getById(scanInt());
            if (transport != null) {
                return transport;
            } else {
                System.out.println("trasporto non trovato");
            }
        }
    }

    // get Route
    private static Route getRoute() {
        Route route = null;
        while (true) {
            System.out.println("\ninserisci l'id della tratta percorsa");
            System.out.println("0 - indietro");
            route = rouDao.getById(scanInt());
            if (route != null) {
                return route;
            } else {
                System.out.println("Tratta non trovata");
            }
        }
    }

    private static Seller getSeller() {
        Seller seller = null;
        while (true) {
            System.out.println("\ninserisci l'id del venditore");
            System.out.println("0 - indietro");
            seller = sellDao.getById(scanInt());
            if (seller != null) {
                return seller;
            } else {
                System.out.println("venditore non trovato");
            }
        }
    }

    private static void userMenuLogin() {
        int menu01 = -1;
        while (menu01 < 0) {
            System.out.println();
            System.out.println("Area utenti");
            System.out.println();
            System.out.println("inserisci il tuo id");
            System.out.println("0 - indietro");
            System.out.println();
            menu01 = scanInt();
            if (menu01 == 0) {
                System.out.println("indietro");
            } else {
                if (userDAO.getById(menu01) != null) {
                    userMenu(userDAO.getById(menu01));
                } else {
                    System.out.println("utente non trovato");
                }
                menu01 = -1;
            }
        }

    }

    private static void userMenu(UserBadge userBadge) {
        int menu01 = -1;
        while (menu01 < 0) {
            System.out.println("benvenuto " + userBadge.getName());
            System.out.println();
            System.out.println("seleziona un'opzione");
            System.out.println("1 - Acquista biglietto");
            System.out.println("2 - gestisci tessera personale");
            System.out.println("3 - gestisci abbonamento");
            System.out.println("0 - indietro");
            menu01 = scanInt();
            switch (menu01) {
                case 1:
                    buyTicket(userBadge);
                    menu01 = -1;
                    break;
                case 2:
                    userBadgeMenu(userBadge);
                    menu01 = -1;
                    break;
                case 3:
                    subscriptionMenu(userBadge);
                    menu01 = -1;
                    break;
                case 0:
                    System.out.println("indietro");
                    break;
                default:
                    System.out.println("Opzione non valida");
                    menu01 = -1;
            }
        }
    }

    private static void takeTransport() {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("inserire punto di partenza - 0 per tornare indietro");
            String startPlace = input.nextLine().toLowerCase().trim();

            if (startPlace.equals("0")) break;

            System.out.println("inserire punto di arrivo - 0 per tornare indietro");
            String endPlace = input.nextLine().toLowerCase().trim();

            if (endPlace.equals("0")) break;

            Route userRoute = rouDao.findTravelForThis(startPlace, endPlace);
            if (userRoute != null
                    && !userRoute.getTravel().isEmpty()
                    && rouDao.transportIsActive(userRoute)
            ) {
                String ritorno = testTicket(userRoute);
                if (ritorno.equals("0")) break;
            } else if (userRoute != null) {
                if (userRoute.getTravel().isEmpty()) System.out.println("non ci sono viaggi disponibili");
            } else {
                System.out.println("rotta non disponibile ci scusiamo per il disagio");
            }
        }
    }

    private static String testTicket(Route useRoute) {
        int menu01 = -1;
        while (menu01 < 0) {
            System.out.println("inserire numero biglietto - 0 per tornare indietro");
            menu01 = scanInt();
            Ticket userTick = tickDao.getById(menu01);
            if (userTick != null && tickDao.tickIsNotValidated(userTick)) {
                Travel userTrav = useRoute.getTravel().get(0);
                Transport userTrans = useRoute.getTransports().get(0);
                valDao.validate(userTick, userTrans, userTrav);
                System.out.println("biglietto convalidato correttamente puoi procedere");
                menu01 = 0;
            } else if (userTick != null && !tickDao.tickIsNotValidated(userTick)) {
                System.err.println("il biglietto è gia convalidato , c'hai provato");
                menu01 = -1;
            } else if (menu01 == 0) {
                System.out.println("indietro");
            } else {
                System.out.println("biglietto non trovato");
                menu01 = -1;
            }
        }
        return "0";
    }

    private static void testValidation(Route useRoute, Ticket userTick) {

    }


    private static void subscriptionMenu(UserBadge userBadge) {
        int menu01 = -1;
        while (menu01 < 0) {
            System.out.println();
            System.out.println("Area abbonamenti");
            System.out.println();
            System.out.println("inserisci il id dell'abbonamento da gestire");
            System.out.println("0 - indietro");
            System.out.println();
            menu01 = scanInt();
            Subscription userSubscription = subDao.getById(menu01);
            if (userSubscription != null) {
                subDao.reNew(userSubscription, userSubscription.getType());
            } else {
                if (menu01 == 0) {
                    System.out.println("indietro");
                } else {
                    System.out.println("non trovato");
                    menu01 = -1;
                }
            }
        }

    }

    private static void userBadgeMenu(UserBadge userBadge) {
        int menu01 = -1;
        while (menu01 < 0) {
            System.out.println();
            System.out.println("Area tessera");
            System.out.println();
            System.out.println("1 - vedi scadenza");
            System.out.println("2 - rinnova tessera personale");
            System.out.println("0 - indietro");
            menu01 = scanInt();
            switch (menu01) {
                case 1:
                    System.out.println("la tessera scadrà il : " + userBadge.getActivationDate().plusYears(1));
                    menu01 = -1;
                    break;
                case 2:
                    userDAO.reNewUserBadge(userBadge);
                    menu01 = -1;
                    break;
                case 0:
                    System.out.println("indietro");
                    break;
                default:
                    System.out.println("Opzione non valida");
                    menu01 = -1;
            }
        }
    }

    private static void buyTicket(UserBadge userBadge) {
        System.out.println();
        System.out.println("Area biglietti");
        System.out.println();
        System.out.println("1 - compra biglietto");
        System.out.println("2 - compra abbonamento");
        int menu01 = -1;
        while (menu01 < 0) {
            menu01 = scanInt();
            switch (menu01) {
                case 1: {
                    Ticket newTicket = new Ticket(LocalDate.now(), sellDao.getRandomSeller());
                    System.out.println("biglietto creato il nuovo biglietto è : " + newTicket);
                    tickDao.save(newTicket);
                    break;
                }
                case 2: {
                    int menu02 = -1;
                    while (menu02 < 0) {
                        System.out.println("che tipo ?");
                        System.out.println("1 - settimanale");
                        System.out.println("2 - mensile");
                        System.out.println("0 - indietro");
                        menu02 = scanInt();
                        switch (menu02) {
                            case 1: {
                                Subscription newSub = new Subscription(LocalDate.now(), sellDao.getRandomSeller(),
                                        TicketDuration.WEEKLY, LocalDate.now(), userBadge);
                                System.out.println("abbonamento creato il nuovo abbonamento è : " + newSub);
                                subDao.save(newSub);
                                break;
                            }
                            case 2: {
                                Subscription newSub = new Subscription(LocalDate.now(), sellDao.getRandomSeller(),
                                        TicketDuration.MONTHLY, LocalDate.now(), userBadge);
                                System.out.println("abbonamento creato il nuovo abbonamento è : " + newSub);
                                subDao.save(newSub);
                                break;
                            }

                            case 0: {
                                System.out.println("indietro");
                                break;
                            }
                            default: {
                                System.out.println("Opzione non valida");
                                menu02 = -1;
                            }
                        }
                    }

                }

                case 0: {
                    System.out.println("indietro");
                    break;
                }
                default: {
                    System.out.println("Opzione non valida");
                    menu01 = -1;
                }


            }
        }

    }

    private static void noIdMenu() {
        Scanner input = new Scanner(System.in);
        int menu01 = -1;
        while (menu01 < 0) {
            System.out.println("cosa vuoi fare ?");
            System.out.println("1 - compra biglietto");
            System.out.println("2 - fai tessera");
            System.out.println("3 - prendi un mezzo");
            System.out.println("0 - indietro");
            menu01 = scanInt();
            switch (menu01) {
                case 1:
                    Ticket newTicket = new Ticket(LocalDate.now(), sellDao.getRandomSeller());
                    tickDao.save(newTicket);
                    menu01 = -1;
                    break;
                case 2:
                    try {
                        System.out.println("inserire nome");
                        String name = input.nextLine();
                        System.out.println("inserire cognome");
                        String lastname = input.nextLine();
                        System.out.println("inserire data di nascita in questo formato YYYY-MM-DD");
                        LocalDate birth = LocalDate.parse(input.nextLine());
                        UserBadge newUser = new UserBadge(name, lastname, birth, LocalDate.now());
                    } catch (DateTimeParseException ex) {
                        System.err.println("data non valida");
                        ;
                    }
                    menu01 = -1;
                    break;
                case 3:
                    takeTransport();
                    menu01 = -1;
                    break;
                case 0:
                    System.out.println("indietro");
                    break;
                default:
                    System.out.println("Opzione non valida");
                    menu01 = -1;
            }
        }
    }


    private static int scanInt() {
        int num = -1;
        Scanner scanner = new Scanner(System.in);
        while (num < 0) {
            try {
                num = scanner.nextInt();
            } catch (Exception e) {
                System.err.println("Inserisci un numero");
                scanner.nextLine();
            }
        }
        return num;
    }

    private static void createUsers() {
        Supplier<UserBadge> userSupplier = () -> {
            return new UserBadge(faker.name().firstName(), faker.name().lastName(), now.minusYears(rnd.nextInt(20, 50)), now.minusYears(rnd.nextInt(0, 5)));
        };
        for (int d = 0; d < 10; d++) {
            userDAO.save(userSupplier.get());
        }
    }


    private static void createSellers() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                sellDao.save(new Seller(true, SellerType.AUTO));

            } else {
                sellDao.save(new Seller(false, SellerType.REAL));
            }
        }
    }

    private static void createSubscriptions() {
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


    private static void createTransport() {
        for (int e = 0; e < 20; e++) {
            if (e <= 5) {
                transDao.save(new Transport(faker.name().title(), TransportType.BUS, 20, true));
            } else if (e > 5 && e <= 10) {
                transDao.save(new Transport(faker.name().title(), TransportType.TRAM, 80, true));
            } else if (e > 10 && e <= 15) {
                transDao.save(new Transport(faker.name().title(), TransportType.BUS, 20, false));
            } else {
                transDao.save(new Transport(faker.name().title(), TransportType.TRAM, 80, false));

            }
        }
    }

    private static void createServiceAndRoutes() {
        for (int o = 0; o < 10; o++) {
            servDao.save(new Service(transDao.getRandomTransport(), now));
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

    private static void createTravelAndSetRouTransAndTravel() {
        int randomMinutes = 20 + rnd.nextInt(21);
        for (int c = 0; c < 10; c++) {
            LocalDateTime coso = nowTime.minusDays(rnd.nextInt(20, 30));
            travDao.save(new Travel(coso,
                    coso.plus(randomMinutes, ChronoUnit.valueOf("MINUTES")),
                    rouDao.getRandomRoute(),
                    transDao.getRandomTransport()));
        }

//        for (int w = 0; w < 10; w++) {
//            LocalDateTime coso = now2.minusDays(rnd.nextInt(20, 30));
//            rouDao.setTrans();
//            rouDao.setTravel(rouDao.getRandomRoute(), new Travel(coso,
//                    coso.plus(randomMinutes, ChronoUnit.valueOf("MINUTES")),
//                    rouDao.getRandomRoute(),
//                    transDao.getRandomTransport()));
//        }
    }


    private static void createTicket() {
        for (int i = 0; i <= 30; i++) {
            tickDao.save(new Ticket(now.minusDays(rnd.nextInt(1, 30)), sellDao.getRandomSeller()));
        }
    }

    private static void createValidation() {
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

    private static void createAll() {
        createUsers();
        createTransport();
        createServiceAndRoutes();
        createSellers();
        createSubscriptions();
        createTravelAndSetRouTransAndTravel();
        createTicket();
        createValidation();
    }
}










