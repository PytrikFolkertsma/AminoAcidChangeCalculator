/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.pfolkertsma.aminoacidchangecalculator;

/**
 *
 * @author pfolkertsma
 */
public class DAOfactory {

    public static final LoginDAO createMysqlDAO(
            String user,
            String pass,
            String url) {
        LoginDAO dao = new LoginDAO();
        dao.connect(user, pass, url);
        return dao;
    }
}
