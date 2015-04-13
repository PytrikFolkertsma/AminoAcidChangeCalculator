/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.pfolkertsma.aminoacidchangecalculator;

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
    private String [] variants;

    /**
     * A list with the aminoAcidChanges the SNP causes.
     */
    private String [] aminoAcidChanges;

    /**
     * The chromosome position of the SNP.
     */
    private int chrPos;

    /**
     * A boolean which is true if the SNP can cause a frameshift mutation and false if it can not.
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
       
    protected void createCodon(){
        Codon codon = new Codon();
        codon.firstNucChrPos = this.chrPos-1;
        codon.secondNucChrPos = this.chrPos;
        codon.thirdNucChrPos = this.chrPos+1;
        codon.getNucleotides(chr);
        codon.getAminoAcid();
        System.out.println(codon.aminoAcid);
    }
    
    
}
