package paivakirja;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * P�iv�kirjan linnut, joka osaa mm. lis�t� uuden linnun
 * @author Jyrki M�ki
 * @version 22.02.2021
 */
public class LintuTietokanta {
	private boolean muutettu = false;
    private static final int MAX_JASENIA = Integer.MAX_VALUE;
    private Lintu[] alkiot = new Lintu[5];
    private int lkm = 0; // aluksi 0
    private String tiedostonNimi = "";

    /**
     * Palauttaa viitteen i:teen lintu lajiin
     * @param i monennenko linnun viite halutaan
     * @return viite lintuun, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
     */
    public Lintu anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i) {
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        }
        return alkiot[i];
    }

    /**
     * Palauttaa p�iv�kirjan lintu lajien m��r�n
     * @return lajien m��r�
     */
    public int getLkm() {
        return lkm;
    }

    /**
     * Lis�� uuden linnun tietorakenteeseen.
     * @param lintu lis�tt�v�n linnun viite
     * @throws SailoException jos tietorakenne on t�ynn�
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * LintuTietokanta linnut = new LintuTietokanta();
     * Lintu harakka = new Lintu();
     * Lintu varis = new Lintu();
     * linnut.getLkm() === 0;
     * linnut.lisaa(harakka); linnut.getLkm() === 1;
     * linnut.lisaa(varis); linnut.getLkm() === 2;
     * linnut.lisaa(harakka); linnut.getLkm() === 3;
     * linnut.anna(0) === harakka;
     * linnut.anna(1) === varis;
     * linnut.anna(2) === harakka;
     * linnut.anna(3) === harakka;#THROWS IndexOutOfBoundsException
     * linnut.lisaa(harakka); linnut.getLkm() === 4;
     * linnut.lisaa(varis); linnut.getLkm() === 5;
     * linnut.lisaa(harakka);
     * </pre>
     */
    public void lisaa(Lintu lintu) throws SailoException {
        if (lkm >= MAX_JASENIA) throw new SailoException("Liikaa alkioita");
        if (lkm >= alkiot.length) {
            alkiot = Arrays.copyOf(alkiot, lkm+20);
        }
        alkiot[lkm] = lintu;
        lkm++;
        muutettu = true;
    }

    /**
     * Tallentaa lintujen tiedot tiedostoon.  Kesken. TODO
     * @throws SailoException jos tallennus ep�onnistuu
     */
    public void tallenna() {
    	if (!muutettu) return;

        try (PrintStream fo = new PrintStream(new FileOutputStream(getTiedostonNimi(), false), false, StandardCharsets.UTF_8)) {
            for (int i = 0; i < this.getLkm(); i++) {
                Lintu lintu = this.anna(i);
                fo.println(lintu.toString());
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedosto ei aukea: " + ex.getMessage());
        }
        
        muutettu = false;
    }

    /**
     * Asettaa tiedoston nimen
     * @param tied tallennustiedoston perusnimi
     */
    public void setTiedostonPerusNimi(String tied) {
        tiedostonNimi = tied;
    }

    /**
     * Palauttaa tiedoston nimen, jota k�ytet��n tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return tiedostonNimi;
    }

    /**
     * Etsii linnun tunnusnumeron perusteella
     * @param tunnus
     * @return lintu
     */
    public Lintu etsi(int tunnus) {
        for (Lintu lintu : alkiot) {
            if (lintu.getTunnusNro() == tunnus) {
                return lintu;
            }
        }
        return null;
    }

    /**
     * Lukee lintutietokannan tiedostosta.
     * @param tied tiedoston nimi
     * @throws SailoException
     * TODO testit
     */
    public void lueTiedostosta(String tied) throws SailoException {
        setTiedostonPerusNimi(tied);

        try (BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi(), StandardCharsets.UTF_8))) {
            String rivi;

            while ((rivi = fi.readLine()) != null) {
                rivi = rivi.trim();
                if ("".equals(rivi) || rivi.charAt(0) == ';') continue;
                Lintu lintu = new Lintu();
                lintu.parse(rivi);
                lisaa(lintu);
                }            
        } catch (FileNotFoundException e) {
            System.err.println("Tiedosto ei aukea! " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Virhe tiedostoa luettaessa! " + e.getMessage());
        }

    }
    
    
    /**
     * @param args ei k�yt�ss�
     */
    public static void main(String[] args) {
        LintuTietokanta linnut = new LintuTietokanta();

        Lintu harakka = new Lintu();
        Lintu varis = new Lintu();
        harakka.rekisteroi();
        harakka.vastaaLintu();
        varis.rekisteroi();
        varis.vastaaLintu();

        try {
            linnut.lisaa(harakka);
            linnut.lisaa(varis);

            System.out.println("===== LinnutTietokanta testi =====");

            for (int i = 0; i < linnut.getLkm(); i++) {
                Lintu lintu = linnut.anna(i);
                lintu.tulosta(System.out);
            }

        } catch (SailoException e) {
            System.out.println(e.getMessage());
        }

    }

}
