package org.lowLevelDesign.parkingLot.Entities.Parking;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.ParkingSpot;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.Vehicle;
import org.lowLevelDesign.parkingLot.Entities.Constants.ParkingTicketStatus;

@Getter
@Setter
@Builder
public class ParkingTicket {
  private final String ticketNumber;
  private final Vehicle vehicle;
  private final ParkingSpot parkingSpot;
  private final Long issuedAt;
  private Long vacatedAt;
  private double charges;
  private ParkingTicketStatus parkingTicketStatus;
}
