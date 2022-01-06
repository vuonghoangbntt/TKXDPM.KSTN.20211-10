package entity.costcalculator;

import entity.bike.Bike;

public interface RentCostCalculator {
    long calculateRentCost(String startTime, String endTime, Bike bike);
}
