export interface Vehicle {
    id?: number;
    licensePlate: string;
    type: VehicleType;
    hasDiscount: boolean;
    entryTime?: string;
    exitTime?: string;
    assignedSpot?: string;
}

export enum VehicleType {
    MOTORCYCLE = 'MOTORCYCLE',
    LIGHT_VEHICLE = 'LIGHT_VEHICLE'
} 