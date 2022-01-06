package controller;

import entity.card.Card;
import utils.Configs;

import java.sql.SQLException;

/**
 * This {@code CardLoginController} class control the flow of the login card process
 * in our ECO BIKE Software.
 *
 * @author nhom10
 *
 */
public class CardLoginController extends BaseController{

    public boolean validateCardInfo(String cardCode, String password){
        return true;
    }

    /**
     *
     * @param cardCode so the cua card
     * @param password mat khau cua card
     * @return login thanh cong hay that bai
     * @throws SQLException
     */
    public boolean login(String cardCode, String password) throws SQLException {
        Card card = Card.login(cardCode, password);
        if(card!=null){
            Configs.card = card;
            return true;
        }
        return false;
    }

    /**
     *
     * @param userName ten dang nhap cua card
     * @return validate cua ten dang nhap
     */
    public boolean validateUsernameCard(String userName){
        if(userName.length() < 8) return false;
        if(userName.contains(" ")) return false;
        if(userName.contains("<")) return false;
        if(userName.contains("/")) return false;
        if(userName.contains("=")) return false;
        return true;
    }

    /**
     *
     * @param passWord mat khau dang nhap cua card
     * @return validate cua mat khau  
     */
    public boolean validatePasswordCard(String passWord){
        if(passWord.length() < 8) return false;
        if(passWord.contains(" ")) return false;
        if(passWord.contains("<")) return false;
        if(passWord.contains("/")) return false;
        if(passWord.contains("=")) return false;
        return true;
    }
}
