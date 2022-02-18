package entity.bike;

import entity.db.AIMSDB;
import utils.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class BikeHandler {
    private static final Logger LOGGER = Utils.getLogger(Bike.class.getName());
    /**
     *
     * @param id id cua xe
     * @return doi tuong xe voi id truyen vao
     * @throws SQLException
     */
    public static Bike getBikeById(int id){
        String sql = "SELECT * FROM bike WHERE bikeCode = "+id;
        ResultSet res = AIMSDB.query(sql);
        try {
            return handleBikeType(res);
        }catch(Exception e) {
            LOGGER.info("Get Bike By ID caused Error");
            e.printStackTrace();
        }
        return null;
    }

    public static Bike handleBikeType(ResultSet res) throws SQLException {
        String type = res.getString("type");
        if(type.equals("Xe dap don")){
            return new SingleBike(res);
        }else if(type.equals("Xe dap doi")){
            return new DoubleBike(res);
        }else if(type.equals("Xe dap dien")){
            return new SingleElectricBike(res);
        }else {
            return null;
        }
    }
    /**
     *
     * @param id id bai xe
     * @return danh sach xe trong bai xe
     * @throws SQLException
     */
    public static List getBikeByDockID(int id) throws SQLException{
        String sql = "SELECT * FROM bike WHERE status = 1 AND dockID = "+id;
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        ArrayList medium = new ArrayList<>();
        while(res.next()){
            Bike bike = handleBikeType(res);
            if(bike!=null) {
                medium.add(bike);
            }
        }
        LOGGER.info(id+"-"+res);
        return medium;
    }

}
