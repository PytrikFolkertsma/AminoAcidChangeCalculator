/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.pfolkertsma.aminoacidchangecalculator;

import java.io.IOException;
import java.sql.Connection;

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
        
    }
    
    protected void getStartStopPositions(){
        
    }
    
    protected void calculatePositionInCodon(){
        
    }
    
}
