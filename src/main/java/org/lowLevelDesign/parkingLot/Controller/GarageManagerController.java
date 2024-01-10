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

@AllArgsConstructor
public class GarageManagerController {
  private final ParkingCatalogService parkingCatalogService;

  public ParkingGarage createParkingGarage() {
    return parkingCatalogService.createParkingGarage("parkingGarage1");
  }

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

  private void addParkingSpot(ParkingSpotType parkingSpotType, List<ParkingSpot> parkingSpotList) {
    for (int i = 0; i < 5; i++) {
      parkingSpotList.add(ParkingSpotFactory.createParkingSpot(parkingSpotType));
    }
  }
}
