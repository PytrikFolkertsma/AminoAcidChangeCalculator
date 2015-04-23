/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.pfolkertsma.aminoacidchangecalculator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pfolkertsma
 */
public class LoginDAO {

    private Connection connection;

    void connect(String user, String pass, String url) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect() throws IOException {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new IOException(ex);
        }
    }

    public List getCdsId(String mRnaId) throws IOException {
        List<String> cdsIds = new ArrayList();
        try {
            String sql = "SELECT cds_id FROM cds_mrna_parents WHERE m_rna_id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, mRnaId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String cdsId = rs.getString("cds_id");
                cdsIds.add(cdsId);
            }
        } catch (SQLException ex) {
            throw new IOException(ex);
        }
        return cdsIds;
    }

    public ArrayList getStartStopPositions(List cdsIds) throws IOException {
        ArrayList<List> startStopPositions = new ArrayList();
        for (Object cdsId : cdsIds) {
            try {
                String sql = "SELECT chr_start, chr_stop FROM cds_s WHERE generated_id=?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, cdsId.toString());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    List<String> line = new ArrayList();
                    String start = rs.getString("chr_start");
                    String stop = rs.getString("chr_stop");
                    line.add(start);
                    line.add(stop);
                    startStopPositions.add(line);
                }
            } catch (SQLException ex) {
                throw new IOException(ex);
            }
        }

        return startStopPositions;
    }

}
