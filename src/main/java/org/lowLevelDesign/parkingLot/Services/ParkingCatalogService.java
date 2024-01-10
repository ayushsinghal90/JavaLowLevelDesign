package org.lowLevelDesign.parkingLot.Services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.ParkingSpot;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.Vehicle;
import org.lowLevelDesign.parkingLot.Entities.Parking.ParkingFloor;
import org.lowLevelDesign.parkingLot.Entities.Parking.ParkingGarage;
import org.lowLevelDesign.parkingLot.Exception.NotFoundException;
import org.lowLevelDesign.parkingLot.repository.ParkingFloorRepository;
import org.lowLevelDesign.parkingLot.repository.ParkingGarageRepository;

/**
 * Service class for managing parking garage and floor information.
 *
 * @author ayushsinghal90
 */
@AllArgsConstructor
public class ParkingCatalogService {

  // Logger for logging service-related events
  private static final Logger LOG = LogManager.getLogger(ParkingCatalogService.class);

  // Repositories for managing parking garage and floor data
  private final ParkingGarageRepository parkingGarageRepository;
  private final ParkingFloorRepository parkingFloorRepository;

  /**
   * Creates a new parking garage with the given name.
   *
   * @param name The name of the parking garage.
   * @return The created parking garage.
   */
  public ParkingGarage createParkingGarage(String name) {
    return parkingGarageRepository.addParkingGarage(name);
  }

  /**
   * Adds parking floors with associated parking spots to a parking garage.
   *
   * @param garageName The name of the parking garage.
   * @param parkingFloorWithParkingSpots A map containing parking floor names and corresponding
   *     lists of parking spots.
   */
  public void addParkingFloorToGarage(
      String garageName, Map<String, List<ParkingSpot>> parkingFloorWithParkingSpots) {
    for (String floorName : parkingFloorWithParkingSpots.keySet()) {
      ParkingFloor parkingFloor = getOrCreateParkingFloor(garageName, floorName);
      parkingFloor.getParkingSpots().addAll(parkingFloorWithParkingSpots.get(floorName));
    }
  }

  /**
   * Retrieves or creates a parking floor for a given garage and floor name.
   *
   * @param garageName The name of the parking garage.
   * @param floorName The name of the parking floor.
   * @return The existing or newly created parking floor.
   */
  private ParkingFloor getOrCreateParkingFloor(String garageName, String floorName) {
    return Optional.ofNullable(parkingGarageRepository.getParkingFloor(garageName, floorName))
        .orElse(parkingGarageRepository.addParkingFloor(garageName, floorName));
  }

  /**
   * Retrieves a parking spot for a given vehicle in a specific garage and floor.
   *
   * @param garageName The name of the parking garage.
   * @param floorName The name of the parking floor.
   * @param vehicle The vehicle for which to find a parking spot.
   * @return The parking spot for the given vehicle.
   * @throws NotFoundException if the parking floor or garage does not exist.
   */
  public ParkingSpot getParkingSpotForVehicle(
      String garageName, String floorName, Vehicle vehicle) {
    return Optional.of(parkingGarageRepository.getParkingFloor(garageName, floorName))
        .map(
            parkingFloor ->
                parkingFloorRepository.getParkingSpotThroughVehicle(parkingFloor, vehicle))
        .orElseThrow(
            () ->
                new NotFoundException(
                    String.format(
                        "Parking floor: %s does not exist in garage: %s", floorName, garageName)));
  }
}
