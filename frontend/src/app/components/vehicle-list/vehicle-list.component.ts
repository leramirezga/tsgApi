import { Component, OnInit } from '@angular/core';
import { VehicleService } from '../../services/vehicle.service';
import { Vehicle, VehicleType } from '../../models/vehicle.model';

@Component({
  selector: 'app-vehicle-list',
  templateUrl: './vehicle-list.component.html',
  styleUrls: ['./vehicle-list.component.scss']
})
export class VehicleListComponent implements OnInit {
  vehicles: Vehicle[] = [];
  totalEarnings: number = 0;
  newVehicle: Vehicle = {
    licensePlate: '',
    type: VehicleType.MOTORCYCLE,
    hasDiscount: false
  };
  vehicleTypes = Object.values(VehicleType);

  constructor(private vehicleService: VehicleService) { }

  ngOnInit(): void {
    this.loadVehicles();
    this.loadTotalEarnings();
  }

  loadVehicles(): void {
    this.vehicleService.getAllVehicles().subscribe(
      vehicles => this.vehicles = vehicles,
      error => console.error('Error loading vehicles:', error)
    );
  }

  loadTotalEarnings(): void {
    this.vehicleService.getTotalEarnings().subscribe(
      earnings => this.totalEarnings = earnings,
      error => console.error('Error loading earnings:', error)
    );
  }

  registerVehicle(): void {
    this.vehicleService.registerVehicle(this.newVehicle).subscribe(
      () => {
        this.loadVehicles();
        this.newVehicle = {
          licensePlate: '',
          type: VehicleType.MOTORCYCLE,
          hasDiscount: false
        };
      },
      error => console.error('Error registering vehicle:', error)
    );
  }

  deleteVehicle(id: number): void {
    if (confirm('¿Está seguro de eliminar este vehículo?')) {
      this.vehicleService.deleteVehicle(id).subscribe(
        () => this.loadVehicles(),
        error => console.error('Error deleting vehicle:', error)
      );
    }
  }

  forceExitAll(): void {
    if (confirm('¿Está seguro de forzar la salida de todos los vehículos?')) {
      this.vehicleService.forceExitAllVehicles().subscribe(
        () => {
          this.loadVehicles();
          this.loadTotalEarnings();
        },
        error => console.error('Error forcing exit:', error)
      );
    }
  }
} 