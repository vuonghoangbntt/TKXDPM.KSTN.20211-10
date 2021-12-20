package entity.card;

import entity.db.AIMSDB;
import utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class Card {
    public static Logger LOGGER = Utils.getLogger(Card.class.getName());

    protected String cardCode;

    protected String owner;

    protected String dateExpired;

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(String dateExpired) {
        this.dateExpired = dateExpired;
    }

    public Card(String cardCode, String owner, String dateExpired){
        this.cardCode = cardCode;
        this.owner = owner;
        this.dateExpired = dateExpired;
    }

    public static boolean checkCardInfo(String cardCode, String password) throws SQLException {
        String sql = "SELECT * FROM card";
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        while(res.next()){
            if(cardCode.equals(res.getString("cardCode")) && password.equals(res.getString("password"))){
                return true;
            }
        }
        return false;
    }

    public static Card login(String cardCode, String password) throws SQLException {
        if(true){
            String sql = "SELECT * FROM card";
            Statement stm = AIMSDB.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);

            while(res.next()){
                if(cardCode.equals(res.getString("cardCode"))){
                    return new Card(cardCode, res.getString("owner"), res.getString("dateExpired"));
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardCode='" + cardCode + '\'' +
                ", owner='" + owner + '\'' +
                ", dateExpired='" + dateExpired + '\'' +
                '}';
    }
}
