package catalog.service;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ErrorMessage {
    private static VBox mainBox;
    private static int errMessInd;

    static {
        mainBox = null;
        errMessInd = -1;
    }

    private ErrorMessage() {

    }

    public static void createErrMess(VBox mainBox, int errMessInd, String errMessage) {
        if (ErrorMessage.errMessInd < 0 && ErrorMessage.mainBox == null) {
            Object object;
            ErrorMessage.mainBox = mainBox;
            ErrorMessage.errMessInd = errMessInd;
            HBox messBox = new HBox();
            messBox.setMinHeight(30);
            messBox.setAlignment(Pos.CENTER);
            Label messLabel = new Label(errMessage);
            messLabel.setFont(Font.font(16));
            messLabel.setTextFill(Color.DARKRED);
            messBox.getChildren().add(messLabel);
            mainBox.getChildren().add(errMessInd, messBox);
        }
    }

    public static void deleteErrMes() {
        if (errMessInd > -1 && ErrorMessage.mainBox != null) {
            ErrorMessage.mainBox.getChildren().remove(ErrorMessage.errMessInd);
            ErrorMessage.mainBox.autosize();
            ErrorMessage.errMessInd = -1;
            ErrorMessage.mainBox = null;
        }
    }

}
