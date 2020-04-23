package mx.com.vialogika.logikpos.util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import mx.com.vialogika.logikpos.core.DiscountType;

import java.io.IOException;
import java.util.Optional;

public class DiscountDialog {
    @FXML
    private Stage dialog;
    @FXML
    private Label signLabel;
    @FXML
    private TextField discountField;
    @FXML
    private ToggleButton toggleMode;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;

    private DiscountApplyCallback callback;
    private DiscountType discountType;

    public void initialize(){
        setListeners();
    }

    private void setListeners() {
        discountField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                discountField.setText(oldValue);
            }
        });
        toggleMode.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                discountType = DiscountType.PERCENT;
                toggleMode.setText("Porcentaje");
                signLabel.setText("%");
            } else {
                discountType = DiscountType.FIXED;
                toggleMode.setText("Cantidad fija");
                signLabel.setText("$");
            }
        });
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                callback.onDiscount(discountField.getText(),discountType);
                dialog.close();
            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
    }

    public void setCallback(DiscountApplyCallback callback) {
        this.callback = callback;
    }

    final void showAndWait(){
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("discount_dialog.fxml"));
        try {
            dialog = loader.load();
            loader.setController(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialog.showAndWait();
    }

    public interface DiscountApplyCallback{
        void onDiscount(String discount, DiscountType type);
    }
}
