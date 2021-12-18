package paivakirja;

import java.io.PrintStream;
import java.util.Random;

/**
 * Linnun tiedot
 * @author Jyrki M‰ki
 * @version 22.2.2021
 */
public class Lintu {
    
    private int tunnusNro;
    private String laji = "";
    private String tieteellinenNimi = "";
    private String pituus = "";
    private String siipiv‰li = "";
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
        out.println("Siipiv‰li: " + siipiv‰li);
        out.println("Paino: " + paino);
        out.println("Uhanalaisuus: " + uhanalaisuus);
        out.println("");
    }
    
    /**
     * Apumetodi, jolla saadaan t‰ytetty‰ testiarvot linnuille.
     */
    public void vastaaLintu() {
        Random rand = new Random(); 
        laji = "Lintu" + rand.nextInt(1000);
        tieteellinenNimi = "A" + rand.nextInt(1000);
        pituus = "44-46";
        siipiv‰li = "52-60";
        paino = "160-280";
        uhanalaisuus = "Silm‰ll‰pidett‰v‰";
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
     * Asettaa tunnusnumeron ja samalla varmistaa ett‰
     * seuraava numero on aina suurempi kuin t‰h‰n menness‰ suurin;
     * @param nro asetettava tunnusnumero
     */
    public void setTunnusNro(int nro) {
        tunnusNro = nro;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;
    }
       
    /**
     * @param args ei k‰ytˆss‰
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
    
    @Override
    public String toString() {
        return getTunnusNro() + "|" +
        laji + "|" +
        tieteellinenNimi + "|" +
        pituus + "|" +
        siipiv‰li + "|" +
        paino + "|" +
        uhanalaisuus;
    }

    /**
     * Selvitt‰‰ linnun tiedot | erotellusta merkkijonosta
     * Pit‰‰ huolen, ett‰ seuraavaNro on suurempi kuin tuleva tunnusNro
     * @param rivi ,josta linnun tiedot otetaan
     * TODO try catch numberformatex, testit
     */
    public void parse(String rivi) {
        String[] palat = rivi.split("\\|");
        
        setTunnusNro(Integer.valueOf(palat[0]));
        laji = palat[1];
        tieteellinenNimi = palat[2];
        pituus = palat[3];
        siipiv‰li = palat[4];
        paino = palat[5];
        uhanalaisuus = palat[6];
    }

    /**
     * Palauttaa linnun kenttien lukum‰‰r‰n
     * @return kenttien lukum‰‰r‰
     */
	public int getKenttia() {
		return 7;
	}

	/**
	 * Eka kentt‰, joka on mielek‰s kysytt‰v‰ksi
	 * @return ekan kent‰n indeksi
	 */
	public int ekaKentta() {
		return 1;
	}

	public String getKysymys(int k) {
		switch (k) {
		case 0:
			return "Tunnus nro";
		case 1:
			return "laji";
		case 2:
			return "tieteellinen nimi";
		case 3:
			return "pituus";
		case 4:
			return "siipiv‰li";
		case 5:
			return "paino";
		case 6:
			return "uhanalaisuus";
		}
		return null;
	}

	public String anna(int k) {
		switch (k) {
		case 0:
			return "" + tunnusNro;
		case 1:
			return laji;
		case 2:
			return tieteellinenNimi;
		case 3:
			return pituus;
		case 4:
			return siipiv‰li;
		case 5:
			return paino;
		case 6:
			return uhanalaisuus;
		}
		return null;
	}

}
