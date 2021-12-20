package controller;

import utils.Configs;

public class ViewCardController extends BaseController{

    public void Logout(){
        Configs.card = null;
    }
}
