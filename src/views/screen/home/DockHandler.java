package views.screen.home;

import controller.ViewDockController;
import entity.dock.Dock;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.FXMLScreenHandler;
import views.screen.dock.DockScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DockHandler extends FXMLScreenHandler {
    @FXML
    protected ImageView dockImage;

    @FXML
    protected Label dockName;

    @FXML
    protected Label dockAddress;

    @FXML
    protected Label dockArea;

    @FXML
    protected Label dockNumOfBike;

    @FXML
    protected Button dockView;

    private static Logger LOGGER = Utils.getLogger(DockHandler.class.getName());
    private Dock dock;
    private HomeScreenHandler home;

    public DockHandler(String screenPath, Dock dock, HomeScreenHandler home, Stage stage) throws SQLException, IOException{
        super(screenPath);
        this.dock = dock;
        this.home = home;
        setDockInfo();
    }

    private void setDockInfo() {
        // set the cover image of dock
        File file = new File(dock.getImageURL());
        Image image = new Image(file.toURI().toString());
        dockImage.setFitHeight(160);
        dockImage.setFitWidth(152);
        dockImage.setImage(image);

        dockName.setText(dock.getDockName());
        dockAddress.setText(dock.getAddress());
        dockArea.setText(Integer.toString(dock.getArea()));
        dockNumOfBike.setText(Integer.toString(dock.getBikeNumber()));
        //setImage(dockImage, dock.getImageURL());
    }


    public Dock getDock() {
        return dock;
    }

    public void setDock(Dock dock) {
        this.dock = dock;
    }

}
