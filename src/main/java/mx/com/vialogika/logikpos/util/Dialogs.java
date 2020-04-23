package mx.com.vialogika.logikpos.util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Window;

import java.util.Optional;

public class Dialogs {

    public static String inputDialog(Alert.AlertType type,DialogInputFilter filter,String ... dialogContent){
        TextInputDialog dialog = new TextInputDialog(dialogContent[0]);
        dialog.setTitle(dialogContent[1]);
        dialog.setHeaderText(dialogContent[2]);
        dialog.setContentText(dialogContent[3]);
        if (filter != null){
            final TextField editor = dialog.getEditor();
            editor.textProperty().addListener((observable, oldValue, newValue) -> {
                switch (filter){
                    case DECIMAL:
                        if (!newValue.matches("\\d*(\\.\\d*)?")) {
                            editor.setText(oldValue);
                        }
                        break;
                    case INTEGER:
                        if (!newValue.matches("\\d*")) {
                            editor.setText(newValue.replaceAll("[^\\d]", ""));
                        }
                        break;
                }
            });
        }
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    public static void discountDialog(DiscountDialog.DiscountApplyCallback callback){
        DiscountDialog dialog = new DiscountDialog();
        dialog.setCallback(callback);
        dialog.showAndWait();
    }

    public enum DialogInputFilter{
        INTEGER,
        DECIMAL
    }
}
