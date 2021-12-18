package linnutswing;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fi.jyu.mit.gui.StringTable;

/**
 * Luokka joka k‰sittelee lintup‰iv‰kirjaa Swing-komponenteilla.
 * @author Jyrki M‰ki
 * @version 22.1.2021
 */
public class LinnutSwing {
   private StringTable tableLinnut;
   private StringTable tableHavainnot;
   private JComponent panelLintu;
   private JLabel labelVirhe;
   private JTextField editHaku;

    /**
     * Lis‰‰ linnun tai havainnon
     */
    public void lisaa() {
        JOptionPane.showMessageDialog(null, "Lis‰‰minen ei toimi");       
    }    

    /**
     * Poistaa linnun tai havainnon
     */
    public void poista() {
        JOptionPane.showConfirmDialog(
                null,
                "Haluatko varmasti poistaa?",
                "Poistetaanko?",
                JOptionPane.YES_NO_OPTION);
        JOptionPane.showMessageDialog(null, "Ei toimi");

    }

    /**
     * Tallentaa muutokset
     */
    public void tallenna() {
        JOptionPane.showMessageDialog(null, "Tallentaminen ei toimi");               
    }

    /**
     * @return tiedot linnuista
     */
    public StringTable getTableLinnut() {
        return tableLinnut;
    }

    /**
     * @param tableLinnut tiedot linnuista
     */
    public void setTableLinnut(StringTable tableLinnut) {
        this.tableLinnut = tableLinnut;
    }

    /**
     * @return taulukko havainnoista
     */
    public StringTable getTableHavainnot() {
        return tableHavainnot;
    }

    /**
     * @param tableHavainnot taulukko havainnoista
     */
    public void setTableHavainnot(StringTable tableHavainnot) {
        this.tableHavainnot = tableHavainnot;
    }

    /**
     * @return sis‰lt‰‰ kent‰t, joissa on lintujen tiedot
     */
    public JComponent getPanelLintu() {
        return panelLintu;
    }

    /**
     * @param panelLintu sis‰lt‰‰ kent‰t, joissa on lintujen tiedot
     */
    public void setPanelLintu(JComponent panelLintu) {
        this.panelLintu = panelLintu;
    }
    
    /**
      * N‰ytt‰‰ listasta valitun j‰senen tiedot
      */
     public void naytaJasen() {
        int ind = tableLinnut.getSelectedRow();
        if (ind < 0) return;
        if ( ind > 0 ) setVirhe("Ei osata n‰ytt‰‰ muuta");
        //else setVirhe(null);
    }
     
    /**
     * N‰ytt‰‰ virheen
     * @param virhe
     */
    private void setVirhe(String virhe) {
        labelVirhe.setVisible(true);
        labelVirhe.setText(virhe);        
    }

    /**
     * @return mihin n‰ytet‰‰n virhe teksti
     */
    public JLabel getLabelVirhe() {
        return labelVirhe;
    }

    /**
     * @param labelVirhe mihin n‰ytet‰‰n virhe teksti
     */
    public void setLabelVirhe(JLabel labelVirhe) {
        this.labelVirhe = labelVirhe;
    }

    /**
     * Hakee linnuista
     */
    public void hae() {
        setVirhe("Hakeminen ei toimi");      
    }

    /**
     * @return textfield jossa haettava merkkijono
     */
    public JTextField getEditHaku() {
        return editHaku;
    }

    /**
     * @param editHaku haettava merkkijono
     */
    public void setEditHaku(JTextField editHaku) {
        this.editHaku = editHaku;
    }

    /**
     * N‰ytt‰‰ ohjeen
     */
    public void ohje() {
        JOptionPane.showMessageDialog(null, "Ohjetta ei ole viel‰ toteutettu");
    }

    
}
