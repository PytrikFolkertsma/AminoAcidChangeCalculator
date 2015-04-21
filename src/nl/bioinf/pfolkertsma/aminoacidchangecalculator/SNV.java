/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.pfolkertsma.aminoacidchangecalculator;

import java.util.ArrayList;
import java.util.List;

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

    protected Codon createCodon(int x) {
        Codon codon = new Codon();
        codon.firstNucChrPos = this.chrPos - x;
        codon.secondNucChrPos = this.chrPos - x + 1;
        codon.thirdNucChrPos = this.chrPos - x + 2;
        codon.getNucleotides(chr);
        codon.setAminoAcid();
        setReferenceAminoAcid(codon.aminoAcid);
        return codon;
    }

    protected List createSNPcodon(int x) {
        Codon codonRef = createCodon(x);
        List<String> codonsSnp = new ArrayList<>();
        ArrayList<Character> snpAA = new ArrayList();
        System.out.println("Reference codon: " + codonRef.toString());
        for (String variant : variants) {
            Codon codonSnp = new Codon();
            codonSnp = codonRef;
            // check if reference = "-" (insertion of nucleotide (SNP))
            if (reference.equals("-")) {
                if (x == 1) {
                    codonSnp.secondNucChrPos = this.chrPos - 1;
                    codonSnp.thirdNucChrPos = this.chrPos;
                    codonSnp.getNucleotides(chr);
                    codonSnp.firstNucleotide = variant;
                } else if (x == 2) {
                    codonRef.thirdNucChrPos = this.chrPos - 1;
                    codonSnp.getNucleotides(chr);
                    codonSnp.secondNucleotide = variant;
                } else if (x == 3) {
                    codonRef.thirdNucChrPos = this.chrPos;
                    codonSnp.thirdNucleotide = variant;
                }
                codonSnp.setAminoAcid();
                snpAA.add(codonSnp.aminoAcid);
                System.out.println("SNPcodon: " + codonSnp.toString());
                codonsSnp.add(codonSnp.toString());
                // check if variant = "-" (deletion of nucleotide)
            } else if (variant.equals("-")) {
                if (x == 1) {
                    codonSnp.firstNucChrPos = this.chrPos;
                    codonSnp.secondNucChrPos = this.chrPos + 1;
                    codonSnp.thirdNucChrPos = this.chrPos + 2;
                } else if (x == 2) {
                    codonSnp.secondNucChrPos = this.chrPos;
                    codonSnp.thirdNucChrPos = this.chrPos + 1;
                } else if (x == 3) {
                    codonSnp.thirdNucChrPos = this.chrPos;
                }
                codonSnp.getNucleotides(chr);
                codonSnp.setAminoAcid();
                snpAA.add(codonSnp.aminoAcid);
                System.out.println("SNPcodon: " + codonSnp.toString());
                codonsSnp.add(codonSnp.toString());
                // if there are no insertions/deletions, change the reference to the variant.
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
                System.out.println("SNPcodon: " + codonSnp.toString());
                codonsSnp.add(codonSnp.toString());
            }
        }
        setSnpAminoAcids(snpAA);
        return codonsSnp;
    }
}
