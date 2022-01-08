package views.screen.card;

import controller.CardLoginController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class CardLoginScreenHandler extends BaseScreenHandler {
    public static Logger LOGGER = Utils.getLogger(CardLoginController.class.getName());

    @FXML
    private TextField cardCode;

    @FXML
    private PasswordField cardPassword;

    @FXML
    private Button btnLogin;

    public CardLoginScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        setImage();
        setMenuImage();
        setupLogin();
    }

    private void setupLogin() {
        btnLogin.setOnMouseClicked(e -> {
            try{
                CardLoginController controller = (CardLoginController) this.getBController();
                if(controller.login(cardCode.getText(), cardPassword.getText())){
                    PopupScreen.success("Login successful!");
                    cardViewShow();
                }else{
                    PopupScreen.error("Login failed!");
                }
            } catch (IOException|SQLException ex) {
                LOGGER.info("Error when login!");
                ex.printStackTrace();
            }
        });
    }

}
