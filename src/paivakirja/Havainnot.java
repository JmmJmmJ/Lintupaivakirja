package paivakirja;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Päiväkirjan havainnot, joka osaa mm. lisätä uuden havainnon
 * @author Jyrki Mäki
 * @version 22.02.2021
 */
public class Havainnot {
    
    private String tiedostonNimi = "";
    private boolean muutettu = false;

    
    /** Taulukko havainnoista */
    private final Collection<Havainto> alkiot = new ArrayList<Havainto>();

    
    /**
     * Lisää uuden havainnon tietorakenteeseen.
     * @param hav lisättävä havainto.
     */
    public void lisaa(Havainto hav) {
        alkiot.add(hav);
        muutettu = true;
    }
    

    /**
     * Palauttaa kaikki havainnot
     * @return lista havainnoista
     */
    public List<Havainto> getHavainnot() {
        return (List<Havainto>) alkiot;
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
     * Asettaa tiedoston nimen
     * @param tied tallennustiedoston perusnimi
     */
    public void setTiedostonPerusNimi(String tied) {
        tiedostonNimi = tied;
    }
    

    /**
     * @return havaintojen lukumäärä
     */
    public int getLkm() {
        return alkiot.size();
    }
    

    /**
     * Iteraattori kaikkien havaintojen läpikäymiseen
     * @return havaintoitenaattori
     */
    public Iterator<Havainto> havIter() {
        return alkiot.iterator();
    }
    

    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return tiedostonNimi;
    }
    

    /**
     * Lukee lintutietokannan tiedostosta.
     * @param tied tiedoston nimi
     * @throws SailoException
     */
    public void lueTiedostosta(String tied) throws SailoException {
        setTiedostonPerusNimi(tied);

        try (BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi(), StandardCharsets.UTF_8))) {
            String rivi;

            while ((rivi = fi.readLine()) != null) {
                rivi = rivi.trim();
                Havainto hav = new Havainto();
                hav.parse(rivi);
                lisaa(hav);
            }
            muutettu = false;
        } catch (FileNotFoundException e) {
            throw new SailoException("Tiedosto ei aukea: " + e.getMessage());
        } catch (IOException e) {
            throw new SailoException("Tiedosto ei aukea: " + e.getMessage());
        }
    }
    

    /**
     * Tallentaa havainnot tiedostoon.
     * @throws SailoException jos tallennus epäonnistuu
     */
    public void tallenna() throws SailoException {
        try (PrintStream fo = new PrintStream(new FileOutputStream(getTiedostonNimi(), false), false,
                StandardCharsets.UTF_8)) {
            for (Havainto hav : alkiot) {
                fo.println(hav.toString());
            }
        } catch (FileNotFoundException ex) {
            throw new SailoException("Tiedosto ei aukea: " + ex.getMessage());
        }
        muutettu = false;
    }
    
    
    /**
     * Palauttaa onko havaintoja muutettu
     * @return true, jos muutettu
     */
    public boolean getMuutos() {
        return muutettu;
    }
    
    
    /**
     * Asettaa, että havaintoja on muutettu
     */
    public void setMuutettu() {
        muutettu = true;
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
