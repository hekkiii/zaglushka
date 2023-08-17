package com.zaglushka.zaglushka;

import java.text.SimpleDateFormat;
import java.util.Date;

//Class for POST method
public class Posting {
    private final String username;
    private final String password;
    private final String date;
    public Posting(String username, String password){
        this.username = username;
        this.password = password;
        Date date1 = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.date = formatter.format(date1);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getDate(){
        return date;
    }
}
