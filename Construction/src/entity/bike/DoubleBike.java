package entity.bike;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoubleBike extends Bike{

    public DoubleBike(ResultSet res) throws SQLException {
        super(res);
        this.costScale = 1.5F;
    }

    @Override
    public String getAdvancedInfo() {
        return "Không";
    }
}
