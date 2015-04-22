/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.pfolkertsma.aminoacidchangecalculator;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author pfolkertsma
 */
public class LoginDAO {

    private Connection connection;

    void connect(String user, String pass, String url) {
        System.out.println("test connect");
        Class.forName("com.mysql.jdbc.Driver");
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
