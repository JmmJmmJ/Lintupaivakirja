package linnutswing;

import java.awt.Desktop;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
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
import javax.swing.table.TableModel;

import fi.jyu.mit.gui.EditPanel;
import fi.jyu.mit.gui.TextAreaOutputStream;
import paivakirja.Havainto;
import paivakirja.Lintu;
import paivakirja.Paivakirja;
import paivakirja.SailoException;
import wbLinnut.LintuTaulukkoModel;

/**
 * Luokka joka k‰sittelee lintup‰iv‰kirjaa Swing-komponenteilla.
 * @author Jyrki M‰ki
 * @version 26.02.2021
 */
public class LinnutSwing {
    
    private JTable tableLinnut;
    private JTable tableHavainnot;
    private JComponent panelLintu;
    private JLabel labelVirhe;
    private JTextField editHaku;
    private LintuTaulukkoModel lintuTaulukkoModel;
    
    private EditPanel[] editLintuKentta;
    private Lintu apulintu = new Lintu();
    private KeyReleased keyReleased = new KeyReleased();
    private boolean tietojaMuokattu = false;
    private Lintu lintuBak;

    private final Paivakirja paivakirja;
    private JTextArea areaLintu = new JTextArea(); //tilap‰inen tulostusalue
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
        lintu.vastaaLintu(); 
        lintu.rekisteroi();
        try {
            paivakirja.lisaa(lintu);
            PrintStream os = TextAreaOutputStream.getTextPrintStream(areaLintu);
            areaLintu.setText("");
            tulosta(os, lintu);
        } catch (SailoException e) {
            JOptionPane.showMessageDialog(null, "Ei voi lis‰t‰: " + e.getMessage());
        }
    }
    

    /**
     * Lis‰‰ linnun tai havainnon
     */
    public void lisaaHavainto() {
        Havainto hav = new Havainto();
        hav.vastaaHavainto(lintuKohdalla.getTunnusNro());
        hav.rekisteroi();
        paivakirja.lisaa(hav);
    }
    

    /**
     * Poistaa linnun tai havainnon
     */
    public void poista() {
        JOptionPane.showConfirmDialog(null, "Haluatko varmasti poistaa?", "Poistetaanko?", JOptionPane.YES_NO_OPTION);
        JOptionPane.showMessageDialog(null, "Ei toimi");
    }
    

    /**
     * Tallentaa muutokset
     */
    public void tallenna() {
        try {
            paivakirja.tallenna();
            paivita();
        } catch (SailoException e) {
            JOptionPane.showMessageDialog(null, "Talletuksessa ongelmia! " + e.getMessage());
        }
        tietojaMuokattu = false;
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
     * Palauttaa onko lintutietokantaan tehty muutoksia
     * @return true, jos muutettu
     */
    public boolean getMuutos() {
        return paivakirja.getMuutos();
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
     * Asettaa editoitavan linnun
     * @param l uusi viite kohdalla olevalle linnulle
     */
    public void setLintuKohdalla(Lintu l) {
        this.lintuKohdalla = l;
    }
    

    /**
      * N‰ytt‰‰ listasta valitun lajin tiedot
      */
    public void naytaLaji() {
        int ind = this.tableLinnut.getSelectedRow();
        if (ind < 0)
            return;
        int i = tableLinnut.convertRowIndexToModel(ind);
        setLintuKohdalla(this.paivakirja.annaLintu(i));
        laitaLaji();
    }
    

    /**
     * N‰ytt‰‰ linnun tiedot lajin alueeseen
     */
    private void laitaLaji() {
        if (this.lintuKohdalla == null)
            return;
        for (int i = 0, k = lintuKohdalla.ekaKentta(); k < lintuKohdalla.getKenttia(); k++, i++) {
            editLintuKentta[i].setText(lintuKohdalla.anna(k));
            editLintuKentta[i].setToolTipText("");
        }
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
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            File ohje = new File("suunnitelma.pdf");
            try {
                desktop.open(ohje);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Virhe: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Virhe ohjeen avaamisessa");
        }
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
     * @param nro
     */
    protected void paivita() {
        lintuTaulukkoModel.clear();

        for (int i = 0; i < this.paivakirja.getLintuja(); i++) {
            Lintu lintu = this.paivakirja.annaLintu(i);
            lintuTaulukkoModel.addRow(new Object[] { lintu.getLaji(), lintu.getTieteellinenNimi(),
                    "" + this.paivakirja.annaHavainnot(lintu).size() });
        }
        naytaKaikkiHavainnot();
    }
    

    /**
     * Laittaa valitun linnun havainnot taulukkoon
     */
    public void naytaHavainto() {
        int ind = this.tableLinnut.getSelectedRow();
        if (ind < 0)
            return;
        int i = tableLinnut.convertRowIndexToModel(ind);
        setLintuKohdalla(this.paivakirja.annaLintu(i));

        DefaultTableModel modelHavainnot = (DefaultTableModel) this.tableHavainnot.getModel();
        modelHavainnot.setRowCount(0);
        List<Havainto> havainnot = paivakirja.annaHavainnot(this.lintuKohdalla);

        for (Havainto hav : havainnot) {
            modelHavainnot.addRow(new Object[] { paivakirja.etsi(hav.getLintuNro()).getLaji(),
                    paivakirja.etsi(hav.getLintuNro()).getTieteellinenNimi(), hav.getPaivamaara(), hav.getPaikka(),
                    hav.getLisatiedot() });
        }
    }
    

    /**
     * P‰ivitt‰‰ editoidun havainnon paivakirjaan. Kaikki havainnot.
     */
    public void editHavainnotTable() {
        TableModel t = tableHavainnot.getModel();
        int row = tableHavainnot.getSelectedRow();
        if (row >= 0) {

            if (t.getRowCount() > 0) {
                String jono = paivakirja.getHavainnot().get(row).getTunnusNro() + "|"
                        + paivakirja.getHavainnot().get(row).getLintuNro() + "|" + t.getValueAt(row, 2) + "|"
                        + t.getValueAt(row, 3) + "|" + t.getValueAt(row, 4);

                paivakirja.getHavainnot().get(row).parse(jono);
                paivakirja.setHavaintojaMuutettu();
            }
        }
    }
    

    /**
     * P‰ivitt‰‰ editoidun havainnon paivakirjaan, valitun linnun tiedot.
     */
    public void editHavainnotTableValittu() {
        TableModel t = tableHavainnot.getModel();
        int row = tableHavainnot.getSelectedRow();
        
        if (row >= 0) {

            if (t.getRowCount() > 0) {
                String jono = paivakirja.annaHavainnot(this.lintuKohdalla).get(row).getTunnusNro() + "|"
                        + paivakirja.annaHavainnot(this.lintuKohdalla).get(row).getLintuNro() + "|"
                        + t.getValueAt(row, 2) + "|" + t.getValueAt(row, 3) + "|" + t.getValueAt(row, 4);
                //ohjeissa oli, ett‰ saisi parsea vain tiedoston luvussa. Vaatisi varmaan tablemodelin tekemisen, ett‰ saisi olion havainnot
                //taulukosta
                paivakirja.getHavainnot().get(row).parse(jono);
                paivakirja.setHavaintojaMuutettu();
            }
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
            Havainto hav = it.next();
            modelHavainnot.addRow(new Object[] { paivakirja.etsi(hav.getLintuNro()).getLaji(),
                    paivakirja.etsi(hav.getLintuNro()).getTieteellinenNimi(), hav.getPaivamaara(), hav.getPaikka(),
                    hav.getLisatiedot() });
        }
    }
    

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
            edit.setName("el" + k);
            edit.getEdit().setName("tl" + k);
            panelLintu.add(edit);
            edit.addKeyListener(keyReleased);
        }
    }
    

    /**
     * K‰sittelee lintuun tulleet muutokset
     * @param edit ,jossa muutos tapahtui
     */
    private void kasitteleMuutosLintuun(JTextField edit) {
        String s = edit.getText();
        int k = Integer.parseInt(edit.getName().substring(2));
        String virhe = lintuKohdalla.aseta(k, s);
        paivakirja.setLintuMuutos();
        tietojaMuokattu = true;
        setVirhe(virhe);
    }
    

    /**
     * Tapahtuman k‰sittelij‰ editin n‰pp‰inpainallukselle
     * @author Jyrki M‰ki
     */
    protected class KeyReleased extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if (lintuKohdalla != null) {
            try {
                if (!tietojaMuokattu) {
                    lintuBak = lintuKohdalla.clone();
                }
            } catch (CloneNotSupportedException e1) {
            }
            kasitteleMuutosLintuun((JTextField) e.getComponent());
        }
        }
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
        for (Havainto hav : havainnot) {
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
    

    /**
     * Palauttaa onko tietoja muokattu (editPanelissa)
     * @return true ,jos tietojamuokattu
     */
    public boolean getTietojaEditoitu() {
        return tietojaMuokattu;
    }
    

    /**
     * Palauttaa muokkaamattomat tiedot
     */
    public void palauta() {
        lintuKohdalla = lintuBak;
        try {
            paivakirja.korvaa(lintuBak.clone());
        } catch (CloneNotSupportedException e) {
        } catch (SailoException e) {
            JOptionPane.showMessageDialog(null, "Virhe " + e.getMessage());
        }
        laitaLaji();
        tietojaMuokattu = false;
    }
    
    
    /**
     * Asettaa linnuttaulukon modelin
     * @param model viite modeliin
     */
    public void setLinnutTaulukkoModel(LintuTaulukkoModel model) {
        this.lintuTaulukkoModel = model;
    }

}
