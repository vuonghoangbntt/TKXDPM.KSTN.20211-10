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
}
