package views.screen.rent;

import controller.ViewBikeController;
import entity.bike.Bike;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.dock.BikeDockScreenHandler;
import views.screen.popup.PopupScreen;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class RentHandler extends BaseScreenHandler{
    public static Logger LOGGER = Utils.getLogger(RentHandler.class.getName());
    @FXML
    private TextField barCode;

    @FXML
    private Button btnRent;

    @FXML
    private Button btnCancle;

    private final Bike bike;
    public RentHandler(Stage stage, String screenPath, Bike bike) throws IOException, SQLException {
        super(stage, screenPath);
        setMenuImage();
        setImage();

        this.bike = bike;
        bike.updateStatus();

        // set logo
        setImage();

        // set info
        setBarCode();
    }
    private void setBarCode() {

        barCode.setText(Integer.toString(bike.getId()));
        barCode.setEditable(true);

        btnRent.setOnMouseClicked(e -> {
            try {
                //bike.setStatus(-1);
                //bike.rentBike();
                //setBikeInfo();
                LOGGER.info("User click to see invoice payment");
                RentInfoHandler invoice = new RentInfoHandler(this.stage, Configs.INVOICE_PATH, bike);
                invoice.setBController(new ViewBikeController());
                invoice.setScreenTitle("Invoice");
                invoice.setPreviousScreen(this);
                invoice.setHomeScreenHandler(this.homeScreenHandler);
                invoice.show();

            } catch (Exception ex) {
                LOGGER.info("Rent bike failed!");
                ex.printStackTrace();
            }
        });
    }
}
