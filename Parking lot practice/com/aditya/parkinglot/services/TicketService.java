package com.aditya.parkinglot.services;

import com.aditya.parkinglot.models.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TicketService {
    private Map<Integer, Ticket> tickets;
    private ParkingLotService parkingLotService;

    public TicketService(ParkingLotService parkingLotService) {
        this.tickets = new ConcurrentHashMap<>();
        this.parkingLotService = parkingLotService;
    }

    public synchronized Ticket generateTicket(String parkingLotId, Vehicle vehicle) {
        // Find available slot
        ParkingSlot availableSlot = parkingLotService.findAvailableSlot(parkingLotId, vehicle);
        
        if (availableSlot == null) {
            System.out.println("No available slot for vehicle: " + vehicle.getVehicleNumber());
            return null;
        }

        // Generate ticket
        int ticketId = parkingLotService.generateTicketId();
        Ticket ticket = new Ticket(ticketId, availableSlot.getSlotId(), vehicle);
        
        // Park the vehicle
        boolean parked = parkingLotService.parkVehicle(parkingLotId, vehicle, availableSlot);
        
        if (parked) {
            tickets.put(ticketId, ticket);
            System.out.println("Ticket generated successfully!");
            System.out.println("Ticket ID: " + ticketId);
            System.out.println("Vehicle: " + vehicle.getVehicleNumber());
            System.out.println("Slot: " + availableSlot.getSlotId());
            System.out.println("Entry Time: " + ticket.getEntryTime());
            return ticket;
        } else {
            System.out.println("Failed to park vehicle: " + vehicle.getVehicleNumber());
            return null;
        }
    }

    public double calculateParkingFee(Ticket ticket) {
        if (ticket == null || ticket.isActive()) {
            return 0.0;
        }

        long durationHours = ticket.getDurationInHours();
        
        // Parking fee calculation (can be customized based on requirements)
        double hourlyRate = 10.0; // $10 per hour
        double fee = durationHours * hourlyRate;
        
        // Minimum fee of $5
        return Math.max(fee, 5.0);
    }

    public synchronized Ticket closeTicket(int ticketId) {
        Ticket ticket = tickets.get(ticketId);
        
        if (ticket == null) {
            System.out.println("Ticket not found: " + ticketId);
            return null;
        }

        if (!ticket.isActive()) {
            System.out.println("Ticket already closed: " + ticketId);
            return ticket;
        }

        // Find and unpark the vehicle
        Vehicle vehicle = parkingLotService.unparkVehicle("PL001", ticket.getVehicle());
        
        if (vehicle == null) {
            System.out.println("Vehicle not found for ticket: " + ticketId);
            return null;
        }

        // Close the ticket
        ticket.closeTicket();
        
        // Calculate fee
        double fee = calculateParkingFee(ticket);
        
        System.out.println("Ticket closed successfully!");
        System.out.println("Ticket ID: " + ticketId);
        System.out.println("Vehicle: " + vehicle.getVehicleNumber());
        System.out.println("Exit Time: " + ticket.getExitTime());
        System.out.println("Duration: " + ticket.getDurationInHours() + " hours");
        System.out.println("Parking Fee: $" + fee);
        
        return ticket;
    }

    public Ticket getTicket(int ticketId) {
        return tickets.get(ticketId);
    }

    public boolean isTicketActive(int ticketId) {
        Ticket ticket = tickets.get(ticketId);
        return ticket != null && ticket.isActive();
    }

    public synchronized void displayTicketInfo(int ticketId) {
        Ticket ticket = tickets.get(ticketId);
        
        if (ticket == null) {
            System.out.println("Ticket not found: " + ticketId);
            return;
        }

        System.out.println("=== Ticket Information ===");
        System.out.println("Ticket ID: " + ticket.getTicketId());
        System.out.println("Vehicle: " + ticket.getVehicle().getVehicleNumber());
        System.out.println("Vehicle Type: " + ticket.getVehicle().getVehicleType());
        System.out.println("Slot ID: " + ticket.getSlotId());
        System.out.println("Entry Time: " + ticket.getEntryTime());
        System.out.println("Status: " + (ticket.isActive() ? "Active" : "Closed"));
        
        if (!ticket.isActive()) {
            System.out.println("Exit Time: " + ticket.getExitTime());
            System.out.println("Duration: " + ticket.getDurationInHours() + " hours");
            System.out.println("Parking Fee: $" + calculateParkingFee(ticket));
        }
        System.out.println();
    }

    public synchronized void displayAllTickets() {
        System.out.println("=== All Tickets ===");
        for (Ticket ticket : tickets.values()) {
            System.out.println("Ticket ID: " + ticket.getTicketId() + 
                             " | Vehicle: " + ticket.getVehicle().getVehicleNumber() + 
                             " | Status: " + (ticket.isActive() ? "Active" : "Closed"));
        }
        System.out.println();
    }
} 