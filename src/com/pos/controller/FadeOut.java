/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.controller;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 *
 * @author macbookpro
 */
public class FadeOut {
    
    public static void fadeOut(Node node){
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(10000));
        fade.setNode(node);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }
}
