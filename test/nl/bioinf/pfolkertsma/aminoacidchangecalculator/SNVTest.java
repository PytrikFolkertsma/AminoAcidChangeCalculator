/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.pfolkertsma.aminoacidchangecalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author pfolkertsma
 */
public class SNVTest {

    public SNVTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createCodon method, of class SNV.
     */
    @Test
    public void testCreateCodon() {
        System.out.println("createCodon");

        SNV instance = new SNV();

        Chromosome chr = new Chromosome();
        chr.readFastaFile();
        chr.createChromosomeHashmap();
        CodontableGenerator.initializeTable();

        //Test with chromosome position 3 and mRNA position 3.
        instance.setChrPos(3);
        instance.setmRnaPos(3);
        instance.setChr("ST4.03ch01");
        String result = instance.createCodon(3).toString();
        String expResult = "AGT";
        assertEquals(expResult, result);

        //Test with chromosome position 3 and mRNA position 2.
        instance.setmRnaPos(2);
        result = instance.createCodon(2).toString();
        expResult = "GTC";
        assertEquals(expResult, result);

        //Test with chromosome position 3 and mRNA position 1.
        instance.setmRnaPos(1);
        result = instance.createCodon(1).toString();
        expResult = "TCC";
        assertEquals(expResult, result);
    }

    /**
     * Test of createSNPcodon method, of class SNV.
     */
    @Test
    public void testCreateSNPcodon() {
        System.out.println("createSNPcodon");

        SNV instance = new SNV();

        Chromosome chr = new Chromosome();
        chr.readFastaFile();
        chr.createChromosomeHashmap();
        CodontableGenerator.initializeTable();

        ///////////////////////////////////////////////////////////////////////
        //Test SNPs without insertions/deletions.                            //
        ///////////////////////////////////////////////////////////////////////
        //Test with:
        //chromosome position = 3
        //mRNA position = 3
        //reference = T
        //variants = C/T
        instance.setChrPos(3);
        instance.setmRnaPos(3);
        instance.setChr("ST4.03ch01");
        instance.setReference("T");
        String[] variants = {"C", "A"};
        instance.setVariants(variants);
        List result = instance.createSNPcodon(3);
        List<String> expResult = new ArrayList<>(Arrays.asList("AGC", "AGA"));
        assertEquals(expResult, result);

        //Test with:
        //chromosome position = 3
        //mRNA position = 2
        //reference = T
        //variants = A/C
        instance.setmRnaPos(2);
        String[] variants2 = {"A", "C"};
        instance.setVariants(variants2);
        result = instance.createSNPcodon(2);
        expResult = Arrays.asList("GAC", "GCC");
        assertEquals(expResult, result);

        //Test with:
        //chromosome position = 3
        //mRNA position = 1
        //reference = T
        //variants = A/G
        instance.setmRnaPos(1);
        String[] variants3 = {"A", "G"};
        instance.setVariants(variants3);
        result = instance.createSNPcodon(1);
        expResult = Arrays.asList("ACC", "GCC");
        assertEquals(expResult, result);

        ///////////////////////////////////////////////////////////////////////
        //Test SNPs that are insertions.                                     //
        ///////////////////////////////////////////////////////////////////////
        //Test with:
        //chromosome position = 3
        //mRNA position = 3
        //reference = -
        //variants = A/G
        instance.setmRnaPos(3);
        String[] variants4 = {"A", "G"};
        instance.setVariants(variants4);
        instance.setReference("-");
        result = instance.createSNPcodon(3);
        expResult = Arrays.asList("AGA", "AGG");
        assertEquals(expResult, result);

        //Test with:
        //chromosome position = 3
        //mRNA position = 2
        //reference = -
        //variants = A/G
        instance.setmRnaPos(2);
        String[] variants5 = {"A", "G"};
        instance.setVariants(variants5);
        instance.setReference("-");
        result = instance.createSNPcodon(2);
        expResult = Arrays.asList("GAT", "GGT");
        assertEquals(expResult, result);

        //Test with:
        //chromosome position = 3
        //mRNA position = 1
        //reference = -
        //variants = A/G
        instance.setmRnaPos(1);
        String[] variants6 = {"A", "G"};
        instance.setVariants(variants6);
        instance.setReference("-");
        result = instance.createSNPcodon(1);
        expResult = Arrays.asList("ATC", "GTC");
        assertEquals(expResult, result);

        ///////////////////////////////////////////////////////////////////////
        //Test SNPs that are deletions.                                      //
        ///////////////////////////////////////////////////////////////////////
        
        //Test with:
        //chromosome position = 3
        //mRNA position = 3
        //reference = T
        //variants = -
        instance.setmRnaPos(3);
        String[] variants7 = {"-"};
        instance.setVariants(variants7);
        instance.setReference("T");
        result = instance.createSNPcodon(3);
        expResult = Arrays.asList("AGC");
        assertEquals(expResult, result);
        
        //Test with:
        //chromosome position = 3
        //mRNA position = 2
        //reference = T
        //variants = -
        instance.setmRnaPos(2);
        String[] variants8 = {"-"};
        instance.setVariants(variants8);
        instance.setReference("T");
        result = instance.createSNPcodon(2);
        expResult = Arrays.asList("GCC");
        assertEquals(expResult, result);
        
        //Test with:
        //chromosome position = 3
        //mRNA position = 1
        //reference = T
        //variants = -
        instance.setmRnaPos(1);
        String[] variants9 = {"-"};
        instance.setVariants(variants9);
        instance.setReference("T");
        result = instance.createSNPcodon(1);
        expResult = Arrays.asList("CCA");
        assertEquals(expResult, result);

    }

}
