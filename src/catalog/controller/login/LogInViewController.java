package catalog.controller.login;

import catalog.Main;
import catalog.dao.impl.UserDaoImpl;
import catalog.service.ErrorMessage;
import catalog.service.managers.LoaderManager;
import catalog.service.sha1.PasswordStorage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import static catalog.service.managers.LoaderManager.SIGNUP_PAGE;

public class LogInViewController {

    @FXML
    private Button logInBtn;
    @FXML
    private Button guestBtn;
    @FXML
    private Button signUpBtn;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passField;

    /**
     * Link to main
     */
    private Main main;

    /**
     * Link to primaryStage
     */
    private Stage primaryStage;

    private int errorMesInd;

    public LogInViewController() {
    }

    @FXML
    private void initialize() {
        errorMesInd = -1;
        logInBtn.setOnAction(authorization());
        signUpBtn.setOnAction(showSignUp());
        guestBtn.setOnAction(event -> {
            ErrorMessage.deleteErrMes();
            primaryStage.sizeToScene();
        });
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private void logInErr(String error) {
        HBox passBox = (HBox) passField.getParent();
        VBox mainBox = (VBox) passBox.getParent();
        ErrorMessage.createErrMess(
                mainBox,
                mainBox.getChildren().indexOf(passBox) + 1,
                error);
        primaryStage.sizeToScene();
    }

    private EventHandler<ActionEvent> authorization() {
        return event -> {
            ErrorMessage.deleteErrMes();
            primaryStage.sizeToScene();

            String username = userField.getText();
            String password = passField.getText();

            if (!username.equals("") && !password.equals("")) {
                UserDaoImpl userDao = new UserDaoImpl();
                userDao.createConnection();

                try {
                    if (userDao.findUsername(username) &&
                            PasswordStorage.verifyPassword(
                                    password,
                                    userDao.getPassHash(username))
                            ) {
                        main.setUser(userDao.getByUsername(username));
                        logInErr("Success");
//                        primaryStage.close();
//                        main.showCatalog();
                    } else {
                        logInErr("The username or password is incorrect!");
                    }
                } catch (PasswordStorage.CannotPerformOperationException | PasswordStorage.InvalidHashException e) {
                    userDao.closePreparedStatement();
                    userDao.closeConnection();
                    e.printStackTrace();
                }

                userDao.closePreparedStatement();
                userDao.closeConnection();
            } else {
                logInErr("Entered data is incorrect");
            }
        };
    }

    private EventHandler<ActionEvent> showSignUp() {
        return event -> {
            try {
                FXMLLoader loader = LoaderManager.getLoader(SIGNUP_PAGE);
                BorderPane signUpLayout = (BorderPane) loader.load();
                Scene scene = new Scene(signUpLayout);
                signUpLayout.requestFocus();
                primaryStage.setScene(scene);

                SignUpViewController controller = loader.getController();
                controller.setPrimaryStage(primaryStage);
                controller.setMain(main);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
