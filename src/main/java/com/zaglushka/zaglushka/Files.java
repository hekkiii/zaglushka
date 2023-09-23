package com.zaglushka.zaglushka;

import java.io.*;
import java.util.Random;

public class Files {
    public void FileIn(String users){
        try (FileOutputStream fileOutputStream = new FileOutputStream("users.txt", true)){
            users = users + "\n";
            byte[] buffer = users.getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public String FileOut(){
        try(BufferedReader reader = new BufferedReader(new FileReader("users.txt"))){
            Random random = new Random();
            int count = counts();
            int n = random.nextInt(count);
            return reader.lines().skip(n).findFirst().get();
        } catch (IOException e){
            System.out.println("\nFile not found");
            System.out.println(e.getMessage());
        }
        return null;
    }

    private int counts(){
        int count = 0;
        String line;
        try(BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            while ((line = reader.readLine()) != null){
                count++;
            }
            return count;
        } catch (IOException e){
            System.out.println("\nFile not found");
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
