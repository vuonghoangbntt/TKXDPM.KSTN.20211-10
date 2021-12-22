package controller;

import entity.card.Card;
import utils.Configs;

import java.sql.SQLException;

public class CardLoginController extends BaseController{

    public boolean validateCardInfo(String cardCode, String password){
        return true;
    }

    public boolean login(String cardCode, String password) throws SQLException {
        Card card = Card.login(cardCode, password);
        if(card!=null){
            Configs.card = card;
            return true;
        }
        return false;
    }

    public boolean validateUsernameCard(String userName){
        if(userName.length() < 8) return false;
        if(userName.contains(" ")) return false;
        if(userName.contains("<")) return false;
        if(userName.contains("/")) return false;
        if(userName.contains("=")) return false;
        return true;
    }
    public boolean validatePasswordCard(String passWord){
        if(passWord.length() < 8) return false;
        if(passWord.contains(" ")) return false;
        if(passWord.contains("<")) return false;
        if(passWord.contains("/")) return false;
        if(passWord.contains("=")) return false;
        return true;
    }
}
