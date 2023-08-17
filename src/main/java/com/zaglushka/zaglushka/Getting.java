package com.zaglushka.zaglushka;


//Class for GET method
public class Getting {
    private final long id;
    private final String content;

    public Getting() {
        this.id = 1;
        this.content = "Hello from GET!";
    }

    public long getId() {
        return id;
    }

    public String getContent(){
        return content;
    }
}
