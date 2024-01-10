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

@AllArgsConstructor
public class ParkingCatalogService {
  static final Logger LOG = LogManager.getLogger(ParkingService.class);

  private final ParkingGarageRepository parkingGarageRepository;
  private final ParkingFloorRepository parkingFloorRepository;

  public ParkingGarage createParkingGarage(String name) {
    return parkingGarageRepository.addParkingGarage(name);
  }

  public void addParkingFloorToGarage(
      String garageName, Map<String, List<ParkingSpot>> parkingFloorWithParkingSpots) {
    for (String floorName : parkingFloorWithParkingSpots.keySet()) {
      ParkingFloor parkingFloor = getOrCreateParkingFloor(garageName, floorName);
      parkingFloor.getParkingSpots().addAll(parkingFloorWithParkingSpots.get(floorName));
    }
  }

  private ParkingFloor getOrCreateParkingFloor(String garageName, String floorName) {
    return Optional.ofNullable(parkingGarageRepository.getParkingFloor(garageName, floorName))
        .orElse(parkingGarageRepository.addParkingFloor(garageName, floorName));
  }

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
                        "Parking floor: %s does not exists in garage: %s", floorName, garageName)));
  }
}
