package com.pos.controller;

import com.pos.business.Product;
import com.pos.db.ProductDB;
import com.pos.fxml.Page;
import com.pos.validation.FXMLValidator;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ItemController extends BaseController implements Initializable {

    @FXML
    private TextField nameText;

    @FXML
    private TextField categoryText;

    @FXML
    private TableColumn<Product, String> categoryColumn;

    @FXML
    private TableColumn<Product, Integer> quantityColumn;

    @FXML
    private TextField search;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableView<Product> selectedItemsTable;

    @FXML
    private TextField quantityText;

    @FXML
    private TextField priceText;

    @FXML
    private TextField descText;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private TableColumn<Product, String> descriptionColumn;

    ProductDB itdb = new ProductDB();
    private ObservableList<Product> items = FXCollections.observableArrayList();

    @FXML
    void handleLogoutButton(ActionEvent event) throws IOException {

        navigate(event, Page.LOGIN.getPage());
    }

    @FXML
    void handleSelectedItemsTable(ActionEvent event) {

        // selectedItemsTable.getSelectionModel().getSelectedItem();
    }

    @FXML
    void handleRemoveButton(ActionEvent event) {

        if (selectedItemsTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No item selected\nSelect the item you want to remove from the table");
            alert.showAndWait();
            selectedItemsTable.requestFocus();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setContentText("Are you sure you want delete " + this.getItem().getName() + " ?");
            alert.setHeaderText("Confirm Delete");
            Optional<ButtonType> button = alert.showAndWait();
            if (button.get().equals(ButtonType.OK)) {
                this.items.remove(this.getItem());
            }

        }

    }

    @FXML
    void handleSearch() {

    }

    @FXML
    void handleAddButton(ActionEvent event) {

        if (this.isValidInput()) {
            Product i = new Product();
            i.setName(nameText.getText());
            i.setDescription(descText.getText());
            i.setCategory(categoryText.getText());
            i.setPrice(Double.parseDouble(priceText.getText()));
            i.setQuantity(Integer.parseInt(quantityText.getText()));

            if(itdb.add(i)){
                System.out.println("Added successfully");
                items.add(i);
                selectedItemsTable.setItems(items);
            }

        }

    }

    @FXML
    void handleBackButton(ActionEvent event) throws IOException {

        navigate(event, Page.MANAGER.getPage());
    }

    @FXML
    void handleUpdateButton(ActionEvent event) {

        if (this.isValidInput()) {
            Product i = new Product();
            i.setName(nameText.getText());
            i.setDescription(descText.getText());
            i.setCategory(categoryText.getText());
            i.setPrice(Double.parseDouble(priceText.getText()));
            i.setQuantity(Integer.parseInt(quantityText.getText()));

            // if(itdb.update(i)){
            System.out.println("Update successfully");

            // }
        }
    }

    @FXML
    void handleClearButton(ActionEvent event) {

        nameText.setText("");
        descText.setText("");
        categoryText.setText("");
        priceText.setText("");
        quantityText.setText("");
    }

    public boolean isValidInput() {
        FXMLValidator f = new FXMLValidator();
        return f.isPresent(nameText, "Item name")
                && f.isPresent(descText, "Item Description")
                && f.isPresent(categoryText, "Category")
                && f.isPresent(priceText, "Price")
                && f.isDouble(priceText, "Price")
                && f.isPresent(quantityText, "Quantity")
                && f.isInteger(quantityText, "Quantity");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        items = itdb.getAll();
        selectedItemsTable.setItems(items);
        FilteredList<Product> filteredItems = new FilteredList<>(items, i->true);
        search.setOnKeyReleased(e-> {
            filteredItems.setPredicate(new Predicate<Product>() {
                @Override
                public boolean test(Product it) {
                    if(nameColumn.getText() == null || nameColumn.getText().isEmpty() ||
                            descriptionColumn.getText() == null || descriptionColumn.getText().isEmpty()) {
                        return true;
                    }
                    
                    return it.getName().toLowerCase().contains(search.getText().toLowerCase()) ||
                            it.getDescription().contains(search.getText());
                }
            });
        });

        
        selectedItemsTable.setItems(filteredItems);
    }

    public Product getItem() {
        return Product.class.cast(selectedItemsTable.getSelectionModel().getSelectedItem());
    }
}
