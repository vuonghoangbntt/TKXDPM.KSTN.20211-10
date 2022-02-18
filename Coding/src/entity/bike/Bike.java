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

    protected Statement stm;
    protected int id;
    protected String licensePlate;
    protected int status;
    protected int numOfPedal;
    protected int valueOfBike;
    protected int numOfSaddle;
    protected int numOfSeat;
    protected int dockID;
    protected String type;
    protected String name;
    protected String imageURL;

    public float getCostScale() {
        return costScale;
    }

    protected float costScale;

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
     * @param status trang thai xe
     * @param numOfPedal so ban dap cua xe
     * @param valueOfBike gia tri cua xe
     * @param numOfSaddle so yen xe
     * @param numOfSeat so cho ngoi cua xe
     * @param DockID id cua bai do xe
     * @param imageURL hinh anh xe
     */
    public Bike(int id, String licensePlate, String type, int status, int numOfPedal, int valueOfBike,
                int numOfSaddle, int numOfSeat, int DockID, String imageURL, String name){
        this.id = id;
        this.licensePlate = licensePlate;
        this.type = type;
        this.dockID = DockID;
        this.imageURL = imageURL;
        this.status = status;
        this.numOfPedal = numOfPedal;
        this.valueOfBike = valueOfBike;
        this.numOfSaddle = numOfSaddle;
        this.numOfSeat = numOfSeat;
        this.name = name;
    }

    public Bike(ResultSet res) throws SQLException {
        this.id = res.getInt("bikeCode");
        this.licensePlate = res.getString("licensePlate");
        this.type = res.getString("type");
        this.dockID = res.getInt("dockID");
        this.imageURL = res.getString("bikeImage");
        this.status = res.getInt("status");
        this.numOfPedal = res.getInt("numOfPedal");
        this.valueOfBike = res.getInt("valueOfBike");
        this.numOfSaddle = res.getInt("numOfSaddle");
        this.numOfSeat = res.getInt("numOfSeat");
        this.name = res.getString("name");
    }

    /**
     * cap nhat trang thai cac xe trong database
     * @throws SQLException
     */
    public void updateStatus() throws SQLException {
        this.status = BikeDAO.getBikeStatusByID(this.id);
    }

    /**
     * phuong thuc thue xe
     * @throws SQLException
     */
    public void rentBike() throws SQLException{
        BikeDAO.updateBIkeStatus(id,-1);
        setStatus(-1);
    }

    /**
     * phuong thuc tra xe
     * @throws SQLException
     */
    public void returnBike() throws SQLException{
        BikeDAO.updateBIkeStatus(id,1);
        setStatus(1);
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

    public String getAdvancedInfo(){
        return "Không";
    }
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
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
