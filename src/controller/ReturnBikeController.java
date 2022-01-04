package controller;

import utils.Configs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
/**
 * This {@code ReturnBikeController} class control the flow of return bike process
 * in our ECO BIKE Software.
 *
 * @author nhom10
 *
 */
public class ReturnBikeController extends BaseController{
    /**
     *
     * @param startTime thoi gian thue xe
     * @param endTime thoi gian tra xe
     * @return so tien phai tra
     */
    public int calculateFee(String startTime, String endTime){
        // Custom date format
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(startTime);
            d2 = format.parse(endTime);
        }catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = d2.getTime() - d1.getTime();//as given
        long hours = TimeUnit.MILLISECONDS.toHours(diff);
        if(hours==0){
            return 10000;
        }
        if(Configs.bike.getRemainBattery()<0){
            return (int) hours*Configs.bike.getNumOfSeat()*10000;
        }else{
            return (int) hours*Configs.bike.getNumOfSeat()*20000;
        }
    }
}
