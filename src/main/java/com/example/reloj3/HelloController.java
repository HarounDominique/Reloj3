package com.example.reloj3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloController {
    @FXML
    private Label reloj;

    @FXML
    protected void onHelloButtonClick() {
        Reloj r = new Reloj();
        reloj.setText(r.);
        timeLabel.setText(formattedTime);
    }
}