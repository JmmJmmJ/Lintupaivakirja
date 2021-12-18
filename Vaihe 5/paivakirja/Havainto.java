package paivakirja;

import java.io.PrintStream;
import java.util.Random;

/**
 * Havainto, joka osaa mm. itse huolehtia tunnusNro:staan.
 * @author Jyrki
 * @version 2.02.2021
 */
public class Havainto {
    private int tunnusNro;
    private int lintuNro;
    private String paivamaara;
    private String paikka;
    private String tiedot;
    
    private static int seuraavaNro = 1;
    
    /**
     * Alustetaan havainto.
     */
    public Havainto() {
    }
    
    /**
     * Alustetaan tietyn linnun havainto.
     * @param lintuNro linnun viitenumero
     */
    public Havainto(int lintuNro) {
        this.lintuNro = lintuNro;
    }
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot Havainnolle
     * Lisätiedot arvotaan, jotta kahdella havainolla ei olisi
     * samoja tietoja
     * @param nro viite lintuun, jonka havainnosta on kyse
     */
    public void vastaaHavainto(int nro) {
        lintuNro = nro;
        Random rand = new Random(); 
        paivamaara = "10.01.2021";
        paikka = "Lapua";
        tiedot = "A" + rand.nextInt(1000);
    }
    
    /**
     * Tulostetaan havainnot tiedot
     * @param out tietovirta, johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(paivamaara + " " + paikka + " " + tiedot);
    }
    
    /**
     * Antaa havainnolle seuraavan rekisterinumeron
     * @return havainnon uusi tunnusNro
     * @example
     * <pre name="test">
     * Havainto hav = new Havainto();
     * hav.getTunnusNro() === 0;
     * hav.rekisteroi();
     * Havainto hav2 = new Havainto();
     * hav2.rekisteroi();
     * int n1 = hav.getTunnusNro();
     * int n2 = hav2.getTunnusNro();
     * n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    
    /**
     * Palauttaa havainnon oma id
     * @return havainnon id
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
    
    /**
     * Palautetaan mille linnulle havainto kuuluu
     * @return havainnon id
     */
    public int getLintuNro() {
        return lintuNro;
    }

    /**
     * Testi ohjelma Havainnolle.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Havainto hav = new Havainto();
        hav.vastaaHavainto(2);
        hav.tulosta(System.out);
    }


}
