package com.zaglushka.zaglushka;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Actions {
    private static final String URL = "jdbc:postgresql://localhost:5432/testdb";
    private static final String dbUser = "postgres";
    private static final String dbPass = "postgrespassword";
    private static final String table1 = "first";
    private static final String table2 = "second";

    public User selectUser(String login) throws SQLException {
        String sql = "SELECT * FROM " + table1 + " INNER JOIN " + table2 + " ON " + table1 + ".login1 = " + table2 +
                ".login2 WHERE login1 = '" + login +"';";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, dbUser, dbPass);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            User user = null;

            if(rs.next()){
                user = new User(rs.getString("login1"), rs.getString("password"),
                        rs.getString("date"), rs.getString("email"));
            }

            return user;
        } catch (SQLException e){
            System.out.println("\nAn error has occurred...");
            System.out.println(e);
        }
        finally {
            if (conn != null) conn.close();
        }
        return null;
    }

    public String insertUser(User user){
        String sql = "INSERT INTO " + table1 + " VALUES (?, ?, ?); \n" + "INSERT INTO " + table2 + " VALUES (?, ?);";
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try (Connection conn = DriverManager.getConnection(URL, dbUser, dbPass);
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setDate(3, Date.valueOf(date.format(formatt)));
            ps.setString(4, user.getLogin());
            ps.setString(5, user.getEmail());

            int affectedRows = ps.executeUpdate();

            return "Affected rows = " + affectedRows;
        } catch (SQLException e){
            System.out.println("\nAn error has occurred...");
            System.out.println(e);
        }
        return null;
    }
}
