package paivakirja;

import java.util.Iterator;
import java.util.List;

/**
 * P‰iv‰kirja-luokka, joka huolehtii lintupaivakirjasta.
 * @author Jyrki M‰ki
 * @version 22.02.2021
 */
public class Paivakirja {
    
    private LintuTietokanta linnut = new LintuTietokanta();
    private Havainnot havainnot = new Havainnot();

    
    /**
     * Palauttaa tietokannan sis‰lt‰mien lintu lajien m‰‰r‰n
     * @return laji m‰‰r‰
     */
    public int getLintuja() {
        return linnut.getLkm();
    }
    

    /**
     * Palauttaa tietokannan sis‰lt‰mien havaintojen m‰‰r‰n
     * @return havaintojen m‰‰r‰
     */
    public int getHavaintoja() {
        return havainnot.getLkm();
    }
    

    /**
     * TODO
     * Poistaa lintutietokannasta ja havainnoista ne joilla on nro. Kesken.
     * @param nro viitenumero, jonka mukaan poistetaan
     * @return montako poistettiin
     */
    public int poista(int nro) {
        return 0;
    }

    
    /**
     * Korvaa j‰senen tietorakenteeseen
     * @param lintu lis‰tt‰v‰n linnun viite
     * @throws SailoException jos tietorakenne on jo t‰ynn‰
     */
    public void korvaa(Lintu lintu) throws SailoException {
        linnut.korvaa(lintu);
    }
    

    /**
     * Lis‰‰ paivakirjaan uuden j‰senen
     * @param lintu lis‰tt‰v‰ laji
     * @throws SailoException jos lis‰yst‰ ei voida tehd‰
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * #PACKAGEIMPORT
     * Paivakirja paivakirja = new Paivakirja();
     * Lintu harakka = new Lintu(), varis = new Lintu();
     * harakka.rekisteroi(); varis.rekisteroi();
     * paivakirja.getLintuja() === 0;
     * paivakirja.lisaa(harakka); paivakirja.getLintuja() === 1;
     * paivakirja.lisaa(varis); paivakirja.getLintuja() === 2;
     * paivakirja.lisaa(harakka); paivakirja.getLintuja() === 3;
     * paivakirja.getLintuja() === 3;
     * paivakirja.annaLintu(0) === harakka;
     * paivakirja.annaLintu(1) === varis;
     * paivakirja.annaLintu(2) === harakka;
     * paivakirja.annaLintu(3) === harakka; #THROWS IndexOutOfBoundsException 
     * paivakirja.lisaa(harakka); paivakirja.getLintuja() === 4;
     * paivakirja.lisaa(harakka); paivakirja.getLintuja() === 5;
     * paivakirja.lisaa(harakka);
     * </pre>
     */
    public void lisaa(Lintu lintu) throws SailoException {
        linnut.lisaa(lintu);
    }
    

    /**
     * Lis‰‰ uusi havainto p‰iv‰kirjaan
     * @param hav
     */
    public void lisaa(Havainto hav) {
        havainnot.lisaa(hav);
    }
    

    /**
     * Haetaan kaikki linnun havainnot
     * @param lintu lintu, jonka havaintoja haetaan
     * @return tietorakenne, jossa viitteet lˆydettyihin havaintoihin
     */
    public List<Havainto> annaHavainnot(Lintu lintu) {
        return havainnot.annaHavainnot(lintu.getTunnusNro());
    }

    
    /**
     * Palauttaa i:n linnun
     * @param i monesko lintu palautetaan
     * @return viite i:teen lintuun
     * @throws IndexOutOfBoundsException jos i v‰‰rin
     */
    public Lintu annaLintu(int i) throws IndexOutOfBoundsException {
        return linnut.anna(i);
    }
    

    /**
     * Palautaa tunnusnumeroon liitetyn linnun
     * @param tunnus ,jolla haetaan
     * @return lintu
     */
    public Lintu etsi(int tunnus) {
        return linnut.etsi(tunnus);
    }
    

    /**
     * Tallettaa p‰iv‰kirjan tiedot tiedostoon
     * @throws SailoException jos tallentamisessa ongelmia
     */
    public void tallenna() throws SailoException {
        String virhe = "";
        try {
            linnut.tallenna();
        } catch (SailoException e) {
            virhe = e.getMessage();
        }

        try {
            havainnot.tallenna();
        } catch (SailoException e) {
            virhe += e.getMessage();
        }
        if (!"".equals(virhe))
            throw new SailoException(virhe);
    }
    

    /**
     * Palauttaa listan havainnoista
     * @return lista havainnoista
     */
    public List<Havainto> getHavainnot() {
        return havainnot.getHavainnot();
    }
    

    /**
     * Lukee p‰vi‰kirjan tiedot tiedostosta
     * @param nimi jota k‰ytet‰‰n lukemisessa
     * @throws SailoException ,jos lukeminen ep‰onnistuu
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        linnut = new LintuTietokanta();
        havainnot = new Havainnot();

        linnut.lueTiedostosta("linnut.dat");
        havainnot.lueTiedostosta(nimi);
    }
    

    /**
     * Kaikkien havaintojen iteraattori
     * @return havaintoiteraattori
     */
    public Iterator<Havainto> havIterator() {
        return havainnot.havIter();
    }


    /**
     * Asettaa, ett‰ lintujen tietoja on muutettu
     */
    public void setLintuMuutos() {
        linnut.setMuutos();
    }
    
    /**
     * Asettaa, ett‰ havaintoja on muutettu
     */
    public void setHavaintojaMuutettu() {
        havainnot.setMuutettu();
    }

    /**
     * Palauttaa onko lintutietokantaan tai havaintoihin tehty muutoksia
     * @return true, jos muutettu
     */
    public boolean getMuutos() {
        return (linnut.getMuutos() || havainnot.getMuutos());
    }
    
    
    /**
     * Testiohjelma p‰iv‰kirjalle
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        LintuTietokanta linnut = new LintuTietokanta();

        try {
            linnut.lueTiedostosta("linnut.dat");

            // Lintu harakka = new Lintu(), varis = new Lintu();
            // harakka.rekisteroi();
            // harakka.vastaaLintu();
            // varis.rekisteroi();
            // varis.vastaaLintu();

            // linnut.lisaa(harakka);
            // linnut.lisaa(varis);

            System.out.println("============= P‰iv‰kirja testi =================");

            for (int i = 0; i < linnut.getLkm(); i++) {
                Lintu lintu = linnut.anna(i);
                System.out.println("Lintu nro: " + i);
                lintu.tulosta(System.out);
            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
