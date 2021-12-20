package entity.bike;

import entity.db.AIMSDB;
import utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Bike {
    private static final Logger LOGGER = Utils.getLogger(Bike.class.getName());

    protected Statement stm;
    protected int id;
    protected String licensePlate;
    protected String type;
    protected String motor;
    protected int status;
    protected int numOfPedal;
    protected int valueOfBike;
    protected int numOfSaddle;
    protected int maxTime;
    protected int remainBattery;
    protected int numOfSeat;
    protected int dockID;
    protected String imageURL;

    public Bike() throws SQLException{
        stm = AIMSDB.getConnection().createStatement();
    }

    public Bike(int id, String licensePlate, String type, String motor, int status, int numOfPedal, int valueOfBike,
                int numOfSaddle, int maxTime, int remainBattery, int numOfSeat, int DockID, String imageURL){
        this.id = id;
        this.licensePlate = licensePlate;
        this.type = type;
        this.dockID = DockID;
        this.imageURL = imageURL;
        this.motor = motor;
        this.status = status;
        this.numOfPedal = numOfPedal;
        this.valueOfBike = valueOfBike;
        this.numOfSaddle = numOfSaddle;
        this.maxTime = maxTime;
        this.remainBattery = remainBattery;
        this.numOfSeat = numOfSeat;
    }

    public static Bike getBikeById(int id) throws SQLException{
        String sql = "SELECT * FROM bike WHERE bikeCode = "+id;
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if(res.next()){
            return new Bike(res.getInt("bikeCode"), res.getString("licensePlate"), res.getString("type"), res.getString("motor"),
                    res.getInt("status"), res.getInt("numOfPedal"), res.getInt("valueOfBike"), res.getInt("numOfSaddle"),
                    res.getInt("maxTime"), res.getInt("remainBattery"), res.getInt("numOfSeat"), res.getInt("dockID"), res.getString("bikeImage"));
        }
        return null;
    }

    public static List getBikeByDockID(int id) throws SQLException{
        String sql = "SELECT * FROM bike WHERE status = 1 AND dockID = "+id;
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        ArrayList medium = new ArrayList<>();
        while(res.next()){
            Bike bike =  new Bike(res.getInt("bikeCode"), res.getString("licensePlate"), res.getString("type"), res.getString("motor"),
                    res.getInt("status"), res.getInt("numOfPedal"), res.getInt("valueOfBike"), res.getInt("numOfSaddle"),
                    res.getInt("maxTime"), res.getInt("remainBattery"), res.getInt("numOfSeat"), res.getInt("dockID"), res.getString("bikeImage"));
            medium.add(bike);
        }
        LOGGER.info(id+"-"+res);
        return medium;
    }
    public void updateStatus() throws SQLException {
        String sql = "SELECT * FROM bike WHERE bikeCode = "+id;
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);

        if(res.next()){
            this.setStatus(res.getInt("status"));
        }
    }
    public void rentBike() throws SQLException{
        String sql = "UPDATE bike SET status = -1 WHERE bikeCode = "+id;
        Statement stm = AIMSDB.getConnection().createStatement();
        stm.execute(sql);
        setStatus(-1);
    }
    public void returnBike() throws SQLException{
        String sql = "UPDATE bike SET status = 1,dockID = "+this.dockID+" WHERE bikeCode = "+this.id;
        Statement stm = AIMSDB.getConnection().createStatement();
        stm.execute(sql);
    }
    public String getBikeStatus(){
        if(status == 1){
            return "available";
        }else{
            return "unavailable";
        }
    }
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Statement getStm() {
        return stm;
    }

    public void setStm(Statement stm) {
        this.stm = stm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNumOfPedal() {
        return numOfPedal;
    }

    public void setNumOfPedal(int numOfPedal) {
        this.numOfPedal = numOfPedal;
    }

    public int getValueOfBike() {
        return valueOfBike;
    }

    public void setValueOfBike(int valueOfBike) {
        this.valueOfBike = valueOfBike;
    }

    public int getNumOfSaddle() {
        return numOfSaddle;
    }

    public void setNumOfSaddle(int numOfSaddle) {
        this.numOfSaddle = numOfSaddle;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public int getRemainBattery() {
        return remainBattery;
    }

    public void setRemainBattery(int remainBattery) {
        this.remainBattery = remainBattery;
    }

    public int getNumOfSeat() {
        return numOfSeat;
    }

    public void setNumOfSeat(int numOfSeat) {
        this.numOfSeat = numOfSeat;
    }

    public int getDockID() {
        return dockID;
    }

    public void setDockID(int dockID) {
        this.dockID = dockID;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

}
