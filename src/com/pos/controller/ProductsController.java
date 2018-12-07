/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.controller;

/**
 *
 * @author macbookpro
 */

import com.pos.business.Product;
import com.pos.db.ProductDB;
import com.pos.fxml.Page;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import static jdk.nashorn.internal.objects.NativeString.search;

public class ProductsController extends BaseController implements Initializable{

    @FXML
    private Button btnDashboard;

    @FXML
    private Button customerPageBtn;

    @FXML
    private Button employeesPageBtn;

    @FXML
    private Button transactionsPageBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private BorderPane dashboardPane;

    @FXML
    private VBox dashboard;

    @FXML
    private Button newProductBtn;

    @FXML
    private TextField searchProductText;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, Integer> productIDCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, String> productDescriptionCol;

    @FXML
    private TableColumn<Product, String> productCategoryCol;

    @FXML
    private TableColumn<Product, Double> productPriceCol;

    @FXML
    private TableColumn<Product, Integer> productQuantityCol;

    @FXML
    private Button updateProductBtn;

    @FXML
    private Button delProductBtn;
    
    private ObservableList<Product> items = FXCollections.observableArrayList();
    
    ProductDB itdb = new ProductDB();

    @FXML
    void HandleCustomerPageBtn(ActionEvent event) throws IOException {

        navigate(event, Page.CUSTOMERS.getPage());
    }

    @FXML
    void HandleEmployeesPageBtn(ActionEvent event) throws IOException {

        navigate(event, Page.EMPLOYEES.getPage());
    }



    @FXML
    void handleNewProductBtn(ActionEvent event) throws IOException {

        navigate(event, Page.NEW_PRODUCT.getPage());
    }

     @FXML
    void handleBtnDashboard(ActionEvent event) throws IOException {

        navigate(event, Page.DASHBOARD.getPage());
    }

    @FXML
    void handleLogoutBtn(ActionEvent event) throws IOException {

        navigate(event, Page.LOGIN.getPage());
    }

    @FXML
    void handleSettingsBtn(ActionEvent event) throws IOException {

        navigate(event, Page.SETTINGS.getPage());
    }

    @FXML
    void handleTransactionsPageBtn(ActionEvent event) throws IOException {

        navigate(event, Page.TRANSACTION.getPage());
    }

    @FXML
    void handleUpdateProductBtn(ActionEvent event) throws IOException {

        navigate(event, Page.UPDATE_PRODUCT.getPage());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        productDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        productCategoryCol.setCellValueFactory(new PropertyValueFactory<>("Category"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
        productQuantityCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        items = itdb.getAll();
        productTable.setItems(items);
        
        FilteredList<Product> filteredItems = new FilteredList<>(items, i->true);
        searchProductText.setOnKeyReleased(e-> {
            filteredItems.setPredicate(new Predicate<Product>() {
                @Override
                public boolean test(Product it) {
                    if(productNameCol.getText() == null || productNameCol.getText().isEmpty() ||
                            productDescriptionCol.getText() == null || productDescriptionCol.getText().isEmpty() ||
                            productCategoryCol.getText() == null || productCategoryCol.getText().isEmpty()) {
                        return true;
                    }
                    
                    return it.getName().toLowerCase().contains(searchProductText.getText().toLowerCase()) ||
                            it.getDescription().toLowerCase().contains(searchProductText.getText().toLowerCase()) ||
                            it.getCategory().toLowerCase().contains(searchProductText.getText().toLowerCase());
                }
            });
        });

        
        productTable.setItems(filteredItems);
    }
    

}
