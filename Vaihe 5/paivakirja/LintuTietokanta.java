package paivakirja;

/**
 * Päiväkirjan linnut, joka osaa mm. lisätä uuden linnun
 * @author Jyrki
 * @version 23.1.2021
 */
public class LintuTietokanta {
    
    private static final int MAX_JASENIA = 5; // alkuarvaus koolle
    private Lintu[] alkiot = new Lintu[MAX_JASENIA];
    private int lkm = 0; // aluksi 0
    private String tiedostonNimi = "";
    
    /**
     * Palauttaa viitteen i:teen lintu lajiin
     * @param i monennenko linnun viite halutaan
     * @return viite lintuun, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
     */
    public Lintu anna(int i) throws IndexOutOfBoundsException {
        if ( i < 0 || lkm <= i) {
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        }
        return alkiot[i];
    }

    /**
     * Palauttaa päiväkirjan lintu lajien määrän
     * @return lajien määrä
     */
    public int getLkm() {
        return lkm;
    }

    /**
     * Lisää uuden linnun tietorakenteeseen.
     * @param lintu lisättävän linnun viite
     * @throws SailoException jos tietorakenne on täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * LinnutTietokanta linnut = new LinnutTietokanta();
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
     * linnut.lisaa(harakka);#THROWS SailoException
     * </pre>
     */
    public void lisaa(Lintu lintu) throws SailoException {
        if ( lkm >= alkiot.length) {
            throw new SailoException("Liikaa Alkioita"); 
        }
        alkiot[lkm] = lintu;
        lkm++;
    }
    
    /**
     * Tallentaa lintujen tiedot tiedostoon.  Kesken. TODO
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedostonNimi);
    }

    /**
     * @param args ei käytössä
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
        
        for ( int i = 0; i < linnut.getLkm(); i++) {
            Lintu lintu = linnut.anna(i);
            lintu.tulosta(System.out);
        }
        
        } catch (SailoException e) {
            System.out.println(e.getMessage());
        }

    }

}
