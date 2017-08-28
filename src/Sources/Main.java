package Sources;

import Sources.Views.Menu.MenuCtrl;
import Util.Errors;
import Util.HibernateUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by Nirchenko Kostya for GoogleMapsDirectionApplication.
 * @since at 18.07.2017
 */
public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    Session session = HibernateUtil.getSessionFactory().openSession();
    Query query;

    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(
                    Main.class.getResourceAsStream("/logging.properties")
            );
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Exception: ", e);
            Errors.showGlobalException(e, false);
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("GoogleMapsDirectionApplication");
//        this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Views/Img/App.png")));
        session.beginTransaction();
//        query = session.createQuery("from")
        initRootLayout();
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            rootLayout = (BorderPane) loader.load(getClass().getResourceAsStream("Views/Menu/Menu.fxml"));

            primaryStage.setScene(new Scene(rootLayout));
            login();
            primaryStage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Exception: ", e);
        }
    }

    private void login() {
        primaryStage.getIcons().clear();
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Вход");
        dialog.setHeaderText("Пожалуйста, авторизируйтесь под\nвашей учетной записью");
        dialog.setGraphic(new ImageView(this.getClass().getResource("Views/Img/login.png").toString()));
        ButtonType loginButtonType = new ButtonType("Далее", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        TextField login = new TextField();
        login.setPromptText("Введите ваш логин");
        PasswordField password = new PasswordField();
        password.setPromptText("Введите ваш пароль");
        grid.add(new Label("Логин :"), 0, 0);
        grid.add(login, 1, 0);
        grid.add(new Label("Пароль :"), 0, 1);
        grid.add(password, 1, 1);
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);
        password.textProperty().addListener(((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        }));
        dialog.getDialogPane().setContent(grid);
        Platform.runLater(login::requestFocus);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(login.getText(), password.getText());
            }
            return null;
        });
        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(person -> {
            session.beginTransaction();

            try {
                FXMLLoader loader = new FXMLLoader();
                BorderPane pane = loader.load(getClass().getResourceAsStream("Views/Menu/Menu.fxml"));
                rootLayout.setTop(pane);
                MenuCtrl menuCtrl = loader.getController();
                menuCtrl.setMain(this);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Exception: ", e);
            }
        });
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Main getMain() {
        return this;
    }
}
