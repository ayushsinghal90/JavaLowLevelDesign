package org.lowLevelDesign.parkingLot.Services;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.ParkingSpot;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.Vehicle;
import org.lowLevelDesign.parkingLot.Entities.Parking.ParkingTicket;
import org.lowLevelDesign.parkingLot.repository.ParkingTicketRepository;
import org.lowLevelDesign.parkingLot.repository.VehicleRepository;

@AllArgsConstructor
public class TicketService {
  static final Logger LOG = LogManager.getLogger(ParkingService.class);

  private final VehicleRepository vehicleRepository;
  private final ParkingTicketRepository parkingTicketRepository;
  private final ParkingCatalogService parkingCatalogService;

  public ParkingTicket getOrCreateParkingTicket(
      String garageName, String floorName, Vehicle vehicle) {
    return Optional.ofNullable(
            parkingTicketRepository.getTicketByLicenseNumber(vehicle.getLicenseNumber()))
        .orElseGet(() -> createParkingTicket(garageName, floorName, vehicle));
  }

  private ParkingTicket createParkingTicket(String garageName, String floorName, Vehicle vehicle) {
    ParkingSpot parkingSpot =
        parkingCatalogService.getParkingSpotForVehicle(garageName, floorName, vehicle);
    ParkingTicket parkingTicket = parkingTicketRepository.assignParkingTicket(parkingSpot, vehicle);
    LOG.info(
        String.format(
            "Vehicle: %s parked at: %s with ticket: %s",
            vehicle.getLicenseNumber(), parkingSpot.getNumber(), parkingTicket.getTicketNumber()));
    return parkingTicket;
  }

  public void completeParkingTicket(String vehicleLicenseNumber) {
    ParkingTicket parkingTicket =
        parkingTicketRepository.getTicketByLicenseNumber(vehicleLicenseNumber);
    parkingTicketRepository.completeParkingTicket(parkingTicket.getTicketNumber());
    parkingTicket.getParkingSpot().setOccupied(false);
  }
}
