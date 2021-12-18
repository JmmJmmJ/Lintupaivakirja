package paivakirja;

import java.io.PrintStream;
import java.util.Random;

/**
 * Havainto, joka osaa mm. itse huolehtia tunnusNro:staan.
 * @author Jyrki M‰ki
 * @version 22.02.2021
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
     * Apumetodi, jolla saadaan t‰ytetty‰ testiarvot Havainnolle
     * Lis‰tiedot arvotaan, jotta kahdella havainolla ei olisi
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
     * @param tunnus havainnon id
     */
    public void setTunnusNro(int tunnus) {
    	tunnusNro = tunnus;
    }
    
    /**
     * Palautetaan mille linnulle havainto kuuluu
     * @return linnun id
     */
    public int getLintuNro() {
        return lintuNro;
    }
    
    /**
     * Asettaa mille linnulle havainto kuuluu
     * @param id linnun id
     */
    public void setLintuNro(int id) {
    	lintuNro = id;
    }
    
    /**
     * @return havainnon p‰iv‰m‰‰r‰
     */
    public String getPaivamaara() {
        return paivamaara;
    }
    
    /**
     * @return havainnon paikka
     */
    public String getPaikka() {
        return paikka;
    }
    
    /**
     * @return havainnon lisatiedot
     */
    public String getLisatiedot() {
        return tiedot;
    }
    
    /**
     * Selvitt‰‰ havainnon tiedot | erotellusta merkkijonosta
     * Pit‰‰ huolen, ett‰ seuraavaNro on suurempi kuin tuleva tunnusNro
     * @param rivi ,josta havainnon tiedot otetaan
     * TODO try catch numberformatex, testit
     */
    public void parse(String rivi) {
        String[] palat = rivi.split("\\|");
        
        setTunnusNro(Integer.valueOf(palat[0]));
        setLintuNro(Integer.valueOf(palat[1]));
        paivamaara = palat[2];
        paikka = palat[3];
        tiedot = palat[4];
    }
    
    @Override
    public String toString() {
        return tunnusNro + "|" +
                lintuNro + "|" +
                paivamaara + "|" +
                paikka + "|" +
                tiedot;
    }

    /**
     * Testi ohjelma Havainnolle.
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        Havainto hav = new Havainto();
        hav.vastaaHavainto(2);
        hav.tulosta(System.out);
    }


}
