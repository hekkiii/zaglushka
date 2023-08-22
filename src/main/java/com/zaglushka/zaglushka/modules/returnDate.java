package com.zaglushka.zaglushka.modules;

import java.text.SimpleDateFormat;
import java.util.Date;

public class returnDate {
    public static String returnDa(){
        Date date = new Date();
        String date1;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        date1 = formatter.format(date);
        return date1;
    }
}
