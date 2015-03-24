/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.pfolkertsma.aminoacidchangecalculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author pfolkertsma & ngnodde
 */
public class AminoAcidChangeCalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AminoAcidChangeCalculator ac = new AminoAcidChangeCalculator();
        ac.start();
    }

    public void start() {
        csvFileReader();
    }

    public void csvFileReader() {
        try (BufferedReader br = new BufferedReader(new FileReader("/commons/student/2014-2015/Thema11/NGnodde/CSV/testSNPdata.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String lineElements [] = line.split(";");
                if (lineElements[2].equals("SNV")){
                    System.out.println(lineElements[4]);
                    SNV snv = new SNV();
                }
                if (lineElements[2].equals("MNV")){
                    System.out.println("");
                }
                
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
