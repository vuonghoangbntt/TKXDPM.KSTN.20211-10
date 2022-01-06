package controller;

import entity.costcalculator.RentCostCalculator;
import entity.costcalculator.Strategy1;
import utils.Configs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ReturnBikeController extends BaseController{
    /**
     * cost calculator interface and strategy
     * */
    private RentCostCalculator calculator = new Strategy1();
    public long calculateFee(String startTime, String endTime){
        return calculator.calculateRentCost(startTime, endTime, Configs.bike);
    }
}
