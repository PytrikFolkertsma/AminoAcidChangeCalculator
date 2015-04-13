/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.pfolkertsma.aminoacidchangecalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * @author pfolkertsma
 */
public class Chromosome {
    
    protected String[] descriptions;
    protected String[] sequences;
    protected static HashMap <String, String> chromosomes = new HashMap();
    
    protected void readFastaFile(){
        Path path = Paths.get("/commons/student/2014-2015/Thema11/NGnodde/PotatoGenome/chr1test.fasta"); 
        List desc = new ArrayList();
        List seq = new ArrayList();

        try {
            BufferedReader reader;
            reader = Files.newBufferedReader(path, Charset.defaultCharset());
           
            StringBuffer buffer = new StringBuffer();
            
            String line = reader.readLine();
           
            if (line == null) {
                throw new IOException("Empty file");
            }
            if (line.charAt(0) != '>') {
                throw new IOException("First line of file should start with '>'");
            } else {
                desc.add(line);
            }
            
            for (line = reader.readLine().trim(); line != null; line = reader.readLine()) {
                if (line.length() > 0 && line.charAt(0) == '>') {
                    seq.add(buffer.toString());
                    buffer = new StringBuffer();
                    desc.add(line);
                } else {
                    buffer.append(line.trim());
                }
            }
            if (buffer.length() != 0) {
                seq.add(buffer.toString());
            }
        } catch (IOException e) {
            System.out.println("Error when reading file");
            e.printStackTrace();
        }

        descriptions = new String[desc.size()];
        sequences = new String[seq.size()];
        for (int i = 0; i < seq.size(); i++) {
            descriptions[i] = (String) desc.get(i);
            sequences[i] = (String) seq.get(i);
        }
    } 
    
    protected void createChromosomeHashmap(){
        IntStream.range(0, descriptions.length).forEach(
            n -> {
                chromosomes.put(descriptions[n].substring(1), sequences[n]);
            }
        );
    }
}
