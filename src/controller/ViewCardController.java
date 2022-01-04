package controller;

import utils.Configs;
/**
 * This {@code ViewCardController} class control the flow of the view card process
 * in our ECO BIKE Software.
 *
 * @author nhom10
 *
 */
public class ViewCardController extends BaseController{
    /**
     * phuong thuc dang xuat khoi card
     */
    public void Logout(){
        Configs.card = null;
    }
}
