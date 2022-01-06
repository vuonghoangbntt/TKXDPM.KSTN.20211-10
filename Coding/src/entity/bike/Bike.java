package entity.bike;

import entity.db.AIMSDB;
import utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * This {@code Bike} class represent bike entity
 * in our ECO BIKE Software.
 *
 * @author nhom10
 *
 */
public class Bike {
    private static final Logger LOGGER = Utils.getLogger(Bike.class.getName());
    public static final Map<String, Double> COST_SCALE = Map.of(
            "Xe dap don", 1.0,
            "Xe dap doi", 1.5,
            "Xe dap dien",1.5
    );

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
    protected String name;
    protected String imageURL;

    /**
     * phuong thuc ket noi den database
     * @throws SQLException
     */
    public Bike() throws SQLException{
        stm = AIMSDB.getConnection().createStatement();
    }

    /**
     * khoi tao entity Bike
     * @param id id cua xe
     * @param licensePlate bien so xe
     * @param type loai xe
     * @param motor kieu xe
     * @param status trang thai xe
     * @param numOfPedal so ban dap cua xe
     * @param valueOfBike gia tri cua xe
     * @param numOfSaddle so yen xe
     * @param maxTime thoi gian su dung toi da cua xe
     * @param remainBattery phan tram pin con lai (doi voi xe dien)
     * @param numOfSeat so cho ngoi cua xe
     * @param DockID id cua bai do xe
     * @param imageURL hinh anh xe
     */
    public Bike(int id, String licensePlate, String type, String motor, int status, int numOfPedal, int valueOfBike,
                int numOfSaddle, int maxTime, int remainBattery, int numOfSeat, int DockID, String imageURL, String name){
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
        this.name = name;
    }

    /**
     *
     * @param id id cua xe
     * @return doi tuong xe voi id truyen vao
     * @throws SQLException
     */
    public static Bike getBikeById(int id) throws SQLException{
        String sql = "SELECT * FROM bike WHERE bikeCode = "+id;
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if(res.next()){
            return new Bike(res.getInt("bikeCode"), res.getString("licensePlate"), res.getString("type"), res.getString("motor"),
                        res.getInt("status"), res.getInt("numOfPedal"), res.getInt("valueOfBike"), res.getInt("numOfSaddle"),
                        res.getInt("maxTime"), res.getInt("remainBattery"), res.getInt("numOfSeat"), res.getInt("dockID"), res.getString("bikeImage"), res.getString("name"));
        }
        return null;
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
            
            Bike bike =  new Bike(res.getInt("bikeCode"), res.getString("licensePlate"), res.getString("type"), res.getString("motor"),
                    res.getInt("status"), res.getInt("numOfPedal"), res.getInt("valueOfBike"), res.getInt("numOfSaddle"),
                    res.getInt("maxTime"), res.getInt("remainBattery"), res.getInt("numOfSeat"), res.getInt("dockID"), res.getString("bikeImage"), res.getString("name"));
            medium.add(bike);
        }
        LOGGER.info(id+"-"+res);
        return medium;
    }

    /**
     * cap nhat trang thai cac xe trong database
     * @throws SQLException
     */
    public void updateStatus() throws SQLException {
        String sql = "SELECT * FROM bike WHERE bikeCode = "+id;
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);

        if(res.next()){
            this.setStatus(res.getInt("status"));
        }
    }

    /**
     * phuong thuc thue xe
     * @throws SQLException
     */
    public void rentBike() throws SQLException{
        String sql = "UPDATE bike SET status = -1 WHERE bikeCode = "+id;
        Statement stm = AIMSDB.getConnection().createStatement();
        stm.execute(sql);
        setStatus(-1);
    }

    /**
     * phuong thuc tra xe
     * @throws SQLException
     */
    public void returnBike() throws SQLException{
        String sql = "UPDATE bike SET status = 1,dockID = "+this.dockID+" WHERE bikeCode = "+this.id;
        Statement stm = AIMSDB.getConnection().createStatement();
        stm.execute(sql);
    }

    /**
     * phuong thuc lay trang thai cua xe
     * @return
     */
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
