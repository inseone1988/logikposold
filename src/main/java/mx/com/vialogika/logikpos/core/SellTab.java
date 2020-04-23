package mx.com.vialogika.logikpos.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import mx.com.vialogika.logikpos.ui.SellView;

import java.io.IOException;

public class SellTab extends Tab {
    private Sell sell;

    public SellTab() {
        this.sell = new Sell();
        addContent();
    }

    private void addContent() {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("sell_view.fxml"));
        try {
            VBox content = loader.load();
            SellView sellView = loader.getController();
            sellView.setSell(sell);
            setContent(content);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
