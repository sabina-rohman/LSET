package uk.lset;

import java.util.ArrayList;

public class Booking {
    private Route route;
    private ArrayList<Passenger> passengers;
    private double totalFare;

    public Booking(Route route, ArrayList<Passenger> passengers){
        this.route = route;
        this.passengers = passengers;
    }

    public double calculateTotalFare(){
        totalFare = 0;
        for(Passenger passenger : passengers){
            double discountRate = passenger.getDiscountRate();
            double fare = route.getBaseFare() * (1- discountRate);
            totalFare += fare;
        }

        return totalFare;
    }

    public double getTotalFare() {
       return calculateTotalFare();
    }

    public void displayBookingSummary(){
        System.out.println("---Booking confirmation---");
        System.out.println("Route: " + route.getStartStation() + " -> " + route.getEndStation());
        System.out.println("Passengers: " + passengers.size());
        System.out.println("Total fare: £" + getTotalFare());
    }

    public void viewAllBooking(){
        System.out.println("---Booking summary----");
        System.out.println("Route: " + route.getStartStation() + " -> " + route.getEndStation());
        System.out.println("Passengers: " + passengers.size());
        System.out.println("Total fare: £" + getTotalFare());

        for(Passenger passenger : passengers){
            System.out.println(passenger + ". Applied discount: " + passenger.getDiscountString());
        }

        System.out.println("------------------------");
    }

    @Override
    public String toString() {
        return "Booking[" +
                "route=" + route +
                ", passengers=" + passengers +
                ", totalFare=" + totalFare +
                ']';
    }
}
