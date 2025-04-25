package com.parking.service;

import com.parking.entity.Vehicle;
import java.util.List;

public interface ParkingService {
    Vehicle registerVehicle(Vehicle vehicle);
    Vehicle updateVehicle(Long id, Vehicle vehicle);
    void deleteVehicle(Long id);
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(Long id);
    double calculateTotalEarnings();
    void forceExitAllVehicles();
    boolean isSpotAvailable(Vehicle.VehicleType type);
    String assignSpot(Vehicle.VehicleType type);
} 