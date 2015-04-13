/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.pfolkertsma.aminoacidchangecalculator;

import java.util.ArrayList;

/**
 *
 * @author pfolkertsma & ngnodde
 */
public class SNV {

    /**
     * The id of the SNP.
     */
    private int id;

    /**
     * The mRNA position of the SNP.
     */
    private int mRnaPos;

    /**
     * The reference of the SNP.
     */
    private String reference;

    /**
     * The SNP variants.
     */
    private String[] variants;

    /**
     * A list with the aminoAcidChanges the SNP causes.
     */
    private String[] aminoAcidChanges;

    /**
     * The chromosome position of the SNP.
     */
    private int chrPos;

    /**
     * A boolean which is true if the SNP can cause a frameshift mutation and
     * false if it can not.
     */
    private boolean frameshift;

    /**
     * The length of the SNP.
     */
    private int length;

    /**
     * The chromosome the SNP is from.
     */
    private String chr;

    /**
     * The amino acid of the codon the reference is in.
     */
    private char referenceAminoAcid;

    /**
     * The amino acids of the codon the SNP are in.
     */
    private ArrayList snpAminoAcids;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getmRnaPos() {
        return mRnaPos;
    }

    public void setmRnaPos(int mRnaPos) {
        this.mRnaPos = mRnaPos;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String[] getVariants() {
        return variants;
    }

    public void setVariants(String[] variants) {
        this.variants = variants;
    }

    public String[] getAminoAcidChanges() {
        return aminoAcidChanges;
    }

    public void setAminoAcidChanges(String[] aminoAcidChanges) {
        this.aminoAcidChanges = aminoAcidChanges;
    }

    public int getChrPos() {
        return chrPos;
    }

    public void setChrPos(int chrPos) {
        this.chrPos = chrPos;
    }

    public boolean isFrameshift() {
        return frameshift;
    }

    public void setFrameshift(boolean frameshift) {
        this.frameshift = frameshift;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getChr() {
        return chr;
    }

    public void setChr(String chr) {
        this.chr = chr;
    }

    public char getReferenceAminoAcid() {
        return referenceAminoAcid;
    }

    public void setReferenceAminoAcid(char referenceAminoAcid) {
        this.referenceAminoAcid = referenceAminoAcid;
    }

    public ArrayList getSnpAminoAcids() {
        return snpAminoAcids;
    }

    public void setSnpAminoAcids(ArrayList snpAminoAcids) {
        this.snpAminoAcids = snpAminoAcids;
    }

    protected Codon createCodon(int x, String reference) {
        Codon codon = new Codon();
        codon.firstNucChrPos = this.chrPos - x;
        codon.secondNucChrPos = this.chrPos - x + 1;
        codon.thirdNucChrPos = this.chrPos - x + 2;

//        if (reference.equals("-") && x == 1){
//            codon.firstNucChrPos = this.chrPos -x +2;
//            codon.secondNucChrPos = this.chrPos -x + 3;    
//            codon.thirdNucChrPos = this.chrPos -x + 4;
//        } else if (reference.equals("-") && x == 2){
//            codon.secondNucChrPos = this.chrPos -x + 3;
//            codon.thirdNucChrPos = this.chrPos -x + 4;
//        } else if (reference.equals("-") && x == 3){
//            codon.thirdNucChrPos = this.chrPos -x + 4;
//        }
        codon.getNucleotides(chr);
        codon.setAminoAcid();
        setReferenceAminoAcid(codon.aminoAcid);
        return codon;
    }

    protected void createSNPcodon(int x) {
        Codon codonRef = createCodon(x, reference);
        ArrayList<Character> snpAA = new ArrayList();
        System.out.println("Referentiecodon: " + codonRef.firstNucleotide + codonRef.secondNucleotide + codonRef.thirdNucleotide);
        for (String variant : variants) {
            Codon codonSnp = new Codon();
            codonSnp = codonRef;

            // check if reference = "-" (insertion of SNP)
            if (reference.equals("-")) {
                if (x == 1) {
                    codonSnp.secondNucChrPos = this.chrPos -x ;
                    codonSnp.thirdNucChrPos = this.chrPos -x + 1;
                    codonSnp.getNucleotides(chr);
                    codonSnp.firstNucleotide = variant;
                    codonSnp.setAminoAcid();
                    System.out.println("SNP codon 1: " + codonSnp.firstNucleotide + codonSnp.secondNucleotide + codonSnp.thirdNucleotide);
                } else if (x == 2) {
                    codonRef.thirdNucChrPos = this.chrPos -x;
                    codonSnp.getNucleotides(chr);
                    codonSnp.secondNucleotide = variant;
                    codonSnp.setAminoAcid();
                    System.out.println("SNP codon 2: " + codonSnp.firstNucleotide + codonSnp.secondNucleotide + codonSnp.thirdNucleotide);
                } else if (x == 3) {
                    codonRef.thirdNucChrPos = this.chrPos;
                    codonSnp.thirdNucleotide = variant;
                    codonSnp.setAminoAcid();
                    System.out.println("SNP codon 3: " + codonSnp.firstNucleotide + codonSnp.secondNucleotide + codonSnp.thirdNucleotide);
                }
            // check if variant = "-" (deletion of SNP)
            } else if (variant.equals("-")) {
                if (x == 1) {
                    // codonSnp.secondNucChrPos = this.chrPos;
                    //
                    //
                    //
                    //
                }
                
            } else {
                if (x == 1) {
                    codonSnp.firstNucleotide = variant; // the SNP is on the first position of the codon.
                } else if (x == 2) {
                    codonSnp.secondNucleotide = variant; // the SNP is on the second position in the codon.
                } else if (x == 3) {
                    codonSnp.thirdNucleotide = variant; // the SNP is on the third position in the codon.
                }
                codonSnp.setAminoAcid();
                snpAA.add(codonSnp.aminoAcid);
                System.out.println("SNPcodon: " + codonSnp.firstNucleotide + codonSnp.secondNucleotide + codonSnp.thirdNucleotide);
            }
        }
        setSnpAminoAcids(snpAA);
    }
}
