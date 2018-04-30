package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    TableView<Product> table;
    TextField nameInput, priceInput, quantityInput;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("JavaFX-Demo-TableView");

        //Name cloumn
        TableColumn<Product, String> nameColumn = new TableColumn<>("Name"); // header
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(e -> {
            ((Product) e.getTableView().getItems().get(e.getTablePosition().getRow())).setName(e.getNewValue());
        });
//        nameColumn.setOnEditCommit(
//                new EventHandler<TableColumn.CellEditEvent<Product, String>>() {
//                    @Override
//                    public void handle(TableColumn.CellEditEvent<Product, String> event) {
//                        ((Product) event.getTableView().getItems().get(event.getTablePosition().getRow())).setName(event.getNewValue());
//                    }
//                }
//        );

        //Price cloumn
        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price"); // header
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Quantity cloumn
        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity"); // header
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        //Name input
        nameInput = new TextField();
        nameInput.setPromptText("Name");

        //Price input
        priceInput = new TextField();
        priceInput.setPromptText("Price");

        //Quantity input
        quantityInput = new TextField();
        quantityInput.setPromptText("Quantity");

        //Button
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addButtonClicked());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(nameInput, priceInput, quantityInput, addButton, deleteButton);

        table = new TableView<>();
        table.setEditable(true);
        table.setItems(getProduct());
        table.getColumns().addAll(nameColumn, priceColumn, quantityColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, hBox);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    //Add button clicked
    public void addButtonClicked() {
        Product product = new Product();
        product.setName(nameInput.getText());
        product.setPrice(Double.parseDouble(priceInput.getText()));
        product.setQuantity(Integer.parseInt(quantityInput.getText()));
        table.getItems().add(product);
        nameInput.clear();
        priceInput.clear();
        quantityInput.clear();
    }

    //Delete button clicked
    public void deleteButtonClicked() {
        ObservableList<Product> productSelected, allProducteds;
        allProducteds = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();

        productSelected.forEach(allProducteds::remove);
    }


    public static void main(String[] args) {
        launch(args);
    }

    // Get all of the product
    public ObservableList<Product> getProduct() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.add(new Product("Laptop", 859.00, 20));
        products.add(new Product("Bouncy Ball", 2.49, 198));
        products.add(new Product("Toilet", 99.00, 74));
        products.add(new Product("Bouncy Ball", 2.49, 198));
        products.add(new Product("The NoteBook DVD", 19.99, 198));
        products.add(new Product("Corn", 1.49, 856));
        return products;
    }
}
