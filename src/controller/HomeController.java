package controller;

import entity.dock.Dock;

import java.sql.SQLException;
import java.util.List;

/**
 * This {@code HomeController} class control the flow of home screen
 * in our ECO BIKE Software.
 *
 * @author nhom10
 *
 */
public class HomeController extends BaseController{
    /**
     *
     * @return tra ve danh sach tat ca bai do xe
     * @throws SQLException
     */
    public List getAllDock() throws SQLException{
        return Dock.getAllDock();
    }
}
