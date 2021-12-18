package paivakirja;

/**
 * @author Jyrki
 * @version 25.1.2021
 */
public class SailoException extends Exception {

    private static final long serialVersionUID = 1L;
    
    /**
     * Poikkeuksen muodostaja, jolle tuodaan poikkeuksessa
     * k�ytett�v� viesti
     * @param viesti Poikkeuksen viesti
     */
    public SailoException (String viesti) {
        super(viesti);
    }

}
