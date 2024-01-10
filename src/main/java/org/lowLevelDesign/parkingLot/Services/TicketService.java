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

/**
 * Service class for managing parking tickets.
 *
 * @author ayushsinghal90
 */
@AllArgsConstructor
public class TicketService {

  // Logger for logging service-related events
  private static final Logger LOG = LogManager.getLogger(TicketService.class);

  // Repositories and services for handling vehicles, parking tickets, and parking catalog
  private final VehicleRepository vehicleRepository;
  private final ParkingTicketRepository parkingTicketRepository;
  private final ParkingCatalogService parkingCatalogService;

  /**
   * Gets or creates a parking ticket for the specified vehicle in the given garage and floor.
   *
   * @param garageName The name of the parking garage.
   * @param floorName The name of the parking floor.
   * @param vehicle The vehicle for which to get or create a parking ticket.
   * @return The parking ticket associated with the vehicle.
   */
  public ParkingTicket getOrCreateParkingTicket(
      String garageName, String floorName, Vehicle vehicle) {
    return Optional.ofNullable(
            parkingTicketRepository.getTicketByLicenseNumber(vehicle.getLicenseNumber()))
        .orElseGet(() -> createParkingTicket(garageName, floorName, vehicle));
  }

  /**
   * Creates a new parking ticket for the specified vehicle in the given garage and floor.
   *
   * @param garageName The name of the parking garage.
   * @param floorName The name of the parking floor.
   * @param vehicle The vehicle for which to create a parking ticket.
   * @return The newly created parking ticket.
   */
  private ParkingTicket createParkingTicket(String garageName, String floorName, Vehicle vehicle) {
    // Get a parking spot for the vehicle from the parking catalog
    ParkingSpot parkingSpot =
        parkingCatalogService.getParkingSpotForVehicle(garageName, floorName, vehicle);

    // Assign a parking ticket for the vehicle and parking spot
    ParkingTicket parkingTicket = parkingTicketRepository.assignParkingTicket(parkingSpot, vehicle);

    // Log parking information
    LOG.info(
        String.format(
            "Vehicle: %s parked at: %s with ticket: %s",
            vehicle.getLicenseNumber(), parkingSpot.getNumber(), parkingTicket.getTicketNumber()));

    return parkingTicket;
  }

  /**
   * Completes the parking process for a vehicle with the specified license number.
   *
   * @param vehicleLicenseNumber The license number of the parked vehicle.
   */
  public void completeParkingTicket(String vehicleLicenseNumber) {
    // Get the parking ticket associated with the specified vehicle license number
    ParkingTicket parkingTicket =
        parkingTicketRepository.getTicketByLicenseNumber(vehicleLicenseNumber);

    // Complete the parking ticket and mark the associated parking spot as unoccupied
    parkingTicketRepository.completeParkingTicket(parkingTicket.getTicketNumber());
    parkingTicket.getParkingSpot().setOccupied(false);
  }
}
