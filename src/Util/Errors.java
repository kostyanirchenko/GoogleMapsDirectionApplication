package Util;

import Sources.Main;
import Util.Mail.TLS.Sender;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

/**
 * Created by Nirchenko Kostya for GoogleMapsDirectionApplication.
 * @since at 19.07.2017
 */
public class Errors {

    public static void showGlobalException(Exception e, boolean isDatabaseException) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Упс, что-то пошло не так");
        alert.setContentText("Пожалуйста, отправте отчет об ошибке разработчику.");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionText = sw.toString();
        Label label = new Label("Стек ошибки: ");
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);
        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);
        alert.getDialogPane().setExpandableContent(expContent);

        ButtonType sendMail = new ButtonType("Отправить", ButtonBar.ButtonData.APPLY);
        ButtonType cancel = new ButtonType("Выйти", ButtonBar.ButtonData.CANCEL_CLOSE);

        Stage errorStage = (Stage) alert.getDialogPane().getScene().getWindow();
        errorStage.getIcons().add(new Image(Main.class.getResourceAsStream("Views/Img/error.png")));
        alert.getButtonTypes().setAll(sendMail, cancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == sendMail) {
            Sender sender = new Sender();
            sender.send("Отчет об ошибке", textArea.getText());
        } else {
            System.exit(-1111);
        }
    }

    public static void showUserException() {

    }

    public static void showLoginException(String message) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Ошибка");
        error.setHeaderText("Упс, что-то пошло не так!");
        error.setContentText(message);
        Stage errorStage = (Stage) error.getDialogPane().getScene().getWindow();
        errorStage.getIcons().add(new Image(Main.class.getResourceAsStream("Sources/Views/Img/error.png")));
        errorStage.showAndWait();
    }

    public static void showInfoMessage(String message) {
        Alert information = new Alert(Alert.AlertType.INFORMATION);
        information.setTitle("Информация");
        information.setContentText(message);
        Stage informationStage = (Stage) information.getDialogPane().getScene().getWindow();
        informationStage.getIcons().add(new Image(Main.class.getResourceAsStream("Sources/Views/Img/confirm.png")));
        informationStage.showAndWait();
    }
}
