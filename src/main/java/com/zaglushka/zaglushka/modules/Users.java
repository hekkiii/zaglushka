package com.zaglushka.zaglushka.modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private String username;
    private String password;
    private String date = returnDate.returnDa();
}
