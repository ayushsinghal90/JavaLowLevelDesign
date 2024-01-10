package org.lowLevelDesign.parkingLot.Entities.Vehicles;

import org.lowLevelDesign.parkingLot.Entities.Abstracts.Vehicle;

public class Handicap extends Vehicle {
  public Handicap(String licenseNumber) {
    super(licenseNumber, null, true);
  }
}
