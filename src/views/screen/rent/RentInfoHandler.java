package views.screen.rent;

import controller.PaymentController;
import controller.ViewBikeController;
import entity.bike.Bike;
import entity.payment.RentTransaction;
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
import views.screen.payment.PaymentScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class RentInfoHandler extends BaseScreenHandler {
    public static Logger LOGGER = Utils.getLogger(RentInfoHandler.class.getName());
    @FXML
    private TextField barCode;

    @FXML
    private TextField type;

    @FXML
    private TextField saddle;

    @FXML
    private  TextField pedal;

    @FXML
    private TextField dock;

    @FXML
    private TextField licensePlates;

    @FXML
    private TextField value;

    @FXML
    private TextField deposit;

    @FXML
    private Button btnPayment;

    @FXML
    private Button btnCancel;

    private final Bike bike;
    public RentInfoHandler(Stage stage, String screenPath, Bike bike) throws IOException, SQLException {
        super(stage, screenPath);
        setMenuImage();
        setImage();

        this.bike = bike;
        bike.updateStatus();

        // set logo
        setImage();

        // set info
        setInvoiceInfo();
    }

    private void setInvoiceInfo() {
        barCode.setText(Integer.toString(bike.getId()));
        barCode.setEditable(false);

        saddle.setText(Integer.toString(bike.getNumOfSaddle()));
        saddle.setEditable(false);

        dock.setText(Integer.toString(bike.getDockID()));
        dock.setEditable(false);

        pedal.setText(bike.getNumOfPedal()+" pedals");
        pedal.setEditable(false);

        licensePlates.setText(bike.getLicensePlate());
        licensePlates.setEditable(false);

        type.setText(bike.getType());
        type.setEditable(false);

        value.setText(bike.getValueOfBike()+" VND");
        value.setEditable(false);

        deposit.setText(bike.getValueOfBike()/10 +" VND");
        deposit.setEditable(false);

        btnPayment.setOnMouseClicked(e -> {
            try{
                // Thanh toán
                RentTransaction rentTransaction = new RentTransaction();
                rentTransaction.setBikeCode(bike.getId());
                rentTransaction.setDepositeCost(bike.getValueOfBike()/10);
                rentTransaction.setRentCardCode(Configs.card.getCardCode());

                PaymentScreenHandler paymentScreen = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN, rentTransaction);
                paymentScreen.setBController(new PaymentController());
                paymentScreen.setScreenTitle("Payment");
                paymentScreen.setPreviousScreen(this);
                paymentScreen.setHomeScreenHandler(this.homeScreenHandler);
                paymentScreen.show();
                //PopupScreen.error("Chức năng chưa được hỗ trợ!");

            }catch (Exception ex){
                LOGGER.info("Payment failed!");
                ex.printStackTrace();
            }
        });
    }

    public Bike getBike() {
        return bike;
    }
}
