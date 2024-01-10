package org.lowLevelDesign.parkingLot.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.ParkingSpot;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.Vehicle;
import org.lowLevelDesign.parkingLot.Entities.Constants.ParkingSpotType;
import org.lowLevelDesign.parkingLot.Entities.Parking.ParkingFloor;

/**
 * Repository class for managing parking spots on a parking floor.
 *
 * @author ayushsinghal90
 */
public class ParkingFloorRepository {
  // Logger for logging messages
  static final Logger LOG = LogManager.getLogger(ParkingFloor.class);

  /**
   * Retrieves an available parking spot of the appropriate type for a given vehicle on the parking
   * floor.
   *
   * @param parkingFloor The parking floor to search for available spots.
   * @param vehicle The vehicle for which a parking spot is sought.
   * @return An available parking spot of the appropriate type, or null if none is available.
   */
  public ParkingSpot getParkingSpotThroughVehicle(ParkingFloor parkingFloor, Vehicle vehicle) {
    // Determine the parking spot type based on the vehicle type
    ParkingSpotType parkingSpotType;
    switch (vehicle.getVehicleType()) {
      case CAR:
        parkingSpotType = ParkingSpotType.CAR;
        break;
      case MINI_VAN:
        parkingSpotType = ParkingSpotType.MINI_VAN;
        break;
      case BIKE:
        parkingSpotType = ParkingSpotType.BIKE;
        break;
      case ELECTRIC_CAR:
        parkingSpotType = ParkingSpotType.ELECTRIC_CAR;
        break;
      default:
        parkingSpotType = ParkingSpotType.HANDICAPPED;
        break;
    }

    // Find the first available parking spot of the determined type
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
