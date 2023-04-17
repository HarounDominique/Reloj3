package com.example.reloj3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

class Reloj implements ActionListener, Serializable {


    private boolean modo24horas;
    private boolean activar_alarma;
    private DateTimeFormatter formateador;

    public Reloj() {
        Timer t = new Timer(1000, this);
        t.start();
    }

    public void actionPerformed(ActionEvent e) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = sdf.format(d);

    }

}