package com.parking.controller;

import com.parking.entity.Vehicle;
import com.parking.service.ParkingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
@Tag(name = "Parking Controller", description = "API para la gestión del parqueadero")
public class ParkingController {
    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @Operation(summary = "Registrar un nuevo vehículo", description = "Registra un vehículo en el parqueadero")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vehículo registrado exitosamente",
            content = @Content(schema = @Schema(implementation = Vehicle.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o no hay espacios disponibles")
    })
    @PostMapping("/register")
    public ResponseEntity<Vehicle> registerVehicle(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(parkingService.registerVehicle(vehicle));
    }

    @Operation(summary = "Actualizar información de un vehículo", description = "Actualiza la información de un vehículo existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vehículo actualizado exitosamente",
            content = @Content(schema = @Schema(implementation = Vehicle.class))),
        @ApiResponse(responseCode = "404", description = "Vehículo no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(
            @Parameter(description = "ID del vehículo a actualizar") @PathVariable Long id,
            @RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(parkingService.updateVehicle(id, vehicle));
    }

    @Operation(summary = "Eliminar un vehículo", description = "Elimina un vehículo del sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vehículo eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Vehículo no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(
            @Parameter(description = "ID del vehículo a eliminar") @PathVariable Long id) {
        parkingService.deleteVehicle(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Obtener todos los vehículos", description = "Retorna una lista de todos los vehículos registrados")
    @ApiResponse(responseCode = "200", description = "Lista de vehículos obtenida exitosamente",
        content = @Content(schema = @Schema(implementation = Vehicle.class)))
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(parkingService.getAllVehicles());
    }

    @Operation(summary = "Obtener un vehículo por ID", description = "Retorna la información de un vehículo específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vehículo encontrado exitosamente",
            content = @Content(schema = @Schema(implementation = Vehicle.class))),
        @ApiResponse(responseCode = "404", description = "Vehículo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(
            @Parameter(description = "ID del vehículo a buscar") @PathVariable Long id) {
        return ResponseEntity.ok(parkingService.getVehicleById(id));
    }

    @Operation(summary = "Obtener ganancias totales", description = "Calcula y retorna las ganancias totales del parqueadero")
    @ApiResponse(responseCode = "200", description = "Ganancias calculadas exitosamente",
        content = @Content(schema = @Schema(type = "number", format = "double")))
    @GetMapping("/earnings")
    public ResponseEntity<Double> getTotalEarnings() {
        return ResponseEntity.ok(parkingService.calculateTotalEarnings());
    }

    @Operation(summary = "Forzar salida de todos los vehículos", description = "Registra la salida de todos los vehículos estacionados")
    @ApiResponse(responseCode = "200", description = "Salida forzada realizada exitosamente")
    @PostMapping("/force-exit")
    public ResponseEntity<Void> forceExitAllVehicles() {
        parkingService.forceExitAllVehicles();
        return ResponseEntity.ok().build();
    }
} 