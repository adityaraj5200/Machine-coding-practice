package com.aditya.parkinglot.services;

import com.aditya.parkinglot.models.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingLotService {
    private Map<String, ParkingLot> parkingLots;
    private static AtomicInteger ticketIdCounter = new AtomicInteger(1);

    public ParkingLotService() {
        this.parkingLots = new ConcurrentHashMap<>();
    }

    public synchronized ParkingLot createParkingLot(String parkingLotId, int floors, int slotsPerFloor) {
        ParkingLot parkingLot = new ParkingLot(parkingLotId);
        
        for (int floor = 1; floor <= floors; floor++) {
            ParkingFloor parkingFloor = new ParkingFloor(floor);
            
            // Create slots for each floor
            for (int slot = 1; slot <= slotsPerFloor; slot++) {
                // Distribute vehicle types across slots
                VehicleType vehicleType;
                if (slot <= slotsPerFloor * 0.6) {
                    vehicleType = VehicleType.CAR;
                } else if (slot <= slotsPerFloor * 0.8) {
                    vehicleType = VehicleType.BIKE;
                } else {
                    vehicleType = VehicleType.TRUCK;
                }
                
                ParkingSlot parkingSlot = new ParkingSlot(slot, vehicleType);
                parkingFloor.addSlot(parkingSlot);
            }
            
            parkingLot.addFloor(parkingFloor);
        }
        
        parkingLots.put(parkingLotId, parkingLot);
        return parkingLot;
    }

    public ParkingLot getParkingLot(String parkingLotId) {
        return parkingLots.get(parkingLotId);
    }

    public boolean isParkingLotFull(String parkingLotId, VehicleType vehicleType) {
        ParkingLot parkingLot = parkingLots.get(parkingLotId);
        if (parkingLot == null) {
            return true;
        }
        return parkingLot.getTotalAvailableSlots(vehicleType) == 0;
    }

    public int getAvailableSlots(String parkingLotId, VehicleType vehicleType) {
        ParkingLot parkingLot = parkingLots.get(parkingLotId);
        if (parkingLot == null) {
            return 0;
        }
        return parkingLot.getTotalAvailableSlots(vehicleType);
    }

    public ParkingSlot findAvailableSlot(String parkingLotId, Vehicle vehicle) {
        ParkingLot parkingLot = parkingLots.get(parkingLotId);
        if (parkingLot == null) {
            return null;
        }
        return parkingLot.findAvailableSlot(vehicle);
    }

    public ParkingSlot findVehicleSlot(String parkingLotId, Vehicle vehicle) {
        ParkingLot parkingLot = parkingLots.get(parkingLotId);
        if (parkingLot == null) {
            return null;
        }
        return parkingLot.findSlotByVehicle(vehicle);
    }

    public boolean parkVehicle(String parkingLotId, Vehicle vehicle, ParkingSlot slot) {
        if (slot == null) {
            return false;
        }
        
        return slot.parkVehicle(vehicle);
    }

    public Vehicle unparkVehicle(String parkingLotId, Vehicle vehicle) {
        ParkingSlot slot = findVehicleSlot(parkingLotId, vehicle);
        if (slot == null) {
            return null;
        }
        
        return slot.unparkVehicle();
    }

    public int generateTicketId() {
        return ticketIdCounter.getAndIncrement();
    }

    public synchronized void displayParkingLotStatus(String parkingLotId) {
        ParkingLot parkingLot = parkingLots.get(parkingLotId);
        if (parkingLot == null) {
            System.out.println("Parking lot not found: " + parkingLotId);
            return;
        }

        System.out.println("=== Parking Lot Status: " + parkingLotId + " ===");
        for (ParkingFloor floor : parkingLot.getFloors()) {
            System.out.println("Floor " + floor.getFloorNumber() + ":");
            for (ParkingSlot slot : floor.getSlots()) {
                String status = slot.getIsOccupied() ? 
                    "Occupied by " + slot.getOccupiedVehicle().getVehicleNumber() : 
                    "Available";
                System.out.println("  Slot " + slot.getSlotId() + " (" + 
                    slot.getAcceptedVehicleType() + "): " + status);
            }
        }
        System.out.println();
    }
} 