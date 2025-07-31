import com.aditya.parkinglot.models.*;
import com.aditya.parkinglot.services.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrencyTest {
    public static void main(String[] args) {
        // Initialize services
        ParkingLotService parkingLotService = new ParkingLotService();
        TicketService ticketService = new TicketService(parkingLotService);

        // Create parking lot
        System.out.println("Creating parking lot...");
        parkingLotService.createParkingLot("PL001", 2, 10);
        
        // Display initial status
        parkingLotService.displayParkingLotStatus("PL001");

        // Test concurrent parking operations
        System.out.println("=== Testing Concurrent Parking Operations ===");
        
        ExecutorService executor = Executors.newFixedThreadPool(10);
        
        // Simulate multiple vehicles arriving simultaneously
        for (int i = 1; i <= 15; i++) {
            final int vehicleId = i;
            executor.submit(() -> {
                try {
                    VehicleType vehicleType;
                    if (vehicleId % 3 == 0) {
                        vehicleType = VehicleType.CAR;
                    } else if (vehicleId % 3 == 1) {
                        vehicleType = VehicleType.BIKE;
                    } else {
                        vehicleType = VehicleType.TRUCK;
                    }
                    
                    Vehicle vehicle = new Vehicle("VEH" + String.format("%03d", vehicleId), vehicleType);
                    Ticket ticket = ticketService.generateTicket("PL001", vehicle);
                    
                    if (ticket != null) {
                        System.out.println("Thread " + Thread.currentThread().getName() + 
                                         " parked vehicle " + vehicle.getVehicleNumber() + 
                                         " with ticket " + ticket.getTicketId());
                    } else {
                        System.out.println("Thread " + Thread.currentThread().getName() + 
                                         " failed to park vehicle " + vehicle.getVehicleNumber());
                    }
                } catch (Exception e) {
                    System.err.println("Error in thread " + Thread.currentThread().getName() + ": " + e.getMessage());
                }
            });
        }
        
        // Wait for all parking operations to complete
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Test interrupted: " + e.getMessage());
        }
        
        // Display status after concurrent parking
        System.out.println("\n=== Status After Concurrent Parking ===");
        parkingLotService.displayParkingLotStatus("PL001");
        
        // Test concurrent unparking operations
        System.out.println("=== Testing Concurrent Unparking Operations ===");
        
        ExecutorService unparkExecutor = Executors.newFixedThreadPool(5);
        
        // Simulate multiple vehicles leaving simultaneously
        for (int i = 1; i <= 8; i++) {
            final int ticketId = i;
            unparkExecutor.submit(() -> {
                try {
                    Ticket ticket = ticketService.getTicket(ticketId);
                    if (ticket != null && ticket.isActive()) {
                        Ticket closedTicket = ticketService.closeTicket(ticketId);
                        if (closedTicket != null) {
                            System.out.println("Thread " + Thread.currentThread().getName() + 
                                             " unparked vehicle " + closedTicket.getVehicle().getVehicleNumber() + 
                                             " from ticket " + ticketId);
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Error in unpark thread " + Thread.currentThread().getName() + ": " + e.getMessage());
                }
            });
        }
        
        // Wait for all unparking operations to complete
        unparkExecutor.shutdown();
        try {
            unparkExecutor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Test interrupted: " + e.getMessage());
        }
        
        // Display final status
        System.out.println("\n=== Final Status ===");
        parkingLotService.displayParkingLotStatus("PL001");
        
        // Display all tickets
        ticketService.displayAllTickets();
        
        // Test available slots
        System.out.println("=== Available Slots ===");
        System.out.println("Available CAR slots: " + parkingLotService.getAvailableSlots("PL001", VehicleType.CAR));
        System.out.println("Available BIKE slots: " + parkingLotService.getAvailableSlots("PL001", VehicleType.BIKE));
        System.out.println("Available TRUCK slots: " + parkingLotService.getAvailableSlots("PL001", VehicleType.TRUCK));
        
        System.out.println("\n=== Concurrency Test Completed Successfully! ===");
    }
} 