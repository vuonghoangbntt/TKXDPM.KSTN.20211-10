package views.screen.bike;

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

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class BikeScreenHandler extends BaseScreenHandler {
    public static Logger LOGGER = Utils.getLogger(BikeScreenHandler.class.getName());
    @FXML
    private ImageView bikeImage;

    @FXML
    private TextField bikeID;

    @FXML
    private TextField bikeType;

    @FXML
    private TextField bikePedals;

    @FXML
    private  TextField bikeValue;

    @FXML
    private TextField bikeSaddle;

    @FXML
    private TextField bikeMaxTime;

    @FXML
    private TextField bikeSeats;

    @FXML
    private TextField bikeStatus;

    @FXML
    private TextField bikeBattery;

    @FXML
    private Button btnRentBike;

    private Bike bike;
    public BikeScreenHandler(Stage stage, String screenPath, Bike bike) throws IOException, SQLException {
        super(stage, screenPath);
        setMenuImage();
        setImage();

        this.bike = bike;
        bike.updateStatus();

        // set logo
        setImage();

        // set info
        setBikeInfo();
    }

    private void setBikeInfo() {
        File file = new File(bike.getImageURL());
        Image image = new Image(file.toURI().toString());
        bikeImage.setFitHeight(169);
        bikeImage.setFitWidth(186);
        bikeImage.setImage(image);

        if(bike.getRemainBattery()>=0) {
            bikeBattery.setText(bike.getRemainBattery()+"%");
        }else{
            bikeBattery.setText("No battery");
        }
        bikeBattery.setEditable(false);

        bikeID.setText(Integer.toString(bike.getId()));
        bikeID.setEditable(false);

        bikeSaddle.setText(Integer.toString(bike.getNumOfSaddle()));
        bikeSaddle.setEditable(false);

        bikeMaxTime.setText(bike.getMaxTime()+" mins");
        bikeMaxTime.setEditable(false);

        bikePedals.setText(bike.getNumOfPedal()+" pedals");
        bikePedals.setEditable(false);

        bikeSeats.setText(Integer.toString(bike.getNumOfSeat()));
        bikeSeats.setEditable(false);

        bikeType.setText(bike.getType());
        bikeType.setEditable(false);

        bikeValue.setText(bike.getValueOfBike()+" VND");
        bikeValue.setEditable(false);

        bikeStatus.setText(bike.getBikeStatus());
        bikeStatus.setEditable(false);

        btnRentBike.setOnMouseClicked(e -> {
            try{
                PopupScreen.error("Chức năng chưa được hỗ trợ!");
            }catch (Exception ex){
                LOGGER.info("Pop up failed!");
                ex.printStackTrace();
            }
        });
    }
}
