package entity.bike;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SingleElectricBike extends Bike{
    private int remainBattery;
    private int maxTime;
    private String motor;

    public SingleElectricBike(ResultSet res) throws SQLException {
        super(res);
        this.remainBattery = res.getInt("remainBattery");
        this.maxTime = res.getInt("maxTime");
        this.motor = res.getString("motor");
        this.costScale = 1.5F;
    }

    @Override
    public String getAdvancedInfo() {
        return "Motor: "+this.motor+"\nMax Time Run: "+this.maxTime+"\nRemain Battery: "+this.remainBattery;
    }
}
