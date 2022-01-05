package views.screen.rent;

import controller.PaymentController;
import controller.ReturnBikeController;
import entity.payment.RentTransaction;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.payment.PaymentScreenHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ReturnBikeHandler extends BaseScreenHandler {
    public static Logger LOGGER = Utils.getLogger(ReturnBikeHandler.class.getName());

    @FXML
    private TextField rentBikeID;

    @FXML
    private TextField rentCardCode;

    @FXML
    private TextField rentStartTime;

    @FXML
    private TextField rentEndTime;

    @FXML
    private TextField rentDeposit;

    @FXML
    private TextField rentFee;

    @FXML
    private Label totalAmount;

    @FXML
    private Button btnPayment;

    private RentTransaction rentTransaction;
    public ReturnBikeHandler(Stage stage, String screenPath, RentTransaction rentTransaction) throws IOException, SQLException {
        super(stage, screenPath);
        setMenuImage();
        setImage();

        this.rentTransaction = rentTransaction;
        setUp();
    }

    private void setUp() throws SQLException {
        rentTransaction.endRent();

        ReturnBikeController controller = new ReturnBikeController();
        LOGGER.info(rentTransaction.getRentTime());
        LOGGER.info(rentTransaction.getReturnTime());
        LOGGER.info(""+(controller == null));
        //LOGGER.info(" "+controller.calculateFee("20/12/2021 17:26","20/12/2021 23:35"));
        rentTransaction.setRentCost((int) controller.calculateFee(rentTransaction.getRentTime(), rentTransaction.getReturnTime()));

        rentBikeID.setText(Integer.toString(rentTransaction.getBikeCode()));
        rentBikeID.setEditable(false);

        rentDeposit.setText(Integer.toString(rentTransaction.getDepositeCost()));
        rentDeposit.setEditable(false);

        rentStartTime.setText(rentTransaction.getRentTime());
        rentStartTime.setEditable(false);

        rentEndTime.setText(rentTransaction.getReturnTime());
        rentEndTime.setEditable(false);

        rentCardCode.setText(rentTransaction.getRentCardCode());
        rentCardCode.setEditable(false);

        rentFee.setText(Integer.toString(rentTransaction.getRentCost()));
        rentFee.setEditable(false);

        totalAmount.setText(Integer.toString(-rentTransaction.getRentCost()+rentTransaction.getDepositeCost()));

        btnPayment.setOnMouseClicked(e -> {
            try{
                PaymentScreenHandler paymentScreen = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN, rentTransaction);
                paymentScreen.setBController(new PaymentController());
                paymentScreen.setScreenTitle("Payment");
                paymentScreen.setPreviousScreen(this);
                paymentScreen.setHomeScreenHandler(this.homeScreenHandler);
                paymentScreen.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }

}
