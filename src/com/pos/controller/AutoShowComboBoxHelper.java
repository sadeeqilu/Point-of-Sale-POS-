/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ComboBox;
import javafx.util.Callback;

/**
 *
 * @author macbookpro
 */
public class AutoShowComboBoxHelper {
    
    public AutoShowComboBoxHelper(final ComboBox<String> comboBox, final Callback<String, String> textBuilder) {
        final ObservableList<String> items = FXCollections.observableArrayList(comboBox.getItems());

        comboBox.getEditor().textProperty().addListener((ov, o, n) -> {
            if (n.equals(comboBox.getSelectionModel().getSelectedItem())) {
                return;
            }

            comboBox.hide();
            final FilteredList<String> filtered = items.filtered(s -> textBuilder.call(s).toLowerCase().contains(n.toLowerCase()));
            if (filtered.isEmpty()) {
                comboBox.getItems().setAll(items);
            } else {
                comboBox.getItems().setAll(filtered);
                comboBox.show();
            }
        });
    }
}
