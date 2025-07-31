package com.aditya.parkinglot.models;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private int floorNumber;
    private List<ParkingSlot> slots;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.slots = new ArrayList<>();
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }

    public void setSlots(List<ParkingSlot> slots) {
        this.slots = new ArrayList<>(slots);
    }

    public synchronized void addSlot(ParkingSlot slot) {
        slots.add(slot);
    }

    public synchronized ParkingSlot findAvailableSlot(Vehicle vehicle) {
        for (ParkingSlot slot : slots) {
            if (slot.canParkVehicle(vehicle)) {
                return slot;
            }
        }
        return null;
    }

    public synchronized int getAvailableSlotsCount(VehicleType vehicleType) {
        int count = 0;
        for (ParkingSlot slot : slots) {
            if (!slot.getIsOccupied() && slot.getAcceptedVehicleType() == vehicleType) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return "ParkingFloor{" +
                "floorNumber=" + floorNumber +
                ", slots=" + slots +
                '}';
    }
} 