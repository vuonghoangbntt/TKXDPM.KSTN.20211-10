package entity.payment;

import entity.bike.Bike;
import entity.db.AIMSDB;
import utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class RentTransaction {
    public static Logger LOGGER = Utils.getLogger(RentTransaction.class.getName());

    private int rentalCode;

    private String rentCardCode;

    private String rentTime;

    private int depositeCost;

    private String returnTime;

    private int bikeCode;

    private int rentCost;

    public RentTransaction() throws SQLException {
        setRentalCode();
    }

    public RentTransaction(int rentalCode, String rentCardCode, String rentTime, int depositeCost, String returnTime, int bikeCode, int rentCost){
        this.rentalCode = rentalCode;
        this.rentCardCode = rentCardCode;
        this.rentTime = rentTime;
        this.depositeCost = depositeCost;
        this.returnTime = returnTime;
        this.bikeCode = bikeCode;
        this.rentCost = rentCost;
    }
    public boolean startRent() throws SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        setRentTime(formatter.format(date));
        return createRentTransaction();
    }

    private boolean createRentTransaction() throws SQLException {
        String sql = String.format("INSERT INTO renttransaction (rentalCode, rentCardCode, rentTime, bikeCode, depositeCost) VALUES (%d, '%s','%s',%d,%d)",this.rentalCode,this.rentCardCode, this.rentTime, this.bikeCode, this.depositeCost);
        LOGGER.info(sql);
        Statement stm = AIMSDB.getConnection().createStatement();
        boolean status = stm.execute(sql);
        return status;
    }

    public boolean endRent() throws SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        setReturnTime(formatter.format(date));
        return true;
    }

    public boolean updateRentTransaction() throws SQLException {
        String sql = String.format("UPDATE renttransaction SET returnTime = '%s', rentCost = %d WHERE rentalCode = %d", returnTime, rentCost, rentalCode);
        LOGGER.info(sql);
        Statement stm = AIMSDB.getConnection().createStatement();
        boolean status = stm.execute(sql);
        return status;
    }

    public int getRentalCode() {
        return rentalCode;
    }

    public void setRentalCode() throws SQLException {
        String sql = "SELECT MAX(rentalCode) as Max FROM renttransaction";
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if(res.next()){
            this.rentalCode = res.getInt("Max")+1;
        }else{
            this.rentalCode = 1;
        }
    }

    public static RentTransaction getRentTransactionByCard(String cardCode) throws SQLException{
        String sql = "SELECT * FROM renttransaction WHERE returnTime IS NULL";
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        while(res.next()){
            if(res.getString("rentCardCode").equals(cardCode)){
                LOGGER.info("Get bike code: "+res.getInt("bikeCode"));
                return new RentTransaction(res.getInt("rentalCode"),res.getString("rentCardCode"), res.getString("rentTime"), res.getInt("depositeCost"), null, res.getInt("bikeCode"), -1);
            }
        }
        return null;
    }

    public String getRentTime() {
        return rentTime;
    }

    public void setRentTime(String rentTime) {
        this.rentTime = rentTime;
    }

    public int getDepositeCost() {
        return depositeCost;
    }

    public void setDepositeCost(int depositeCost) {
        this.depositeCost = depositeCost;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public int getBikeCode() {
        return bikeCode;
    }

    public void setBikeCode(int bikeCode) {
        this.bikeCode = bikeCode;
    }

    public int getRentCost() {
        return rentCost;
    }

    public void setRentCost(int rentCost) {
        this.rentCost = rentCost;
    }

    public String getRentCardCode() {
        return rentCardCode;
    }

    public void setRentCardCode(String rentCardCode) {
        this.rentCardCode = rentCardCode;
    }


}
