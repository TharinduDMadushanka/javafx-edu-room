package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

import java.awt.event.ActionEvent;

public class EventControllert {

    @FXML
    private Circle myCircle;
    private double x;
    private double y;

    public void up(javafx.event.ActionEvent event ){
        //System.out.println("Up");
        myCircle.setCenterY(y-=10);
    }

    public void down(javafx.event.ActionEvent event ){
        //System.out.println("Down");
        myCircle.setCenterY(y+=10);
    }

    public void left(javafx.event.ActionEvent actionEvent) {
        //System.out.println("Left");
        myCircle.setCenterY(x-=10);
    }

    public void right(javafx.event.ActionEvent event ){
        //System.out.println("Right");
        myCircle.setCenterY(x+=10);
    }

}
