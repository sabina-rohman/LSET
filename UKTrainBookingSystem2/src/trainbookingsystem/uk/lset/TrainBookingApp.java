package trainbookingsystem.uk.lset;

import java.util.ArrayList;
import java.util.Scanner;

public class TrainBookingApp {
    private static ArrayList<Route> routes = new ArrayList<>();
    private static ArrayList<Booking> bookings = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initialisedRoutes();
        boolean isRunning = true;

        while(isRunning){
            System.out.println("---UK Train Ticket Booking System---");

            System.out.println("1. View available routes");
            System.out.println("2. Make a booking");
            System.out.println("3. View all bookings");
            System.out.println("4. View total daily revenue");
            System.out.println("5. Exit");


            System.out.println("Choose one of the options: (1-5) ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    displayRoutes();
                    break;
                case 2:
                    displayRoutes();
                    makeBooking();
                    break;
                case 3:
                    displayAllBookings();
                    break;
                case 4:
                    System.out.println("Total Daily Revenue for the day: £" + getTotalRevenue());
                    System.out.println("Total bookings: " + bookings.size());
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    System.out.println("Thank you for using the UK Train Ticket Booking System.");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose (1-5)");
            }
        }
        scanner.close();
    }

    private static double getTotalRevenue() {
        double totalDailyRevenue = 0;

        for(Booking booking : bookings){
            totalDailyRevenue += booking.calculateTotalFare();
        }

        return totalDailyRevenue;
    }

    private static void displayAllBookings() {
        if(bookings.isEmpty()){
            System.out.println("No bookings have been made today!");
            return;
        }

        for (Booking booking : bookings){
            booking.viewAllBooking();
        }
    }

    private static void makeBooking() {
        System.out.println("Choose one of the routes");
        int routeChoice = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Passenger> passengers = passengerInfo();

        Route route = routes.get(routeChoice-1);

        Booking booking = new Booking(route, passengers);
        bookings.add(booking);

        booking.displayBookingSummary();
    }

    private static ArrayList<Passenger> passengerInfo() {
        ArrayList<Passenger> passengers = new ArrayList<>();
        while(true){
            System.out.println("Enter the passenger name: ");
            String name = scanner.nextLine();

            System.out.println("Enter passenger age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Does the passenger has 16–25 rail card? (Y/N)");
            char railCardChoice = scanner.next().charAt(0);
            scanner.nextLine();

            boolean hasRailCard;

            if(railCardChoice == 'Y'){
                hasRailCard = true;
            }else{
                hasRailCard = false;
            }

            Passenger passenger = new Passenger(name, age, hasRailCard);
            passengers.add(passenger);

            System.out.println("Do you want to add another passenger to this booking? (Y/N)");
            char hasExtraPassenger = scanner.next().charAt(0);
            scanner.nextLine();

            if(hasExtraPassenger == 'N'){
                break;
            }
        }

        return passengers;
    }

    private static void initialisedRoutes() {
        routes.add(new Route("London", "Manchester", 85));
        routes.add(new Route("Edinburgh", "Glasgow", 30));
        routes.add(new Route("Birmingham", "Cardiff", 50));
        routes.add(new Route("London", "Edinburgh", 120));
        routes.add(new Route("Liverpool", "london", 90));
    }

    private static void displayRoutes() {
        System.out.println("Available routes for today:");
        for(int i = 0; i < routes.size(); i++){
            System.out.println(i+1 + ". " + routes.get(i));
        }
        System.out.println("\n");
    }
}
