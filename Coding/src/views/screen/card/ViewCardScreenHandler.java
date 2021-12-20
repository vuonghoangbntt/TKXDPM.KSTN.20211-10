package views.screen.card;

import controller.BaseController;
import controller.ViewCardController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.util.logging.Logger;

public class ViewCardScreenHandler extends BaseScreenHandler {
    public static Logger LOGGER = Utils.getLogger(ViewCardScreenHandler.class.getName());

    @FXML
    private TextField cardCode;

    @FXML
    private TextField cardOwner;

    @FXML
    private TextField cardExpiredDate;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnViewTransaction;

    public ViewCardScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        setMenuImage();
        setImage();
        setScreenTitle("View Card");
        setButtonControl();
    }

    @Override
    public ViewCardController getBController() {
        return (ViewCardController) super.getBController();
    }

    private void setButtonControl() {
        btnLogout.setOnMouseClicked(e -> {
            try {
                getBController().Logout();
                PopupScreen.success("Logout Successful!");
                this.homeScreenHandler.show();
            } catch (IOException ex) {
                LOGGER.info("Log out error occur!");
                ex.printStackTrace();
            }
        });
        btnViewTransaction.setOnMouseClicked(e -> {
            try{
                PopupScreen.error("View Transaction not implemented yet!!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public void show() {
        setupInfo();
        super.show();
    }

    private void setupInfo() {
        cardCode.setText(Configs.card.getCardCode());
        cardCode.setEditable(false);
        cardOwner.setText(Configs.card.getOwner());
        cardOwner.setEditable(false);
        cardExpiredDate.setText(Configs.card.getDateExpired());
        cardExpiredDate.setEditable(false);
    }
}
