package wbLinnut;

import javax.swing.JOptionPane;

/**
 * 
 *
 * @author Jyrki
 * @version 16.12.2020
 */
public class test {

    /**
     * 
     * @param args
     * @wbp.parser.entryPoint
     */
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Tiedostoa ei löydy");
        
        //JOptionPane.showMessageDialog(null, "Tiedostoa ei löydy");
        //String name = JOptionPane.showInputDialog(null,
        //        "Anna tiedoston nimi",
        //        "Luo uusi havainnot tiedosto",
        //        JOptionPane.INFORMATION_MESSAGE);
        //JOptionPane.showMessageDialog(null,"Ok Done");
        JOptionPane.showConfirmDialog(
                null,
                "Luodaanko tiedosto?",
                "Tiedostoa ei löydy",
                JOptionPane.YES_NO_OPTION);
        
        int a = JOptionPane.showConfirmDialog(
                null,
                "Haluatko varmasti poistaa?",
                "Poistetaanko?",
                JOptionPane.YES_NO_OPTION);
        
    }
    }


