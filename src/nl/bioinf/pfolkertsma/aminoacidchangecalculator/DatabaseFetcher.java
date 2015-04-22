/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.pfolkertsma.aminoacidchangecalculator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pfolkertsma
 */
public class DatabaseFetcher {
    // kijkt welk DMC id bij het DMT id van de SNP hoort (cds_mrna_parents).
    // haalt de start- en stopposities op van DMC (cds_s).
    
    // positie = 0
    
    // voor item in startstopposities:
    //  kijk of de chr_pos van de SNP tussen de start- en stoppositie is:
    //      true:
    //            positie += chr_pos - start_pos
    //      false:
    //            positie += stoppos - startpos
    
    // getal % 3 returnen.
        private Connection connection;
        
        
    protected void getDmcId() throws IOException{
        try {
            String sql = "SELECT cds_id FROM cds_mrna_parents WHERE m_rna_id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "PGSC0003DMT400000001");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String dmcId = rs.getString("cds_id");
                System.out.println(dmcId);
                //return "";
            } else {
                throw new IllegalArgumentException("No cds_id for this m_rna_id is found.");
            }
        } catch (SQLException ex) {
            throw new IOException(ex);
        }
        
    }
    
    protected void getStartStopPositions(){
        
    }
    
    protected void calculatePositionInCodon(){
        
    }
    
}
