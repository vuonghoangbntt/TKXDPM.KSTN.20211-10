package views.screen.payment;

import controller.PaymentController;
import entity.bike.Bike;
import entity.payment.RentTransaction;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;
import views.screen.rent.RentInfoHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;

public class PaymentScreenHandler extends BaseScreenHandler {
    public static Logger LOGGER = Utils.getLogger(PaymentScreenHandler.class.getName());

    @FXML
    private TextField cardCode;

    @FXML
    private TextField cardHolderName;

    @FXML
    private TextField cardExpirationDate;

    @FXML
    private TextField cardSecurityCode;

    @FXML
    private TextField amount;

    @FXML
    private Button btnConfirm;

    private final RentTransaction rentTransaction;

    public PaymentScreenHandler(Stage stage, String screenPath, RentTransaction rentTransaction) throws IOException {
        super(stage, screenPath);

        setMenuImage();
        setImage();

        this.rentTransaction = rentTransaction;
        setUp();
    }

    private void setUp() {
        //LOGGER.info("Deposite cost: "+rentTransaction.getDepositeCost());
        amount.setText(rentTransaction.getDepositeCost() +" VND");
        amount.setEditable(false);

        btnConfirm.setOnMouseClicked(e -> {
            try{
                confirmToPayOrder();
                //this.homeScreenHandler.show();
            } catch (Exception ex) {
                //PopupScreen.error("Rent fail!");
                LOGGER.info("Rent fail!!!");
                ex.printStackTrace();
            }
        });
    }

    void confirmToPayOrder() throws IOException, SQLException {
        String contents = "Giao dịch trừ tiền cọc";
        PaymentController ctrl = (PaymentController) getBController();
        Map<String, String> response = ctrl.payOrder(rentTransaction.getDepositeCost(), contents, "pay", cardCode.getText(), cardHolderName.getText(),
                cardExpirationDate.getText(), cardSecurityCode.getText());
        Bike bike = ((RentInfoHandler) this.getPreviousScreen()).getBike();
        bike.updateStatus();
        if(response.get("RESULT").equals("PAYMENT SUCCESSFUL!") && bike.getStatus()>0){
            LOGGER.info("RENT SUCCESSFUL");
            rentTransaction.startRent();
            bike.rentBike();
        }else if(bike.getStatus()<0){
            response.put("RESULT", "PAYMENT FAILED!");
            response.put("MESSAGE", "BIKE NOT AVAILABLE NOW!");
        }
        LOGGER.info(" "+response);
        ResultScreeenHandler resultScreen = new ResultScreeenHandler(this.stage, Configs.RESULT_SCREEN_PATH, response.get("RESULT"), response.get("MESSAGE") );
        resultScreen.setPreviousScreen(this);
        resultScreen.setHomeScreenHandler(homeScreenHandler);
        resultScreen.setScreenTitle("Result Screen");
        resultScreen.show();
    }
}
