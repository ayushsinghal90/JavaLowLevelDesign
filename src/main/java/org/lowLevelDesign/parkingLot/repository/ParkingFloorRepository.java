package org.lowLevelDesign.parkingLot.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.ParkingSpot;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.Vehicle;
import org.lowLevelDesign.parkingLot.Entities.Constants.ParkingSpotType;
import org.lowLevelDesign.parkingLot.Entities.Constants.VehicleType;
import org.lowLevelDesign.parkingLot.Entities.Parking.ParkingFloor;

public class ParkingFloorRepository {
  static final Logger LOG = LogManager.getLogger(ParkingFloor.class);

  public ParkingSpot getParkingSpotThroughVehicle(ParkingFloor parkingFloor, Vehicle vehicle) {
    ParkingSpotType parkingSpotType;
    if (vehicle.getVehicleType() == VehicleType.CAR) {
      parkingSpotType = ParkingSpotType.CAR;
    } else if (vehicle.getVehicleType() == VehicleType.MINI_VAN) {
      parkingSpotType = ParkingSpotType.MINI_VAN;
    } else if (vehicle.getVehicleType() == VehicleType.BIKE) {
      parkingSpotType = ParkingSpotType.BIKE;
    } else if (vehicle.getVehicleType() == VehicleType.ELECTRIC_CAR) {
      parkingSpotType = ParkingSpotType.ELECTRIC_CAR;
    } else {
      parkingSpotType = ParkingSpotType.HANDICAPPED;
    }

    return parkingFloor
        .getParkingSpots()
        .stream()
        .filter(
            parkingSpot ->
                parkingSpot.getParkingSpotType() == parkingSpotType && !parkingSpot.isOccupied())
        .findFirst()
        .orElse(null);
  }
}
