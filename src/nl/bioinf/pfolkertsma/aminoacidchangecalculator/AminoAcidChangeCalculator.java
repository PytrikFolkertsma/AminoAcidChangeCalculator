/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.pfolkertsma.aminoacidchangecalculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pfolkertsma & ngnodde
 */
public class AminoAcidChangeCalculator {

    LoginDAO dao;
    List<String> cdsIds;
    ArrayList<List> startStopPositions;
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        AminoAcidChangeCalculator ac = new AminoAcidChangeCalculator();
        ac.start();
    }

    public void start() throws IOException {
        CDS cds = new CDS();
        cds.readFastaFile();
        cds.createCdsHashmap();

        dao = DAOfactory.createMysqlDAO("<gebruikersnaam>", "<wachtwoord>", "<database url>");
//        List cdsIds = dao.getCdsId("PGSC0003DMT400000001");
//        System.out.println(dao.getStartStopPositions(cdsIds));
//        dao.disconnect();

        //Chromosome chr = new Chromosome();
        //chr.readFastaFile();
        //chr.createChromosomeHashmap();
        //CodontableGenerator.initializeTable();
        csvFileReader();

    }

    public void csvFileReader() {
        try (BufferedReader br = new BufferedReader(new FileReader("/commons/student/2014-2015/Thema11/NGnodde/CSV/SNPtestdata.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                String lineElements[] = line.split(";");
                //System.out.println(lineElements[0] + " " + lineElements[1] + " " + lineElements[2] + " " + lineElements[3] + " " + lineElements[4] + " " + lineElements[5] + " " + lineElements[6]);
                // 0: snp_id
                // 1: m_rna_id
                // 2: reference
                // 3: variants
                // 4: allele_variations
                // 5: chr
                // 6: chr_position
                cdsIds = new ArrayList(dao.getCdsId(lineElements[1]));
                startStopPositions = new ArrayList(dao.getStartStopPositions(cdsIds));
                int positionSnpInCodon = calculatePositionSnpInCds(Integer.parseInt(lineElements[6]));
                System.out.println(positionSnpInCodon);
//                    // if the line contains an SNV (length of 0 or 1)
//                    SNV snv = new SNV();
//                    snv.setId(Integer.parseInt(lineElements[0]));
//                    snv.setmRnaPos(Integer.parseInt(lineElements[1]));
//                    snv.setLength(Integer.parseInt(lineElements[3]));
//                    snv.setReference(lineElements[4]);
//                    snv.setVariants(lineElements[5].split("/"));
//                    snv.setChr(lineElements[6]);
//                    snv.setChrPos(Integer.parseInt(lineElements[7]));
//                    
//                    System.out.println("Codon position: " + calculateSnpCodonPosition(snv.getmRnaPos()));
//                    snv.createSNPcodon(calculateSnpCodonPosition(snv.getmRnaPos()));
//                    
//                    System.out.println("Reference: " + snv.getReference());
//                    System.out.println("Variants: " + snv.getVariants()[0]);
//                    
//                    System.out.println("Reference amino acid: " + snv.getReferenceAminoAcid());
//                    System.out.println("SNP amino acid: " + snv.getSnpAminoAcids());
//                    System.out.println("\n");
//                    
//                }
//                else if (Integer.parseInt(lineElements[3]) > 1){
//                    //System.out.println("MNV");
//                    MNV mnv = new MNV();
//                }
//                else {
//                    System.out.println("SNP type not found");
//                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int calculatePositionSnpInCds(int chrPos) {
        // klopt nog niet.        
        int positionSnpInCds = 0;
        for (List positions : startStopPositions) {
            int start = Integer.parseInt(positions.get(0).toString())-1;
            int stop = Integer.parseInt(positions.get(1).toString());
            if (chrPos > start && chrPos < stop) {
                positionSnpInCds += (chrPos - start);
            } else {
                positionSnpInCds += (stop - start);
            }
        }
        return positionSnpInCds;
    }
}
