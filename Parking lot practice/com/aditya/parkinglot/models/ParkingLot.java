package com.aditya.parkinglot.models;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<ParkingFloor> floors;
    private String parkingLotId;

    public ParkingLot(String parkingLotId) {
        this.parkingLotId = parkingLotId;
        this.floors = new ArrayList<>();
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }

    public void setFloors(List<ParkingFloor> floors) {
        this.floors = new ArrayList<>(floors);
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public synchronized void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public synchronized ParkingSlot findAvailableSlot(Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            ParkingSlot slot = floor.findAvailableSlot(vehicle);
            if (slot != null) {
                return slot;
            }
        }
        return null;
    }

    public synchronized int getTotalAvailableSlots(VehicleType vehicleType) {
        int total = 0;
        for (ParkingFloor floor : floors) {
            total += floor.getAvailableSlotsCount(vehicleType);
        }
        return total;
    }

    public synchronized ParkingSlot findSlotByVehicle(Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            for (ParkingSlot slot : floor.getSlots()) {
                if (slot.getIsOccupied() && slot.getOccupiedVehicle().getVehicleNumber().equals(vehicle.getVehicleNumber())) {
                    return slot;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "floors=" + floors +
                ", parkingLotId='" + parkingLotId + '\'' +
                '}';
    }
} 