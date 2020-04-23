package mx.com.vialogika.logikpos.ui;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import mx.com.vialogika.logikpos.core.Item;
import mx.com.vialogika.logikpos.core.Sell;

public class SellView implements EventHandler<KeyEvent> {
    @FXML
    private Label grandTotalView;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private TableView<Item> itemTableView;
    @FXML
    private TableColumn<Item, String> descriptionTableColumn;
    @FXML
    private TableColumn<Item, Number> quantityTableColumn;
    @FXML
    private TableColumn<Item, String> priceTableColumn;
    @FXML
    private TableColumn<Item, String> discountTableColumn;
    @FXML
    private TableColumn<Item, String> totalTableColumn;

    private Sell sell;
    private Item selectedItem;
    private SelectionModel<Item> itemSelectionModel;

    public void initialize() {

    }

    private void setupTableView() {
        itemSelectionModel = itemTableView.getSelectionModel();
        itemTableView.setPlaceholder(new Label("Añada items a la venta"));
        descriptionTableColumn.setCellValueFactory(param -> param.getValue().productDescriptionProperty());
        descriptionTableColumn.prefWidthProperty().bind(itemTableView.widthProperty().multiply(0.6));

        quantityTableColumn.setCellValueFactory(param -> param.getValue().quantityProperty());
        quantityTableColumn.prefWidthProperty().bind(itemTableView.widthProperty().multiply(0.1));

        priceTableColumn.setCellValueFactory(param -> param.getValue().priceProperty());
        priceTableColumn.prefWidthProperty().bind(itemTableView.widthProperty().multiply(0.1));

        discountTableColumn.setCellValueFactory(param -> param.getValue().discountProperty());
        discountTableColumn.prefWidthProperty().bind(itemTableView.widthProperty().multiply(0.1));

        totalTableColumn.setCellValueFactory(param -> param.getValue().totalProperty());
        totalTableColumn.prefWidthProperty().bind(itemTableView.widthProperty().multiply(0.1));
        itemTableView.setOnKeyPressed(this);
        itemTableView.setItems(FXCollections.observableList(sell.getItemList()));
    }

    public void setSell(Sell sell) {
        this.sell = sell;
        setupTableView();
        grandTotalView.textProperty().bind(sell.grandTotalProperty());
    }

    @Override
    public void handle(KeyEvent event) {
        if (!itemSelectionModel.isEmpty()){
            switch (event.getCode()) {
                case ADD:
                    itemSelectionModel.getSelectedItem().addQuantity(1);
                    break;
                case SUBTRACT:
                    itemSelectionModel.getSelectedItem().subQuantity(1);
                    break;
                case F2:
                    //Checkout
                    break;
                case F3:
                    //discount
                    break;

            }
        }
        System.out.println(event.getCode());
    }
}
