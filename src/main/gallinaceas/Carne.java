package com.gallinaceas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Carne extends Gallinaceas {

    Date fecha = new Date();
    DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);
    String fecha_envasado = df1.format(fecha);
    SimpleDateFormat formatod = new SimpleDateFormat("dd");

    int dia_caducidad = Integer.parseInt(formatod.format(fecha)) + 4;
    int stock_c = 1000;

    @Override
    public String toString() {
        return "El stock de la carne de fecha de envasado " + 
                this.fecha_envasado + " con caducidad "
                + this.dia_caducidad + " del mismo mes, es de " + 
                this.stock_c + " kg";
    }

    public int stock_carne(int kilos) {
        return stock_c += kilos;
    }

}