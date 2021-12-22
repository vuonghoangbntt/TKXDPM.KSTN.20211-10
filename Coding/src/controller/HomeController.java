package controller;

import entity.dock.Dock;

import java.sql.SQLException;
import java.util.List;

public class HomeController extends BaseController{
    public List getAllDock() throws SQLException{
        return Dock.getAllDock();
    }
}
