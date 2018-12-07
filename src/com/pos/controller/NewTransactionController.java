/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXButton;
import com.pos.business.Product;
import com.pos.db.ProductDB;
import com.pos.fxml.Page;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author macbookpro
 */
public class NewTransactionController extends BaseController implements Initializable {

    


    @FXML
    private VBox vboxMain;

    @FXML
    private VBox boxTrans;

    @FXML
    private JFXButton logoutBtn;

    @FXML
    private HBox tBox;

    @FXML
    private Text snTxt;

    @FXML
    private JFXComboBox<String> nameCombo;

    @FXML
    private Spinner<Integer> qtySpinner;

    @FXML
    private Text priceTxt;

    @FXML
    private Text subTxt;

    @FXML
    private JFXButton delBtn;

    @FXML
    private JFXButton addBtn;

    @FXML
    private Button printReceiptBtn;

    @FXML
    private Button payBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    void handleAddBtn(ActionEvent event) {

        ObservableList<Node> nodes = FXCollections.observableArrayList();
        ObservableList<String> serial = FXCollections.observableArrayList();
        HBox a = tBox;
        
        //nodes = boxTrans.getChildren();
//        for (int i = 0; i < nodes.size(); i++) {
//            Text sn = (Text) (nodes.get(i).getClip());
//            serial.add(sn.getText());
//            System.out.println("here "+sn.getText());
//        }
       // boxTrans.getChildren().add(a);
       doChild();
        
    }

    @FXML
    void handleCancelBtn(ActionEvent event) {

    }

    @FXML
    void handleDelBtn(ActionEvent event) {

        boxTrans.getChildren().remove(boxTrans.getChildren().size()-1);
    }
    
    @FXML
    void handlSettingsBtn(ActionEvent event) {

    }

    @FXML
    void handleLogoutBtn(ActionEvent event) throws IOException {

        navigate(event, Page.LOGIN.getPage());
    }



    @FXML
    void handlePayBtn(ActionEvent event) {

    }

    @FXML
    void handlePrintReceiptBtn(ActionEvent event) {

    }

    @FXML
    void handleSearchItemCombo(ActionEvent event) {

        Spinner qty1 = new Spinner(1, 20, 1);
        qty1.setEditable(true);
        qty1.setMaxWidth(80);
        Product item = items.get(listCombo.indexOf(nameCombo.getSelectionModel().getSelectedItem()));
        qtySpinner = qty1;
        tBox.getChildren().set(2, qtySpinner);
        //tBox.getChildren().add(2, qtySpinner);
        priceTxt.setText(item.getPrice()+"");
        double sub = item.getPrice()*qtySpinner.getValue();
        subTxt.setText(sub+"");
        
    }

    

    public void doChild(){
      HBox h2 = new HBox();
      h2.setPadding(new Insets(0,0,0,20));
      h2.setSpacing(30);
            h2.setStyle("-fx-border-color: #2A3135;"
                    + "-fx-background-color: white;"
                    + "-fx-border-width: 1px 1px 1px 1px;");
            h2.setPadding(new Insets(10,10,10,10));
            Spinner qty1 = new Spinner(1, 20, 1);
            qty1.setEditable(true);
            h2.getChildren().add(qty1);
            h2.setAlignment(Pos.CENTER);
           // boxTrans.getChildren().add(h2);
            
            Text t1 = new Text();
            t1.setText("Name");
            h2.getChildren().add(t1);
            t1 = new Text();
            t1.setText("unit Price");
            h2.getChildren().add(t1);
            t1 = new Text();
            t1.setText("Amount");
            h2.getChildren().add(t1);
            Button b1 = new Button("Add");
      
      boxTrans.getChildren().add(h2);
    }

    ProductDB itdb = new ProductDB();
    private ObservableList<Product> items = FXCollections.observableArrayList();
    private ObservableList<String> listCombo = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        items = itdb.getAll();

        for(int i = 0; i < items.size(); i++){
            listCombo.add(items.get(i).getName());
        }
        
        nameCombo.setItems(listCombo);
 
        try {
            AutoShowComboBoxHelper a = new AutoShowComboBoxHelper(nameCombo, item -> (item));
        } catch (Exception e) {
        }

    
        // TODO
        //doChild();
            //i--;
        //}
    }    
    
}
