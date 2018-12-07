/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.fxml;

import java.net.URL;

/**
 *
 * @author ASIYA
 */
public enum Page {
    LOGIN("loginFXML.fxml"),
    SIDENAV("nav1.fxml"),
    DASHBOARD("dashboard.fxml"),
    EMPLOYEES("employees.fxml"),
    NEW_EMPLOYEE("new_employee.fxml"),
    UPDATE_EMPLOYEE("update_emplyee.fxml"),
    CUSTOMERS("customers.fxml"),
    NEW_CUSTOMER("new_customer.fxml"),
    UPDATE_CUSTOMER("update_customer.fxml"),
    PRODUCTS("products.fxml"),
    NEW_PRODUCT("new_product.fxml"),
    UPDATE_PRODUCT("update_product.fxml"),
    SETTINGS("UserSettings.fxml"),
    TRANSACTION("transactions.fxml"),
    MANAGER("managerfxml.fxml"),
    ITEM("item.fxml"),
    newTR("trans.fxml");
    
    private final String fxml;
    Page(String fxml){
        this.fxml = fxml;
    }
    
    public URL getPage(){
        return getClass().getResource(fxml);
    }
    
}
