package catalog;

import catalog.controller.login.LogInViewController;
import catalog.model.User;
import catalog.service.managers.LoaderManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static catalog.service.managers.LoaderManager.LOGIN_PAGE;

public class Main extends Application {

    private BorderPane loginLayout;
    private Stage primaryStage;
    private User user;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader loader = LoaderManager.getLoader(LOGIN_PAGE);
        loginLayout = (BorderPane) loader.load();

        Scene scene = new Scene(loginLayout);
        loginLayout.requestFocus();
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("Catalog");
        this.primaryStage.setResizable(false);
        this.primaryStage.show();

        LogInViewController controller = loader.getController();
        controller.setMain(this);
        controller.setPrimaryStage(this.primaryStage);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void showCatalog() {

    }
}
