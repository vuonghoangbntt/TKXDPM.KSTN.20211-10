package entity.dock;

import entity.bike.Bike;
import entity.db.AIMSDB;
import utils.Utils;

import javax.print.Doc;
import javax.print.attribute.standard.Media;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * This {@code Dock} class represent dock entity
 * in our ECO BIKE Software.
 *
 * @author nhom10
 *
 */
public class Dock {
    private static final Logger LOGGER = Utils.getLogger(Dock.class.getName());

    protected Statement stm;
    protected int dockId;
    protected String dockName;
    protected String address;
    protected String imageURL;
    protected int area;
    protected int bikeNumber;
    protected List<Bike> lstBike;

    public Dock() throws SQLException{
        stm = AIMSDB.getConnection().createStatement();
        lstBike = new ArrayList<>();
    }

    /**
     * thong tin khoi tao cua bai xe
     * @param id id cua bai xe
     * @param name ten bai xe
     * @param address dia chi bai xe
     * @param area dien tich bai xe
     * @param ImageURL hinh anh bai xe
     * @throws SQLException
     */
    public Dock(int id, String name, String address, int area, String ImageURL) throws SQLException{
        this.dockId = id;
        this.dockName = name;
        this.address = address;
        this.imageURL = ImageURL;
        this.area = area;
        this.lstBike = Bike.getBikeByDockID(id);
    }

    /**
     *
     * @return danh sach tat ca cac bai xe
     * @throws SQLException
     */
    public static List getAllDock() throws SQLException {
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery("select * from dock");
        ArrayList medium = new ArrayList<>();
        while(res.next()){
            Dock dock = new Dock(res.getInt("dockID"), res.getString("name"), res.getString("address"),
                    res.getInt("area"), res.getString("dockImage"));
            medium.add(dock);
        }
        return medium;
    }

    /**
     *
     * @return so luong xe trong bai
     */
    public int getTotalBikeAvailable(){
        return this.lstBike.size();
    }

    /**
     * phuong thuc them xe vao bai
     * @param bike doi tuong xe
     */
    public void addBike(Bike bike){
        lstBike.add(bike);
    }

    /**
     * phuong thuc xoa xe khoi bai
     * @param bike doi tuong xe
     */
    public void removeBike(Bike bike){
        lstBike.remove(bike);
    }

    /**
     *
     * @return danh sach xe trong bai
     */
    public List getListBike(){
        return lstBike;
    }

    public void setListBike(List lstBike){
        this.lstBike = lstBike;
    }
    public int getDockId() {
        return dockId;
    }

    public void setDockId(int dockId) {
        this.dockId = dockId;
    }

    public String getDockName() {
        return dockName;
    }

    public void setDockName(String dockName) {
        this.dockName = dockName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getBikeNumber() {
        return lstBike.size();
    }

    public void setBikeNumber(int bikeNumber) {
        this.bikeNumber = bikeNumber;
    }



}
