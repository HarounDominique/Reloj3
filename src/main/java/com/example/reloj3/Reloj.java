package com.example.reloj3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimerTask;

class Reloj extends Thread implements ActionListener, Serializable {


    private boolean modo24horas;
    private boolean activar_alarma;
    private DateTimeFormatter formateador;
    public String hora = "";

    public Reloj() {

    }

    public void arrancarReloj(){
        //Timer t = new Timer(1000, this);
        //t.start();

    }
    @Override
    public void run(){
        while(true){
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String formattedTime = sdf.format(d);
            this.hora = formattedTime;
        }
    }


    public void actionPerformed(ActionEvent e) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = sdf.format(d);
        this.hora = formattedTime;
    }
}