package com.parking.service.impl;

import com.parking.entity.Vehicle;
import com.parking.repository.VehicleRepository;
import com.parking.service.ParkingService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {
    private static final int MOTORCYCLE_SPOTS = 6;
    private static final int LIGHT_VEHICLE_SPOTS = 5;
    private static final double MOTORCYCLE_RATE = 62.0;
    private static final double LIGHT_VEHICLE_RATE = 120.0;
    private static final double DISCOUNT_RATE = 0.25;

    private final VehicleRepository vehicleRepository;

    public ParkingServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle registerVehicle(Vehicle vehicle) {
        if (!isSpotAvailable(vehicle.getType())) {
            throw new RuntimeException("No spots available for " + vehicle.getType());
        }

        if (vehicleRepository.existsByLicensePlateAndExitTimeIsNull(vehicle.getLicensePlate())) {
            throw new RuntimeException("Vehicle is already parked");
        }

        vehicle.setEntryTime(LocalDateTime.now());
        vehicle.setAssignedSpot(assignSpot(vehicle.getType()));
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        Vehicle existingVehicle = getVehicleById(id);
        existingVehicle.setLicensePlate(vehicle.getLicensePlate());
        existingVehicle.setType(vehicle.getType());
        existingVehicle.setHasDiscount(vehicle.isHasDiscount());
        existingVehicle.setEntryTime(vehicle.getEntryTime());
        existingVehicle.setExitTime(vehicle.getExitTime());
        return vehicleRepository.save(existingVehicle);
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    @Override
    public double calculateTotalEarnings() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream()
                .filter(v -> v.getExitTime() != null)
                .mapToDouble(this::calculateParkingFee)
                .sum();
    }

    @Override
    public void forceExitAllVehicles() {
        List<Vehicle> parkedVehicles = vehicleRepository.findByExitTimeIsNull();
        LocalDateTime now = LocalDateTime.now();
        
        for (Vehicle vehicle : parkedVehicles) {
            vehicle.setExitTime(now);
            vehicleRepository.save(vehicle);
        }
    }

    @Override
    public boolean isSpotAvailable(Vehicle.VehicleType type) {
        long occupiedSpots = vehicleRepository.findByTypeAndExitTimeIsNull(type).size();
        return type == Vehicle.VehicleType.MOTORCYCLE ? 
               occupiedSpots < MOTORCYCLE_SPOTS : 
               occupiedSpots < LIGHT_VEHICLE_SPOTS;
    }

    @Override
    public String assignSpot(Vehicle.VehicleType type) {
        long occupiedSpots = vehicleRepository.findByTypeAndExitTimeIsNull(type).size();
        return type == Vehicle.VehicleType.MOTORCYCLE ? 
               "M" + (occupiedSpots + 1) : 
               "V" + (occupiedSpots + 1);
    }

    private double calculateParkingFee(Vehicle vehicle) {
        Duration duration = Duration.between(vehicle.getEntryTime(), vehicle.getExitTime());
        long hours = duration.toHours();
        if (duration.toMinutes() % 60 > 0) {
            hours++;
        }

        double rate = vehicle.getType() == Vehicle.VehicleType.MOTORCYCLE ? 
                     MOTORCYCLE_RATE : LIGHT_VEHICLE_RATE;
        
        double total = hours * rate;
        
        if (vehicle.isHasDiscount()) {
            total *= (1 - DISCOUNT_RATE);
        }
        
        return total;
    }
} 