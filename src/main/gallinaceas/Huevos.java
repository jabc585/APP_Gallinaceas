package com.gallinaceas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Huevos extends Gallinaceas {

    Date fecha = new Date();
    DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);
    String fecha_envasado = df1.format(fecha);
    SimpleDateFormat formatod = new SimpleDateFormat("dd");

    int dia_caducidad = Integer.parseInt(formatod.format(fecha)) + 7;

    int codpuesta[][][][] = new int[4][100][1000][100];	
                    //Gallina-Prov-Ciudad-Granja

    public int setHuevos(int tipogall, int provincia, int ciudad, 
            int granja, int uds) {
        return codpuesta[tipogall][provincia][ciudad][granja] += uds;
    }	

}
