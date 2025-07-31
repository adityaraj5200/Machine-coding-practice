package com.aditya.parkinglot.models;

import java.util.concurrent.atomic.AtomicBoolean;

public class ParkingSlot {
    private int slotId;
    private AtomicBoolean isOccupied;
    private Vehicle occupiedVehicle;
    private VehicleType acceptedVehicleType;

    public ParkingSlot(int slotId, VehicleType acceptedVehicleType) {
        this.slotId = slotId;
        this.acceptedVehicleType = acceptedVehicleType;
        this.isOccupied = new AtomicBoolean(false);
        this.occupiedVehicle = null;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public Boolean getIsOccupied() {
        return isOccupied.get();
    }

    public void setIsOccupied(Boolean occupied) {
        isOccupied.set(occupied);
    }

    public Vehicle getOccupiedVehicle() {
        return occupiedVehicle;
    }

    public void setOccupiedVehicle(Vehicle occupiedVehicle) {
        this.occupiedVehicle = occupiedVehicle;
    }

    public VehicleType getAcceptedVehicleType() {
        return acceptedVehicleType;
    }

    public void setAcceptedVehicleType(VehicleType acceptedVehicleType) {
        this.acceptedVehicleType = acceptedVehicleType;
    }

    public boolean canParkVehicle(Vehicle vehicle) {
        return !isOccupied.get() && acceptedVehicleType == vehicle.getVehicleType();
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (canParkVehicle(vehicle)) {
            this.occupiedVehicle = vehicle;
            this.isOccupied.set(true);
            return true;
        }
        return false;
    }

    public Vehicle unparkVehicle() {
        Vehicle vehicle = this.occupiedVehicle;
        this.occupiedVehicle = null;
        this.isOccupied.set(false);
        return vehicle;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "slotId=" + slotId +
                ", isOccupied=" + isOccupied.get() +
                ", occupiedVehicle=" + occupiedVehicle +
                ", acceptedVehicleType=" + acceptedVehicleType +
                '}';
    }
} 