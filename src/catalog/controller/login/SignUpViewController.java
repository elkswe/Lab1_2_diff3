package catalog.controller.login;

import catalog.Main;
import catalog.dao.impl.UserDaoImpl;
import catalog.model.User;
import catalog.service.ErrorMessage;
import catalog.service.managers.LoaderManager;
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

import static catalog.service.managers.LoaderManager.LOGIN_PAGE;
import static catalog.service.sha1.PasswordStorage.CannotPerformOperationException;
import static catalog.service.sha1.PasswordStorage.createHash;

public class SignUpViewController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passField;
    @FXML
    private PasswordField confPassField;
    @FXML
    private Button signUpBtn;
    @FXML
    private Button backBtn;

    private Stage primaryStage;

    private Main main;

    @FXML
    private void initialize() {
        backBtn.setOnAction(event -> backBtnHandler());
        signUpBtn.setOnAction(signUpHandler());
    }

    private EventHandler<ActionEvent> signUpHandler() {
        return event -> {
            ErrorMessage.deleteErrMes();
            primaryStage.sizeToScene();
            String username = usernameField.getText();
            String password = passField.getText();
            String confPass = confPassField.getText();

            if (!username.equals("") &&
                    !password.equals("") &&
                    !confPass.equals("")) {
                if (password.equals(confPass)) {
                    UserDaoImpl userDao = new UserDaoImpl();
                    userDao.createConnection();

                    if (!userDao.findUsername(username)) {
                        try {
                            userDao.create(new User(
                                    "user",
                                    username,
                                    createHash(password)
                            ));
                            backBtnHandler();
                        } catch (CannotPerformOperationException e) {
                            userDao.closePreparedStatement();
                            userDao.closeConnection();
                            e.printStackTrace();
                        }
                    } else {
                        signUpErr("A user with this name already exists");
                    }

                    userDao.closePreparedStatement();
                    userDao.closeConnection();
                } else {
                    signUpErr("The passwords you entered do not match");
                    passField.clear();
                    confPassField.clear();
                }
            } else {
                signUpErr("Entered data is incorrect");
            }
        };
    }

    private void signUpErr(String error) {
        HBox confPassBox = (HBox) confPassField.getParent();
        VBox mainBox = (VBox) confPassBox.getParent();
        ErrorMessage.createErrMess(
                mainBox,
                mainBox.getChildren().indexOf(confPassBox) + 1,
                error);
        primaryStage.sizeToScene();
    }

    private void backBtnHandler() {
        try {
            FXMLLoader loader = LoaderManager.getLoader(LOGIN_PAGE);
            BorderPane loginLayout = (BorderPane) loader.load();
            Scene scene = new Scene(loginLayout);
            loginLayout.requestFocus();
            primaryStage.setScene(scene);

            LogInViewController controller = loader.getController();
            controller.setMain(main);
            controller.setPrimaryStage(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
