package paivakirja;

import java.io.PrintStream;
import java.util.Random;

/**
 * Linnun tiedot
 * @author Jyrki
 * @version 22.1.2021
 */
public class Lintu {
    
    private int tunnusNro;
    private String laji = "";
    private String tieteellinenNimi = "";
    private String pituus = "";
    private String siipiväli = "";
    private String paino = "";
    private String uhanalaisuus = "";
    
    private static int seuraavaNro = 1;
       
    /**
     * Tulostetaan linnun tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNro, 3) + " Laji: " + laji);
        out.println("Tieteellinen nimi: " + tieteellinenNimi);
        out.println("Pituus: " + pituus);
        out.println("Siipiväli: " + siipiväli);
        out.println("Paino: " + paino);
        out.println("Uhanalaisuus: " + uhanalaisuus);
        out.println("");
    }
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot linnuille.
     */
    public void vastaaLintu() {
        Random rand = new Random(); 
        laji = "Lintu" + rand.nextInt(1000);
        tieteellinenNimi = "A" + rand.nextInt(1000);
        pituus = "44-46";
        siipiväli = "52-60";
        paino = "160-280";
        uhanalaisuus = "Silmälläpidettävä";
    }
    
    /**
     * Antaa linnulle seuraavan tunnus numeron
     * @return linnun uusi tunnusNro;
     * @example
     * <pre name="test">
     * Lintu harakka = new Lintu();
     * harakka.getTunnusNro() === 0;
     * harakka.rekisteroi();
     * Lintu varis = new Lintu();
     * varis.rekisteroi();
     * int n1 = harakka.getTunnusNro();
     * int n2 = varis.getTunnusNro();
     * n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    
    /**
     * @return linnun tunnusnumero
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
       
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Lintu harakka = new Lintu();
        Lintu varis = new Lintu();
        harakka.rekisteroi();
        varis.rekisteroi();
        harakka.tulosta(System.out);
        harakka.vastaaLintu();
        harakka.tulosta(System.out);
        varis.vastaaLintu();
        varis.tulosta(System.out);
    }

    /**
     * @return laji
     */
    public String getLaji() {
        return laji;
    }

    /**
     * @return Tieteellinen nimi
     */
    public Object getTieteellinenNimi() {
        return tieteellinenNimi;
    }

}
