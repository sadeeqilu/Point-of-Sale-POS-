/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.controller;

import com.pos.business.Product;
import com.pos.business.ProductTrans;
import com.pos.db.ProductDB;
import com.pos.fxml.Page;
import com.pos.validation.FXMLValidator;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ASIYA
 */
public class ManagerController extends BaseController implements Initializable {

    @FXML
    private TableColumn<ProductTrans, Double> unitPriceColumn;

 
    
    @FXML
    private TextField amountIssuedText;
    
    @FXML
    private ComboBox<String> itemCombo;

    @FXML
    private ComboBox<Integer> quantityCombo;

    @FXML
    private TableColumn<ProductTrans, Double> subtotalColumn;

    @FXML
    private TableColumn<ProductTrans, Integer> quantityColumn;

    @FXML
    private TableColumn<ProductTrans, String> nameColumn;

    @FXML
    private TextField searchEmployeeText;


    @FXML
    private Button updateEmployeeBtn;

    @FXML
    private Button delEmployeeBtn;

    @FXML
    private TextField quantityText;

    @FXML
    private Text unitPrice;
    
    @FXML
    private Text userText;



    @FXML
    private Text totalText;
    
    @FXML
    private Text changeText;
    
    @FXML
    private Button removeButton;

    @FXML
    private Button payButton;
    
    @FXML
    private Button cancelButton;
    
    @FXML
    private Text subtotalText;
    
    
    
    
    
    
    
    

    @FXML
    private Button newCustomerBtn;


    
    
    
    
    
    double tota = 0;

    ProductDB itdb = new ProductDB();

    
    public ObservableList<String> listCombo = FXCollections.observableArrayList();
    public ObservableList<Product> items = FXCollections.observableArrayList();
    public ObservableList<Integer> quants = FXCollections.observableArrayList();
    public ObservableList<ProductTrans> itemmm = FXCollections.observableArrayList();
   


    
    @FXML
    void handleItemCombo(ActionEvent event) {

        //item = new Product();
        Product item = items.get(listCombo.indexOf(itemCombo.getSelectionModel().getSelectedItem()));
        unitPrice.setDisable(false);
        unitPrice.setText(Double.toString(item.getPrice()));
        unitPrice.setVisible(true);
        subtotalText.setDisable(false);
        quants.remove(0, quants.size());
        for(int i = 1; i <= item.getQuantity() ; i++){
            quants.add(i);
        }
        quantityCombo.setItems(quants);
        quantityCombo.getSelectionModel().selectFirst();

    }
    
//    public Product getItem(){
//        return 
//                Product.class.cast(selectedItemsTable.getSelectionModel().getSelectedItem());
//    }

    @FXML
    void handleAddButton(ActionEvent event) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        unitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        subtotalColumn.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        
        //total.setText("0");
        ProductTrans itemtrans = new ProductTrans();
        Product item = items.get(listCombo.indexOf(itemCombo.getSelectionModel().getSelectedItem()));
        itemtrans.setName(item.getName());
        itemtrans.setPrice(Double.toString(item.getPrice()));
        double subtotal = Double.parseDouble(subtotalText.getText());
        itemtrans.setSubtotal();
        
//        itemtrans.setQuantity(quantityCombo.getSelectionModel().getSelectedItem());
//                itemmm.add(itemtrans);
//        selectedItemsTable.setItems(itemmm);
        
       tota = 0;
//      for(int i = 0; i <= selectedItemsTable.getItems().size()-1; i++){
//          selectedItemsTable.getSelectionModel().select(i);
//          tota += selectedItemsTable.getSelectionModel().getSelectedItem().getSubtotal();
//      }
      NumberFormat number = NumberFormat.getInstance();
      totalText.setText(number.format(tota));
        System.out.println(tota);
      //  double t = subtotalColumn.getCellData(0);
       // total.setVisible(true);
       itemCombo.getSelectionModel().clearSelection();
       unitPrice.setVisible(false);
       unitPrice.setText("0");
       quantityCombo.getSelectionModel().clearSelection();
       subtotalText.setText("0");
       subtotalText.setVisible(false);
    }

    @FXML
    void handleClearButton(ActionEvent event) {

        
        
    }

    @FXML
    void handleQuantityCombo(ActionEvent event) {

         double price = Double.parseDouble(unitPrice.getText());
         int q = quantityCombo.getSelectionModel().getSelectedItem();
         double subtotal = price * q;
         subtotalText.setText(Double.toString(subtotal));
         subtotalText.setVisible(true);
      
    }

    @FXML
    void handlePayButton(ActionEvent event) {

         FXMLValidator fx = new FXMLValidator();
          if(fx.isPresent(amountIssuedText, "Amount Issued") && fx.isDouble(amountIssuedText, "Amount Issued")){
             double amount = Double.parseDouble(amountIssuedText.getText());
             if(amount < tota){
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setContentText("Amount issued is less than total");
                 alert.showAndWait();
                 amountIssuedText.requestFocus();
             }
             
                double change = amount - tota;
                NumberFormat number = NumberFormat.getInstance();
             
                changeText.setText(number.format(change));
                changeText.setFill(Color.GREEN);
               // this.print();
                //amountIssuedText.setOnKeyReleased(value);
          }

        
      
    }

    private void print(){
        Printer printer =  Printer.getDefaultPrinter();
        PrinterJob job = PrinterJob.createPrinterJob();
        JobSettings set = job.getJobSettings();
        PageLayout pageLayout = set.getPageLayout();
        List<ProductTrans> forprint = itemmm;
        TextArea receipt = new TextArea();
        receipt.appendText("     Receipt      \n");
        receipt.appendText("                    ");
        receipt.appendText("Name       unit Price        quantity       subtotal");
        for(int i = 0; i <= forprint.size()-1; i++){
            receipt.appendText(forprint.get(i).toString());
        }
        receipt.appendText("                                          Total = "+tota);
        receipt.appendText("                                          change = "+changeText.getText());
         
        pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.EQUAL);

        set.setPageLayout(pageLayout);
        job.printPage(receipt);

    }
    
   
    
    @FXML
    void handleCancelButton(ActionEvent event) {

//                itemmm.removeAll(selectedItemsTable.getItems());
//                selectedItemsTable.refresh();
            
                changeText.setText("");
                totalText.setText("");
                amountIssuedText.setText("");
    }

    @FXML
    void handleRemoveButton(ActionEvent event) {

//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("");
//        alert.setContentText("Are you sure you want delete " + selectedItemsTable.getSelectionModel().getSelectedItem().getName() + " ?");
//        alert.setHeaderText("Confirm Delete");
//        Optional<ButtonType> button = alert.showAndWait();
//        if(button.get().equals(ButtonType.OK)) {
//            double total = Double.parseDouble(totalText.getText());
//            total = total - selectedItemsTable.getSelectionModel().getSelectedItem().getSubtotal();
//            totalText.setText(Double.toString(total));
//            this.itemmm.remove(selectedItemsTable.getSelectionModel().getSelectedItem());
//        }
    }
    

    @FXML
    void handleSettingsButton(ActionEvent event) {

        
    }

    @FXML
    void handleLogoutButton(ActionEvent event) throws IOException {

        navigate(event, Page.LOGIN.getPage());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

//        items = itdb.getAll();
//        for(int i = 0; i < items.size(); i++){
//            listCombo.add(items.get(i).getName());
//        }
//        
//        itemCombo.setItems(listCombo);
//  
//    }
    }
}
