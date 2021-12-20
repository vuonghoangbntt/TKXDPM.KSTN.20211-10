package views.screen.payment;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ResultScreeenHandler extends BaseScreenHandler {

    @FXML
    private Label resultLabel;

    @FXML
    private ImageView resultImage;

    @FXML
    private Button btnConfirm;

    public ResultScreeenHandler(Stage stage, String screenPath, String result, String resultMessage) throws IOException {
        super(stage,screenPath);
        setMenuImage();
        setImage();

        resultLabel.setText(result+"\n"+resultMessage);
        File file1 = new File(Configs.IMAGE_PATH + "/" + Configs.PAYMENT_RESULT_IMAGE.get(result));
        javafx.scene.image.Image img1 = new Image(file1.toURI().toString());
        resultImage.setImage(img1);
    }
}