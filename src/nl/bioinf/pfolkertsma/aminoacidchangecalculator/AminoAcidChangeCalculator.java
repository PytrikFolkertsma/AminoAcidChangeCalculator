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
        Chromosome chr = new Chromosome();
        chr.readFastaFile();
        chr.createChromosomeHashmap();
        CodontableGenerator.initializeTable();
        csvFileReader();
        
    }

    public void csvFileReader() {
        try (BufferedReader br = new BufferedReader(new FileReader("/commons/student/2014-2015/Thema11/NGnodde/CSV/testSNPdata2.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                String lineElements [] = line.split(";");
                if (lineElements[3].equals("1") || lineElements[3].equals("0")){
                    // if the line contains an SNV (length of 0 or 1)
                    SNV snv = new SNV();
                    snv.setId(Integer.parseInt(lineElements[0]));
                    snv.setmRnaPos(Integer.parseInt(lineElements[1]));
                    snv.setLength(Integer.parseInt(lineElements[3]));
                    snv.setReference(lineElements[4]);
                    snv.setVariants(lineElements[5].split("/"));
                    snv.setChr(lineElements[6]);
                    snv.setChrPos(Integer.parseInt(lineElements[7]));
                    
                    System.out.println(calculateSnpCodonPosition(snv.getmRnaPos()));
                    snv.createSNPcodon(calculateSnpCodonPosition(snv.getmRnaPos()));
                    
                    System.out.println("Reference: " + snv.getReference());
                    System.out.println("Variants: " + snv.getVariants());
                    
                    System.out.println("Reference amino acid: " + snv.getReferenceAminoAcid());
                    System.out.println("SNP amino acid: " + snv.getSnpAminoAcids());
                    System.out.println("\n \n");
                    
                }
                else if (Integer.parseInt(lineElements[3]) > 1){
                    //System.out.println("MNV");
                    MNV mnv = new MNV();
                }
                else {
                    System.out.println("SNP type not found");
                }
                
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public int calculateSnpCodonPosition(int mrnaPos) {
        if (mrnaPos % 3 == 1) {
            return 1;
        } else if (mrnaPos % 3 == 2) {
            return 2;
        } else if (mrnaPos % 3 == 0) {
            return 3;
        } else {
            return -1;
        }
    }

}
