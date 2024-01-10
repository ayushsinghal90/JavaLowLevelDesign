package org.lowLevelDesign.parkingLot.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.ParkingSpot;
import org.lowLevelDesign.parkingLot.Entities.Constants.ParkingSpotType;
import org.lowLevelDesign.parkingLot.Entities.Parking.ParkingGarage;
import org.lowLevelDesign.parkingLot.Factories.ParkingSpotFactory;
import org.lowLevelDesign.parkingLot.Services.ParkingCatalogService;

/**
 * Controller class for managing parking garages and floors.
 *
 * @author ayushsinghal90
 */
@AllArgsConstructor
public class GarageManagerController {

  // Service for managing parking catalogs
  private final ParkingCatalogService parkingCatalogService;

  /**
   * Creates a parking garage with a default name.
   *
   * @return The created parking garage.
   */
  public ParkingGarage createParkingGarage() {
    return parkingCatalogService.createParkingGarage("parkingGarage1");
  }

  /**
   * Adds a parking floor to the specified garage with various types of parking spots.
   *
   * @param garageName The name of the garage to which the parking floor and spots are added.
   */
  public void addParkingFloorAndParkingSpot(String garageName) {
    List<ParkingSpot> parkingSpots = new ArrayList<>();
    addParkingSpot(ParkingSpotType.HANDICAPPED, parkingSpots);
    addParkingSpot(ParkingSpotType.CAR, parkingSpots);
    addParkingSpot(ParkingSpotType.BIKE, parkingSpots);
    addParkingSpot(ParkingSpotType.ELECTRIC_CAR, parkingSpots);
    addParkingSpot(ParkingSpotType.MINI_VAN, parkingSpots);

    Map<String, List<ParkingSpot>> parkingFloorWithParkingSpot = new HashMap<>();
    parkingFloorWithParkingSpot.put("parkingFloor1", parkingSpots);
    parkingCatalogService.addParkingFloorToGarage(garageName, parkingFloorWithParkingSpot);
  }

  /**
   * Adds parking spots of a specific type to the provided list.
   *
   * @param parkingSpotType The type of parking spot to be added.
   * @param parkingSpotList The list to which parking spots will be added.
   */
  private void addParkingSpot(ParkingSpotType parkingSpotType, List<ParkingSpot> parkingSpotList) {
    for (int i = 0; i < 5; i++) {
      parkingSpotList.add(ParkingSpotFactory.createParkingSpot(parkingSpotType));
    }
  }
}
