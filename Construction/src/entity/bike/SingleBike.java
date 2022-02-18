package entity.bike;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SingleBike extends Bike{

    public SingleBike(ResultSet res) throws SQLException {
        super(res);
        this.costScale = 1.0F;
    }

    @Override
    public String getAdvancedInfo() {
        return "Không";
    }
}
