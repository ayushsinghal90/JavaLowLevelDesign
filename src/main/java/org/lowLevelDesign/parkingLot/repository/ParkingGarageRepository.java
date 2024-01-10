package org.lowLevelDesign.parkingLot.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.lowLevelDesign.parkingLot.Entities.Parking.ParkingFloor;
import org.lowLevelDesign.parkingLot.Entities.Parking.ParkingGarage;

/**
 * Repository class for managing parking garages and floors.
 *
 * @author ayushsinghal90
 */
public class ParkingGarageRepository {
  // Map to store parking garages by name
  private static final Map<String, ParkingGarage> parkingGarageMap = new HashMap<>();

  /**
   * Adds a new parking floor to the specified parking garage.
   *
   * @param garageName The name of the parking garage.
   * @param floorName The name of the new parking floor.
   * @return The newly added parking floor.
   */
  public ParkingFloor addParkingFloor(String garageName, String floorName) {
    ParkingFloor parkingFloor = createNewParkingFloor(floorName);
    getParkingGarage(garageName).getParkingFloors().add(parkingFloor);
    return parkingFloor;
  }

  /**
   * Retrieves a parking floor from the specified parking garage by name.
   *
   * @param garageName The name of the parking garage.
   * @param floorName The name of the parking floor to retrieve.
   * @return The parking floor with the specified name, or null if not found.
   */
  public ParkingFloor getParkingFloor(String garageName, String floorName) {
    return getParkingGarage(garageName)
        .getParkingFloors()
        .stream()
        .filter(parkingFloor -> parkingFloor.getName().equals(floorName))
        .findFirst()
        .orElse(null);
  }

  /**
   * Retrieves a parking garage by name.
   *
   * @param garageName The name of the parking garage to retrieve.
   * @return The parking garage with the specified name, or null if not found.
   */
  public ParkingGarage getParkingGarage(String garageName) {
    return parkingGarageMap.get(garageName);
  }

  /**
   * Adds a new parking garage to the repository.
   *
   * @param garageName The name of the new parking garage.
   * @return The newly added parking garage.
   */
  public ParkingGarage addParkingGarage(String garageName) {
    ParkingGarage parkingGarage = createNewParkingGarage(garageName);
    parkingGarageMap.put(garageName, parkingGarage);
    return parkingGarage;
  }

  /**
   * Creates a new parking garage instance.
   *
   * @param garageName The name of the new parking garage.
   * @return A new parking garage instance.
   */
  private ParkingGarage createNewParkingGarage(String garageName) {
    return new ParkingGarage(garageName, new ArrayList<>());
  }

  /**
   * Creates a new parking floor instance.
   *
   * @param floorName The name of the new parking floor.
   * @return A new parking floor instance.
   */
  public ParkingFloor createNewParkingFloor(String floorName) {
    return new ParkingFloor(floorName, new ArrayList<>());
  }
}
