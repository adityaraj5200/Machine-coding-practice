// Entities I will need:
// Vehicle
// Parkingslots
// Parkingfloor
// ParkingLot
// Ticket

import com.aditya.parkinglot.models.*;
import com.aditya.parkinglot.services.*;

public class ParkingLotSystem {
    public static void main(String[] args) {
        // Initialize services
        ParkingLotService parkingLotService = new ParkingLotService();
        TicketService ticketService = new TicketService(parkingLotService);

        // Create a parking lot with 2 floors and 10 slots per floor
        System.out.println("Creating parking lot...");
        ParkingLot parkingLot = parkingLotService.createParkingLot("PL001", 2, 10);
        
        // Display initial status
        parkingLotService.displayParkingLotStatus("PL001");

        // Test parking vehicles
        System.out.println("=== Testing Vehicle Parking ===");
        
        // Park a car
        Vehicle car1 = new Vehicle("CAR001", VehicleType.CAR);
        Ticket ticket1 = ticketService.generateTicket("PL001", car1);
        
        // Park a bike
        Vehicle bike1 = new Vehicle("BIKE001", VehicleType.BIKE);
        Ticket ticket2 = ticketService.generateTicket("PL001", bike1);
        
        // Park a truck
        Vehicle truck1 = new Vehicle("TRUCK001", VehicleType.TRUCK);
        Ticket ticket3 = ticketService.generateTicket("PL001", truck1);
        
        // Display status after parking
        parkingLotService.displayParkingLotStatus("PL001");
        
        // Display ticket information
        ticketService.displayTicketInfo(ticket1.getTicketId());
        ticketService.displayTicketInfo(ticket2.getTicketId());
        ticketService.displayTicketInfo(ticket3.getTicketId());
        
        // Test unparking
        System.out.println("=== Testing Vehicle Unparking ===");
        ticketService.closeTicket(ticket1.getTicketId());
        ticketService.closeTicket(ticket2.getTicketId());
        
        // Display final status
        parkingLotService.displayParkingLotStatus("PL001");
        
        // Display all tickets
        ticketService.displayAllTickets();
        
        // Test available slots
        System.out.println("=== Available Slots ===");
        System.out.println("Available CAR slots: " + parkingLotService.getAvailableSlots("PL001", VehicleType.CAR));
        System.out.println("Available BIKE slots: " + parkingLotService.getAvailableSlots("PL001", VehicleType.BIKE));
        System.out.println("Available TRUCK slots: " + parkingLotService.getAvailableSlots("PL001", VehicleType.TRUCK));
    }
}