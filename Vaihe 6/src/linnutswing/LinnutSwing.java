package linnutswing;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import fi.jyu.mit.gui.EditPanel;
import fi.jyu.mit.gui.TextAreaOutputStream;
import paivakirja.Havainto;
import paivakirja.Lintu;
import paivakirja.Paivakirja;
import paivakirja.SailoException;

/**
 * Luokka joka k‰sittelee lintup‰iv‰kirjaa Swing-komponenteilla.
 * @author Jyrki M‰ki
 * @version 22.02.2021
 */
public class LinnutSwing {
   private JTable tableLinnut;
   private JTable tableHavainnot;
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
        paivakirja.tallenna();
        JOptionPane.showMessageDialog(null, "Tallentaminen");               
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
    public JTable getTableHavainnot() {
        return this.tableHavainnot;
    }

    /**
     * @param tableHavainnot taulukko havainnoista
     */
    public void setTableHavainnot(JTable tableHavainnot) {
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
    	 for (int i = 0, k = lintuKohdalla.ekaKentta(); k < lintuKohdalla.getKenttia(); k++, i++) {
    		 editLintuKentta[i].setText(lintuKohdalla.anna(k));
    		 editLintuKentta[i].setToolTipText("");
    	 }
    	 
    	 // TODO: tilap‰inen
         //if (this.lintuKohdalla == null) return;
         //this.areaLintu.setText("");
         //PrintStream os = TextAreaOutputStream.getTextPrintStream(this.areaLintu);
         //tulosta(os, this.lintuKohdalla);
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
     * Lukee p‰iv‰kirjan tiedostosta
     * @param s tiedoston nimi
     * @return null jos onnistuu, muuten virheilmoitu
     */
    public String lueTiedostosta(String s) {
        alusta();
        try {
            paivakirja.lueTiedostosta(s);
            paivita();
            return null;
        } catch (SailoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return e.getMessage();
        }
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
        
        DefaultTableModel modelLinnut = (DefaultTableModel) this.tableLinnut.getModel();
        modelLinnut.setRowCount(0);
        
        for (int i = 0; i < this.paivakirja.getLintuja(); i++) {
            Lintu lintu = this.paivakirja.annaLintu(i);
            modelLinnut.addRow(new Object[]{lintu.getLaji(), lintu.getTieteellinenNimi(), "0"}); // TODO havaintojen m‰‰r‰ laskuri
            //model.fireTableDataChanged();
            //model.fireTableStructureChanged();
        }
        naytaKaikkiHavainnot();

    }
    
    /**
     * Laittaa valitun linnun havainnot taulukkoon
     */
    public void naytaHavainto() {
        int ind = this.tableLinnut.getSelectedRow();
        if (ind < 0) return;
        this.lintuKohdalla = this.paivakirja.annaLintu(ind);
        
        DefaultTableModel modelHavainnot = (DefaultTableModel) this.tableHavainnot.getModel();
        modelHavainnot.setRowCount(0);        
        
        List<Havainto> havainnot = paivakirja.annaHavainnot(this.lintuKohdalla);
        
        for (Havainto hav: havainnot) {
            modelHavainnot.addRow(new Object[]{paivakirja.etsi(hav.getLintuNro()).getLaji(), hav.getPaivamaara(), hav.getPaikka(), hav.getLisatiedot()});
        }
    }
    
    /**
     * Laittaa kaikki havainnot taulukkoon
     */
    public void naytaKaikkiHavainnot() {
        DefaultTableModel modelHavainnot = (DefaultTableModel) this.tableHavainnot.getModel();
        modelHavainnot.setRowCount(0);        
        
        Iterator<Havainto> it = paivakirja.havIterator();
        while (it.hasNext()) {
            Havainto hav = it.next(); //TODO: tiedot havaintoihin korjaa
            modelHavainnot.addRow(new Object[]{paivakirja.etsi(hav.getLintuNro()).getLaji(), hav.getPaivamaara(), hav.getPaikka(), hav.getLisatiedot()});
        }      
    }
    
    private EditPanel[] editLintuKentta;
    private Lintu apulintu = new Lintu();

    /**
     * Luo panelJasen:een paikan johon j‰sen tiedot voi tulostaa
     */
    private void luoNaytto() {
        this.panelLintu.removeAll();
        int n = apulintu.getKenttia() - apulintu.ekaKentta();
        editLintuKentta = new EditPanel[n];
        
        for (int i = 0, k = apulintu.ekaKentta(); k < apulintu.getKenttia(); k++, i++) {
        	EditPanel edit = new EditPanel();
        	String otsikko = apulintu.getKysymys(k);
        	edit.setCaption(otsikko);
        	editLintuKentta[i] = edit;
        	panelLintu.add(edit);
        }
        //tableHavainnot.getParent().add(areaLintu); TODO poista
        //this.panelLintu.add(areaLintu);
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
