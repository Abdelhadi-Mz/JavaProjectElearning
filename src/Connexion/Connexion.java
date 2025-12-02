/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Destockafric
 */
public class Connexion {

    private static String url = "jdbc:mysql://localhost:3307/e_learning";
    private static String login = "root";
    private static String password = "";
    private static Connection connection = null;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,login,password);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.out.println("cant download the dirver");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("failed to connect");
        }
        
    }
    public static Connection getConnection() {
        return connection;
    }
    public static void main(String[] args) {
        
    }
}
    

