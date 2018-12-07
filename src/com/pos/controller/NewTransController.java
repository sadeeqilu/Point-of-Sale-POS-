
package com.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.pos.business.Product;
import com.pos.business.ProductTrans;
import com.pos.db.ProductDB;
import com.pos.fxml.Page;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class NewTransController extends BaseController implements Initializable {

    @FXML
    private Text username;

    @FXML
    private JFXButton settingsBtn;

    @FXML
    private JFXButton LogoutBtn;

    @FXML
    private JFXComboBox<String> productCombo;

    @FXML
    private ComboBox<Integer> quantityCombo;

    @FXML
    private HBox priceBox;

    @FXML
    private Text priceTxt;

    @FXML
    private HBox subtotalBox;

    @FXML
    private Text subtotalTxt;

    @FXML
    private HBox addCancelBox;

    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private TableView<ProductTrans> transTable;

    @FXML
    private TableColumn<ProductTrans, String> productCol;

    @FXML
    private TableColumn<ProductTrans, Integer> quantityCol;

    @FXML
    private TableColumn<ProductTrans, String> priceCol;

    @FXML
    private TableColumn<ProductTrans, String> totalCol;

    @FXML
    private HBox totalBox;

    @FXML
    private Text totalTxt;

    @FXML
    private HBox amountBox;

    @FXML
    private JFXTextField amountTxt;

    @FXML
    private HBox changeBox;

    @FXML
    private Text changeTxt;

    @FXML
    private HBox payPrintBox;

    @FXML
    private JFXButton payBtn;

    @FXML
    private JFXButton printReceiptBtn;

    @FXML
    void HandleSettingsBtn(ActionEvent event) {

    }

    private void updateTable(String name){
        if(transTable != null){
                if(transTable.)
        }
    }
    
    @FXML
    void handleAddBtn(ActionEvent event) {
        
        productCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        
        ProductTrans trans = new ProductTrans();
        Product p = products.get(listCombo.indexOf(productCombo.getSelectionModel().getSelectedItem()));
        trans.setName(p.getName());
        trans.setPrice(Double.toString(p.getPrice()));
        trans.setQuantity(quantityCombo.getSelectionModel().getSelectedItem());
        trans.setSubtotal();

        items.add(trans);
      

        
        transTable.setItems(items);
        quantityCombo.setItems(FXCollections.observableArrayList());
        this.setSee(false);
        
    }

    @FXML
    void handleCancelBtn(ActionEvent event) {

    }

    @FXML
    void handleLogoutBtn(ActionEvent event) throws IOException {
        navigate(event, Page.LOGIN.getPage());
    }

    @FXML
    void handlePayBtn(ActionEvent event) {

        try{
            double total = Double.parseDouble(totalTxt.getText().replaceAll(",", ""));
            double amount = Double.parseDouble(amountTxt.getText());
            if(amount < total){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Enter value that is greater than or equal to the total amount of sale");
                alert.showAndWait();
                return;
            }
            changeTxt.setText(NumberFormat.getInstance().format(amount-total));
            amountTxt.setText(NumberFormat.getNumberInstance().format(amount));
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter value for total amount of sale");
            alert.showAndWait();
        }
        
        
    }

    @FXML
    void handlePrintReceiptBtn(ActionEvent event) {

        
        this.printReceipt();
        
        refresh();
        setCEE(false);
        
    }

    @FXML
    void handleRemoveSelectedBtn(ActionEvent event) {

        
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Remove from Cart ");
            alert.setContentText("Are you sure you want delete " + transTable.getSelectionModel().getSelectedItem().getName() + " ?");
            alert.setHeaderText("Confirm Delete");
            Optional<ButtonType> button = alert.showAndWait();
            if(button.get().equals(ButtonType.OK)) {
                double total = Double.parseDouble(totalTxt.getText());
                total = total - Double.parseDouble(transTable.getSelectionModel().getSelectedItem().getSubtotal().replaceAll(",", ""));
                totalTxt.setText(NumberFormat.getIntegerInstance().format(total));
                this.items.remove(transTable.getSelectionModel().getSelectedItem());
            }
        }
        catch(Exception e){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No item selected");
            alert.setContentText("Please select an item from table to remove");
            alert.showAndWait();
        }
    }
    
    @FXML
    void handleProductCombo(ActionEvent event) {
        //quantityCombo.setItems(null);
        
       // productCombo.getEditor().setOnKeyReleased(value);
        quantityCombo.getItems().removeAll();
        try{
            Product p = products.get(productCombo.getSelectionModel().getSelectedIndex());

            for(int i = 1; i <= p.getQuantity(); i++){
                quants.add(i);
            }

            quantityCombo.setItems(quants);
            quantityCombo.getSelectionModel().selectFirst();
            quantityCombo.setVisible(true);
            priceTxt.setText(Double.toString(p.getPrice()));
            subtotalTxt.setText(priceTxt.getText());
        }catch (Exception ex) {
            
        }
    }

    public void setCEE(boolean v){
        totalBox.setVisible(v);
        amountBox.setVisible(v);
        changeBox.setVisible(v);
        payPrintBox.setVisible(v);
    }
    @FXML
    void handleCheckoutBtn(ActionEvent event) {

        try{
            setCEE(true);
            totalTxt.setText(getTotalPriceFormatted());
        }
        catch(NullPointerException ex){
            setCEE(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty table ");
            alert.setContentText("There are no items selected, Please select an item from products at the top left corner.");
            alert.showAndWait();
        }
        //refresh();
    }
    
    public String getTotalPriceFormatted(){
        ObservableList<ProductTrans> ps = transTable.getItems();
        double total = 0;
        for (int i = 0; i < ps.size() ; i++) {
            total += Double.parseDouble(ps.get(i).getSubtotal().replaceAll(",", ""));
         }
        String t = NumberFormat.getInstance().format(total);
        return t;
    }
    
    public void refresh(){
        

        //try{
         products = itdb.getAll();
         quantityCombo.getItems().removeAll();
         quantityCombo.setVisible(false);
         productCombo.getItems().removeAll();
         changeTxt.setText("");
         priceTxt.setText("");
         totalTxt.setText("");
         amountTxt.setText("");
         
            if(productCombo.getSelectionModel().isEmpty()){
               

                for (int i = 0; i < products.size(); i++) {
                    listCombo.add(products.get(i).getName());
                }
                listCombo.sorted();
                productCombo.setItems(listCombo);
                
            }
            transTable.setItems(null);
//        }catch (Exception ex) {
//            
//        }
    }
    
    public void setSee(boolean v){
        subtotalTxt.setVisible(v);
        priceBox.setVisible(v);
        subtotalBox.setVisible(v);
        addCancelBox.setVisible(v);
    }
    @FXML
    void handleQuantityCombo(ActionEvent event) {

        
        try{
            double price = Double.parseDouble(priceTxt.getText());
            int q = quantityCombo.getSelectionModel().getSelectedItem();
            double subtotal = price * q;
            subtotalTxt.setText(NumberFormat.getNumberInstance().format(subtotal));
        }catch (Exception ex) {
            
        }
       
        setSee(true);
        
        
    }
    
      
    
    public void printReceipt() {
            
           try {
              
                List<Map<String,Object>> dataSource = new ArrayList<>();
                ObservableList<ProductTrans> ps = transTable.getItems();
                
               for (int i = 0; i < ps.size(); i++) {
                   Map<String,Object> row = new HashMap<String, Object>();
                    row.put("name", ps.get(i).getName());
                    row.put("price", ps.get(i).getPrice());
                    row.put("quantity", ps.get(i).getQuantity());
                    row.put("subtotal", ps.get(i).getSubtotal());

                    row.put("total", totalTxt.getText());
                    row.put("amount", amountTxt.getText());
                    row.put("change", changeTxt.getText());
                    dataSource.add(row);
               }

               
            
           JRDataSource jRDataSource = new JRBeanCollectionDataSource(dataSource);
               JasperReport jasperReport = JasperCompileManager.compileReport("src/com/pos/reports/Receipt.jrxml");
               JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, jRDataSource);
               JasperViewer jasperViwer = new JasperViewer(jasperPrint); 
                       jasperViwer.setVisible(true);
    } catch (Exception e) {
                System.out.println("ERROR in jasper"+e.getMessage());
           }
    
        
    
    }
    
    public Product getProduct(){
        return null;
    }

    ProductDB itdb = new ProductDB();
    //UserDB udb = new UserDB();
    public ObservableList<String> listCombo = FXCollections.observableArrayList();
    public ObservableList<Product> products = FXCollections.observableArrayList();
    public ObservableList<Integer> quants = FXCollections.observableArrayList();
    public ObservableList<ProductTrans> items = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
       
        //username.setText(LoginController.getUsername());
   
         refresh();
        AutoShowComboBoxHelper a = new AutoShowComboBoxHelper(productCombo, item -> (item));
            
    }

}
