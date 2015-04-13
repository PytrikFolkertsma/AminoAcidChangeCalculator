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
public class Codon {
    protected int firstNucChrPos;
    protected int secondNucChrPos;
    protected int thirdNucChrPos;
    protected String firstNucleotide;
    protected String secondNucleotide;
    protected String thirdNucleotide;
    protected String aminoAcid;
    
    protected void getNucleotides(String chr){
        firstNucleotide = Character.toString(Chromosome.chromosomes.get(chr).charAt(firstNucChrPos));
        secondNucleotide = Character.toString(Chromosome.chromosomes.get(chr).charAt(secondNucChrPos));
        thirdNucleotide = Character.toString(Chromosome.chromosomes.get(chr).charAt(thirdNucChrPos));
    }
    
    protected void getAminoAcid(){
        aminoAcid = CodontableGenerator.table.get(firstNucleotide + secondNucleotide + thirdNucleotide).toString();
    }
}
