package com.parking.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "vehicles")
@Schema(description = "Representa un vehículo en el sistema de parqueadero")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del vehículo", example = "1")
    private Long id;

    @Column(nullable = false, unique = true)
    @Schema(description = "Placa del vehículo", example = "ABC123")
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Tipo de vehículo", example = "MOTORCYCLE")
    private VehicleType type;

    @Column(nullable = false)
    @Schema(description = "Indica si el vehículo aplica para descuento por ser eléctrico o híbrido", example = "true")
    private boolean hasDiscount;

    @Column(name = "entry_time")
    @Schema(description = "Fecha y hora de ingreso del vehículo", example = "2024-04-25T10:00:00")
    private LocalDateTime entryTime;

    @Column(name = "exit_time")
    @Schema(description = "Fecha y hora de salida del vehículo", example = "2024-04-25T12:00:00")
    private LocalDateTime exitTime;

    @Column(name = "assigned_spot")
    @Schema(description = "Espacio asignado al vehículo", example = "M1")
    private String assignedSpot;

    @Schema(description = "Tipos de vehículos permitidos en el parqueadero")
    public enum VehicleType {
        @Schema(description = "Motocicleta", example = "MOTORCYCLE")
        MOTORCYCLE,
        
        @Schema(description = "Vehículo ligero", example = "LIGHT_VEHICLE")
        LIGHT_VEHICLE
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public boolean isHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public String getAssignedSpot() {
        return assignedSpot;
    }

    public void setAssignedSpot(String assignedSpot) {
        this.assignedSpot = assignedSpot;
    }
} 