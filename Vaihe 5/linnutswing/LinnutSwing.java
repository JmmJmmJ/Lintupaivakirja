package linnutswing;

import java.io.PrintStream;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import fi.jyu.mit.gui.StringTable;
import fi.jyu.mit.gui.TextAreaOutputStream;
import paivakirja.Havainto;
import paivakirja.Lintu;
import paivakirja.Paivakirja;
import paivakirja.SailoException;

/**
 * Luokka joka k‰sittelee lintup‰iv‰kirjaa Swing-komponenteilla.
 * @author Jyrki
 * @version 2.02.2021
 */
public class LinnutSwing {
   private JTable tableLinnut;
   private StringTable tableHavainnot;
   private JComponent panelLintu;
   private JLabel labelVirhe;
   private JTextField editHaku;
   
   private final Paivakirja paivakirja;
   private JTextArea areaLintu = new JTextArea(); // TODO tilap‰inen tulostusalue
   private Lintu lintuKohdalla;
   
   /**
    * Alustaa luokan niin, ett‰ se voi k‰ytt‰‰ Swing-komponentteja
    */
   public LinnutSwing() {
       paivakirja = new Paivakirja();
   }
   
    /**
     * Lis‰‰ linnun tai havainnon
     */
    public void lisaa() {
        Lintu lintu = new Lintu();
        lintu.vastaaLintu(); // TODO: poista t‰m‰, kun tietoja voi syˆtt‰‰
        lintu.rekisteroi();
        try {
            paivakirja.lisaa(lintu);
            PrintStream os = TextAreaOutputStream.getTextPrintStream(areaLintu);
            areaLintu.setText("");
            tulosta(os, lintu);
        } catch (SailoException e) {
            JOptionPane.showMessageDialog(null,"Ei voi lis‰t‰: " + e.getMessage());
        }
    }
    
    /**
     * Lis‰‰ linnun tai havainnon
     */
    public void lisaaHavainto() {
        Havainto hav = new Havainto();
        hav.vastaaHavainto(lintuKohdalla.getTunnusNro()); // TODO: poista t‰m‰, kun tietoja voi syˆtt‰‰
        hav.rekisteroi();
        paivakirja.lisaa(hav);
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
     * @return taulukko linnun tiedoista
     */
    public JTable getTableLinnut() {
        return tableLinnut;
    }

    /**
     * @param tableLinnut tiedot linnuista
     */
    public void setTableLinnut(JTable tableLinnut) {
        this.tableLinnut = tableLinnut;
    }

    /**
     * @return taulukko havainnoista
     */
    public StringTable getTableHavainnot() {
        return this.tableHavainnot;
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
        return this.panelLintu;
    }

    /**
     * @param panelLintu sis‰lt‰‰ kent‰t, joissa on lintujen tiedot
     */
    public void setPanelLintu(JComponent panelLintu) {
        this.panelLintu = panelLintu;
    }
    
    /**
      * N‰ytt‰‰ listasta valitun lajin tiedot
      */
     public void naytaLaji() {
        int ind = this.tableLinnut.getSelectedRow();
        if (ind < 0) return;
        this.lintuKohdalla = this.paivakirja.annaLintu(ind);
        laitaLaji();
    }    
     
     /**
      * N‰ytt‰‰ linnun tiedot lajin alueeseen
      */
     private void laitaLaji() {
         if (this.lintuKohdalla == null) return;
         this.areaLintu.setText("");
         PrintStream os = TextAreaOutputStream.getTextPrintStream(this.areaLintu);
         tulosta(os, this.lintuKohdalla);
     }

    /**
     * N‰ytt‰‰ virheen
     * @param virhe
     */
    public void setVirhe(String virhe) {
        this.labelVirhe.setVisible(true);
        this.labelVirhe.setText(virhe);        
    }

    /**
     * @return mihin n‰ytet‰‰n virhe teksti
     */
    public JLabel getLabelVirhe() {
        return this.labelVirhe;
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
        return this.editHaku;
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

    /**
     * Lukee p‰iv‰kirjan tiedostosta TODO
     */
    public void lueTiedostosta() {
        alusta();
    }

    /**
     * T‰m‰ alustaa valitut alueet k‰yttˆkuntoon
     */
    public void alusta() {
        luoNaytto();
        paivita();
        naytaLaji();
    }
    
    /**
     * P‰ivitt‰‰ taulukon linnuista
     * TODO haku
     * @param nro
     */
    protected void paivita() { // TODO taulukko model viitteen‰?
        
        DefaultTableModel model = (DefaultTableModel) this.tableLinnut.getModel();
        model.setRowCount(0);
        
        for (int i = 0; i < this.paivakirja.getLintuja(); i++) {
            Lintu lintu = this.paivakirja.annaLintu(i);
            model.addRow(new Object[]{lintu.getLaji(), lintu.getTieteellinenNimi(), "0"}); // TODO havaintojen m‰‰r‰ laskuri
            //model.fireTableDataChanged();
            //model.fireTableStructureChanged();
        }        
    }

    /**
     * Luo panelJasen:een paikan johon j‰sen tiedot voi tulostaa
     * TODO: vaihdettava editPanelien luomiseksi
     */
    private void luoNaytto() {
        this.panelLintu.removeAll();
        this.panelLintu.add(areaLintu);
    }
    
    /**
     * Tulostaa linnun tiedot
     * @param os tietovirta johon tulostetaan
     * @param lintu tulostettava lintu
     */
    public void tulosta(PrintStream os, final Lintu lintu) {
        os.println("----------------------------------------------");
        lintu.tulosta(os);
        os.println("----------------------------------------------");
        List<Havainto> havainnot = this.paivakirja.annaHavainnot(lintu);
        for ( Havainto hav: havainnot ) {
            hav.tulosta(os);
        }
    }
    
    /**
     * Tulostaa listassa olevat linnut tekstialueeseen
     * @param text alue johon tulostetaan
     */
    public void tulostaValitut(JTextArea text) {
        PrintStream os = TextAreaOutputStream.getTextPrintStream(text);
        os.println("Tulostetaan kaikki j‰senet");
        for (int i = 0; i < this.paivakirja.getLintuja(); i++) {
            Lintu lintu = this.paivakirja.annaLintu(i);
            tulosta(os, lintu);
            os.println("\n\n");
        }    
    }

    
}
