package mx.com.vialogika.logikpos.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import mx.com.vialogika.logikpos.core.DiscountType;
import mx.com.vialogika.logikpos.core.SellTab;
import mx.com.vialogika.logikpos.util.Dialogs;
import mx.com.vialogika.logikpos.util.DiscountDialog;

public class Logikpos {
    @FXML
    private Button addSellButtonTest;
    @FXML
    private TabPane sellContainer;
    @FXML
    private Button discountButton;

    public void initialize(){
        setListeners();
    }

    private void setListeners() {
        discountButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dialogs.discountDialog(new DiscountDialog.DiscountApplyCallback() {
                    @Override
                    public void onDiscount(String discount, DiscountType type) {

                    }
                });
            }
        });
        addSellButtonTest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sellContainer.getTabs().add(new SellTab());
            }
        });
    }


}
