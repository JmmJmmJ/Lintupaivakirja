package paivakirja;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Päiväkirjan havainnot, joka osaa mm. lisätä uuden havainnon
 * @author Jyrki
 * @version 2.02.2021
 */
public class Havainnot {
    
    /** Taulukko havainnoista */
    private final Collection<Havainto> alkiot = new ArrayList<Havainto>();
    
    /**
     * Lisää uuden havainnon tietorakenteeseen.
     * @param hav lisättävä havainto.
     */
    public void lisaa(Havainto hav) {
        alkiot.add(hav);
    }
    
    /**
     * Haetaan kaikki lintulajin havainnot.
     * @param tunnusnro linnun tunnusnumero, jolle havaintoja haetaan
     * @return tietorakenne, jossa viitteet löydettyihin havaintoihin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  Havainnot havainnot = new Havainnot();
     *  Havainto hav1 = new Havainto(2); havainnot.lisaa(hav1);
     *  Havainto hav2 = new Havainto(1); havainnot.lisaa(hav2);
     *  Havainto hav3 = new Havainto(2); havainnot.lisaa(hav3);
     *
     *  List<Havainto> loytyneet;
     *  loytyneet = havainnot.annaHavainnot(3);
     *  loytyneet.size() === 0;
     *  loytyneet = havainnot.annaHavainnot(2);
     *  loytyneet.size() === 2;
     *  loytyneet.get(0) == hav1 === true;
     *  loytyneet.get(1) == hav3 === true;
     * </pre>
     */
    public List<Havainto> annaHavainnot(int tunnusnro) {
        List<Havainto> loydetyt = new ArrayList<Havainto>();
        for (Havainto hav : alkiot) {
            if (hav.getLintuNro() == tunnusnro) {
                loydetyt.add(hav);
            }   
        }
        return loydetyt;
    }


    /**
     * Testiohjelma havainnoille
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Havainnot havainnot = new Havainnot();
        Havainto hav1 = new Havainto();
        hav1.vastaaHavainto(2);
        Havainto hav2 = new Havainto();
        hav2.vastaaHavainto(1);        
        Havainto hav3 = new Havainto();
        hav3.vastaaHavainto(2);        
        Havainto hav4 = new Havainto();
        hav4.vastaaHavainto(2);
        
        havainnot.lisaa(hav1);
        havainnot.lisaa(hav2);
        havainnot.lisaa(hav3);
        havainnot.lisaa(hav4);
        
        System.out.println("==== Havainnot testi ====");
        
        List<Havainto> havainnot2 = havainnot.annaHavainnot(2);
        
        for (Havainto hav : havainnot2) {
            System.out.println(hav.getTunnusNro() + " ");
            hav.tulosta(System.out);
        }

    }


}
