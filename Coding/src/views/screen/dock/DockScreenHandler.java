package views.screen.dock;

import controller.ViewBikeController;
import entity.bike.Bike;
import entity.dock.Dock;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.bike.BikeScreenHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DockScreenHandler extends BaseScreenHandler{
    public static Logger LOGGER = Utils.getLogger(DockScreenHandler.class.getName());

    @FXML
    private Label dockName;

    @FXML
    private Label dockAddress;

    @FXML
    private Label dockNumOfBike;

    @FXML
    private Label dockArea;

    @FXML
    private VBox vboxBike;

    @FXML
    private ImageView dockImage;

    private final Dock dock;
    private final List dockItems;

    public DockScreenHandler(Stage stage, String screenPath, Dock dock) throws IOException {
        super(stage,screenPath);

        setImage();

        this.dock = dock;
        this.dockItems = new ArrayList<>();
        // set image
        setImage();

        try {
            for (Object object : dock.getListBike()) {
                Bike bike = (Bike) object;
                BikeDockScreenHandler b = new BikeDockScreenHandler(Configs.DOCK_BIKE_PATH, bike);
                LOGGER.info("User click to view bike "+bike.getId());
                b.bikeView.setOnMouseClicked(e -> {
                    try {
                        BikeScreenHandler b1 = new BikeScreenHandler(this.stage, Configs.BIKE_SCREEN_PATH, bike);
                        b1.setHomeScreenHandler(this.homeScreenHandler);
                        b1.setBController(new ViewBikeController());
                        b1.setScreenTitle("View Bike");
                        b1.setPreviousScreen(this);
                        b1.show();
                    }catch (Exception ex){
                        LOGGER.info("Error occur when setup bike Screen");
                        ex.printStackTrace();
                    }
                });
                this.dockItems.add(b);
                vboxBike.getChildren().add(b.getContent());
            }
            setDockInfo();
        }catch (Exception e){
            LOGGER.info("Errors occured when get dock items");
            e.printStackTrace();
            throw new IOException();
        }
        setMenuImage();
    }

    private void setDockInfo() {
        // set the cover image of dock
        File file = new File(dock.getImageURL());
        Image image = new Image(file.toURI().toString());
        dockImage.setFitHeight(160);
        dockImage.setFitWidth(142);
        dockImage.setImage(image);

        dockName.setText(dock.getDockName());
        dockAddress.setText(dock.getAddress());
        dockArea.setText(Integer.toString(dock.getArea()));
        dockNumOfBike.setText(Integer.toString(dock.getBikeNumber()));
    }

}
