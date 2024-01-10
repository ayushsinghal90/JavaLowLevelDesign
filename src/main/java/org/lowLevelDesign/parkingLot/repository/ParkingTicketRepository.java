package org.lowLevelDesign.parkingLot.repository;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.ParkingSpot;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.Vehicle;
import org.lowLevelDesign.parkingLot.Entities.Constants.ParkingTicketStatus;
import org.lowLevelDesign.parkingLot.Entities.Parking.ParkingTicket;

/**
 * Repository class for managing parking tickets and associated mappings.
 *
 * @author ayushsinghal90
 */
public class ParkingTicketRepository {
  // Map to store parking tickets by ticket number
  private static final Map<String, ParkingTicket> parkingTicketMap = new HashMap<>();
  // Map to store vehicle-to-ticket mappings
  private static final Map<String, String> vehicleTicketMap = new HashMap<>();
  // Map to store parking spot-to-ticket mappings
  private static final Map<String, String> parkingSpotTicketMap = new HashMap<>();

  /**
   * Completes the parking ticket with the specified ticket number.
   *
   * @param ticketNumber The ticket number of the parking ticket to complete.
   * @throws RuntimeException if the parking ticket is not found.
   */
  public void completeParkingTicket(String ticketNumber) {
    Optional.of(parkingTicketMap.get(ticketNumber))
        .ifPresentOrElse(
            this::completeParking,
            () -> {
              throw new RuntimeException("Parking ticket not found: " + ticketNumber);
            });
  }

  /**
   * Assigns a new parking ticket for the given parking spot and vehicle.
   *
   * @param parkingSpot The parking spot for which the ticket is assigned.
   * @param vehicle The vehicle for which the ticket is assigned.
   * @return The newly assigned parking ticket.
   */
  public ParkingTicket assignParkingTicket(ParkingSpot parkingSpot, Vehicle vehicle) {
    ParkingTicket parkingTicket = createNewTicket(parkingSpot, vehicle);
    parkingTicketMap.put(parkingTicket.getTicketNumber(), parkingTicket);
    vehicleTicketMap.put(vehicle.getLicenseNumber(), parkingTicket.getTicketNumber());
    parkingSpotTicketMap.put(parkingSpot.getNumber(), parkingTicket.getTicketNumber());

    parkingSpot.setOccupied(true);
    return parkingTicket;
  }

  /**
   * Retrieves the parking ticket with the specified ticket number.
   *
   * @param ticketNumber The ticket number to retrieve.
   * @return The parking ticket with the specified ticket number, or null if not found.
   */
  public ParkingTicket getParkingTicket(String ticketNumber) {
    return parkingTicketMap.get(ticketNumber);
  }

  /**
   * Retrieves the parking ticket associated with the given license number.
   *
   * @param licenseNumber The license number of the vehicle.
   * @return The parking ticket associated with the license number, or null if not found.
   */
  public ParkingTicket getTicketByLicenseNumber(String licenseNumber) {
    return Optional.ofNullable(vehicleTicketMap.get(licenseNumber))
        .map(parkingTicketMap::get)
        .orElse(null);
  }

  /**
   * Retrieves the parking ticket associated with the given parking spot number.
   *
   * @param spotNumber The number of the parking spot.
   * @return The parking ticket associated with the parking spot, or null if not found.
   */
  public ParkingTicket getTicketByParkingSpot(String spotNumber) {
    return Optional.ofNullable(parkingSpotTicketMap.get(spotNumber))
        .map(parkingTicketMap::get)
        .orElse(null);
  }

  /**
   * Creates a new parking ticket based on the provided parking spot and vehicle.
   *
   * @param parkingSpot The parking spot for which the ticket is created.
   * @param vehicle The vehicle for which the ticket is created.
   * @return A new parking ticket instance.
   */
  private ParkingTicket createNewTicket(ParkingSpot parkingSpot, Vehicle vehicle) {
    return ParkingTicket.builder()
        .parkingSpot(parkingSpot)
        .parkingTicketStatus(ParkingTicketStatus.ACTIVE)
        .vehicle(vehicle)
        .ticketNumber(UUID.randomUUID().toString())
        .issuedAt(Instant.now().getEpochSecond())
        .build();
  }

  /**
   * Calculates the parking charges for the given parking ticket.
   *
   * @param parkingTicket The parking ticket for which charges are calculated.
   * @return The parking charges based on the time spent in hours.
   */
  private double getCharges(ParkingTicket parkingTicket) {
    long timeSpentInHours = (Instant.now().getEpochSecond() - parkingTicket.getIssuedAt()) / 3600;
    return timeSpentInHours * 10;
  }

  /**
   * Completes the parking process for the given parking ticket.
   *
   * @param parkingTicket The parking ticket to complete.
   */
  private void completeParking(ParkingTicket parkingTicket) {
    parkingTicket.setCharges(getCharges(parkingTicket));
    parkingTicket.setVacatedAt(Instant.now().getEpochSecond());
    parkingTicket.setParkingTicketStatus(ParkingTicketStatus.PAID);

    vehicleTicketMap.remove(parkingTicket.getVehicle().getLicenseNumber());
    parkingSpotTicketMap.remove(parkingTicket.getParkingSpot().getNumber());
  }
}
