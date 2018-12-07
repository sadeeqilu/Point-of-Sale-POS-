/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.business;

import java.text.NumberFormat;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ASIYA
 */
public class ProductTrans {

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty price = new SimpleStringProperty();
    private final IntegerProperty quantity = new SimpleIntegerProperty();
    private final StringProperty subtotal = new SimpleStringProperty();

    public String getSubtotal() {
        return subtotal.get();
    }

    public void setSubtotal() {
        subtotal.set(NumberFormat
                .getNumberInstance().format(Double.parseDouble(this.getPrice())*this.getQuantity()));
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int value) {
        quantity.set(value);
    }

    public String getPrice() {
        return price.get();
    }

    public void setPrice(String value) {
        price.set(value);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    @Override
    public String toString() {
        return  name + "  " + price + " X" + quantity +"  "+ subtotal + "\n";
    }

    

}
