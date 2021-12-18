package wbLinnut;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import javax.swing.table.DefaultTableModel;
import fi.jyu.mit.gui.ComboBoxChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

import fi.jyu.mit.gui.EditPanel;
import linnutswing.LinnutSwing;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;

/**
 * Lintupäiväkirjan käyttöliittymä WindowBuilderillä.
 * @author Jyrki Mäki
 * @version 21.02.2021
 */
public class LinnutGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private final JTabbedPane valilehdet = new JTabbedPane(JTabbedPane.TOP);
    private final JPanel linnut = new JPanel();
    private final JPanel tiedot = new JPanel();
    private final JPanel havainnot = new JPanel();
    private final JPanel valikko_ala = new JPanel();
    private final JButton lisaaNappula = new JButton("Lis\u00E4\u00E4");
    private final JButton poistaNappula = new JButton("Poista");
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menuTiedosto = new JMenu("Tiedosto");
    private final JMenuItem tiedostoMenu_1 = new JMenuItem("Avaa");
    private final JMenuItem tiedostoMenu_2 = new JMenuItem("Tulosta");
    private final JMenuItem tiedostoMenu_3 = new JMenuItem("Tallenna");
    private final JMenu menuOhje = new JMenu("Ohje");
    private final JPanel listaValinnat = new JPanel();
    private final JRadioButton naytaValitut = new JRadioButton("N\u00E4yt\u00E4 valittu laji");
    private final JRadioButton naytaKaikki = new JRadioButton("N\u00E4yt\u00E4 kaikki");
    private final JPanel linnutEtsi = new JPanel();
    private final JLabel Etsi = new JLabel("Etsi");
    private final JTextField EtsiLaatikko = new JTextField();
    private final JMenuItem menuOhje_1 = new JMenuItem("Ohje");
    private final JMenuItem menuOhje_2 = new JMenuItem("Tietoja");
    private final ComboBoxChooser comboBoxChooser = new ComboBoxChooser();
    private final JPanel tiedotPanel = new JPanel();
    private final EditPanel editPanelLaji = new EditPanel();
    private final EditPanel editPanelTNimi = new EditPanel();
    private final EditPanel editPanelPituus = new EditPanel();
    private final EditPanel editPanelSiipivali = new EditPanel();
    private final EditPanel editPanelPaino = new EditPanel();
    private final EditPanel editPanelUhanalai = new EditPanel();
    private final JMenuItem tiedostoMenu_4 = new JMenuItem("Lopeta");
    /**
     * nonvisual
     */
    protected final LinnutSwing linnutswing = new LinnutSwing();
    private final ButtonGroup buttonGroupHavainnot = new ButtonGroup();
    private final JLabel lblTesti2 = new JLabel("Testi");
    private final JScrollPane scrollPane = new JScrollPane();
    private final JTable linnutTable = new JTable();
    private final JScrollPane scrollPane_1 = new JScrollPane();
    private final JTable havainnotTable = new JTable();

    /**
     * Launch the application.
     * @param args 
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    LinnutGUI frame = new LinnutGUI();
                    frame.setVisible(true);
                    frame.lueTiedosto("linnut.dat");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public LinnutGUI() {
        
        linnutswing.setTableLinnut(linnutTable);
        linnutswing.setPanelLintu(tiedotPanel);
        linnutswing.setTableHavainnot(havainnotTable);
        linnutswing.setLabelVirhe(lblTesti2);
        linnutswing.setEditHaku(EtsiLaatikko);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
            linnutswing.tallenna();
            }
        });
        
        EtsiLaatikko.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                linnutswing.hae();
            }
        });
  
        EtsiLaatikko.setColumns(10);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 327);
        
        setJMenuBar(menuBar);
        
        menuBar.add(menuTiedosto);
        tiedostoMenu_1.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                avaa();
            }
        });
        
        menuTiedosto.add(tiedostoMenu_1);
        tiedostoMenu_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                tulosta();
            }
        });
        
        menuTiedosto.add(tiedostoMenu_2);
        tiedostoMenu_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                linnutswing.tallenna();
            }
        });
        
        menuTiedosto.add(tiedostoMenu_3);
        tiedostoMenu_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                lopeta();
            }
        });
        
        menuTiedosto.add(tiedostoMenu_4);
        
        menuBar.add(menuOhje);
        menuOhje_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                linnutswing.ohje();
            }
        });
        
        menuOhje.add(menuOhje_1);
        menuOhje_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                about();
            }
        });
                
        menuOhje.add(menuOhje_2);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        contentPane.add(valilehdet, BorderLayout.CENTER);
        
        valilehdet.addTab("Linnut", null, linnut, null);
        linnut.setLayout(new BorderLayout(0, 0));
        FlowLayout fl_linnutEtsi = (FlowLayout) linnutEtsi.getLayout();
        fl_linnutEtsi.setAlignment(FlowLayout.LEFT);
        
        linnut.add(linnutEtsi, BorderLayout.NORTH);
        Etsi.setHorizontalAlignment(SwingConstants.CENTER);
        
        linnutEtsi.add(Etsi);
        
        linnutEtsi.add(EtsiLaatikko);
        comboBoxChooser.getCaptionLabel().setText("");
        comboBoxChooser.getComboBox().setModel(new DefaultComboBoxModel<String>(new String[] {"Laji", "Tieteellinen nimi"}));
        
        linnutEtsi.add(comboBoxChooser);
        
        linnut.add(scrollPane, BorderLayout.CENTER);

        linnutTable.setModel(new DefaultTableModel(
            new Object[][] {
                {"Harakka", "Pica pica", "1"},
                {"Varis", "Corvus corone", "1"},
                {"Talitiainen", "Parus major", "3"},
                {"Haarap\u00E4\u00E4sky", "Hirundo rustica", "2"},
            },
            new String[] {
                "Laji", "Tieteellinen nimi", "Havaintoja"
            }
        ));
        
        scrollPane.setViewportView(linnutTable);
        
        valilehdet.addTab("Tiedot", null, tiedot, null);
        tiedot.setLayout(new BorderLayout(0, 0));
        
        tiedot.add(tiedotPanel, BorderLayout.NORTH);
        tiedotPanel.setLayout(new BoxLayout(tiedotPanel, BoxLayout.Y_AXIS));
        editPanelLaji.getLabel().setText("Laji");
        editPanelLaji.getEdit().setText("Harakka");
        
        tiedotPanel.add(editPanelLaji);
        editPanelTNimi.getLabel().setText("Tieteellinen nimi");
        editPanelTNimi.getEdit().setText("Pica Pica");
        
        tiedotPanel.add(editPanelTNimi);
        editPanelPituus.getLabel().setText("Pituus");
        editPanelPituus.getEdit().setText("44-46");
        
        tiedotPanel.add(editPanelPituus);
        editPanelSiipivali.getLabel().setText("Siipiv\u00E4li");
        editPanelSiipivali.getEdit().setText("52-60");
        
        tiedotPanel.add(editPanelSiipivali);
        editPanelPaino.getLabel().setText("Paino");
        editPanelPaino.getEdit().setText("160-280");
        
        tiedotPanel.add(editPanelPaino);
        editPanelUhanalai.getLabel().setText("Uhanalaisuus");
        editPanelUhanalai.getEdit().setText("Silm\u00E4ll\u00E4pidett\u00E4v\u00E4");
        
        tiedotPanel.add(editPanelUhanalai);
        
        valilehdet.addTab("Havainnot", null, havainnot, null);
        havainnot.setLayout(new BorderLayout(0, 0));
        FlowLayout fl_listaValinnat = (FlowLayout) listaValinnat.getLayout();
        fl_listaValinnat.setAlignment(FlowLayout.LEFT);
        
        havainnot.add(listaValinnat, BorderLayout.NORTH);
        buttonGroupHavainnot.add(naytaValitut);
        naytaValitut.setMnemonic('V');
        naytaValitut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                linnutswing.naytaHavainto();
            }
        });
        
        listaValinnat.add(naytaValitut);
        buttonGroupHavainnot.add(naytaKaikki);
        naytaKaikki.setSelected(true);
        naytaKaikki.setMnemonic('K');
        naytaKaikki.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                linnutswing.naytaKaikkiHavainnot();
            }
        });
        
        listaValinnat.add(naytaKaikki);
        
        havainnot.add(scrollPane_1, BorderLayout.CENTER);
        havainnotTable.setModel(new DefaultTableModel(
            new Object[][] {
                {null, "test", "test", null},
            },
            new String[] {
                "Laji", "Tieteellinen nimi", "P\u00E4iv\u00E4m\u00E4\u00E4r\u00E4", "Lis\u00E4tiedot"
            }
        ));
        
        scrollPane_1.setViewportView(havainnotTable);
        
        FlowLayout fl_valikko_ala = (FlowLayout) valikko_ala.getLayout();
        fl_valikko_ala.setAlignment(FlowLayout.LEFT);
        
        contentPane.add(valikko_ala, BorderLayout.SOUTH);
        lisaaNappula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (valilehdet.getSelectedIndex() == 0) {
                linnutswing.lisaa(); 
                linnutswing.alusta();
                }
                if (valilehdet.getSelectedIndex() == 2) {
                linnutswing.lisaaHavainto(); 
                linnutswing.alusta();
                }
            }
        });
        
        valikko_ala.add(lisaaNappula);
        poistaNappula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                linnutswing.poista();
            }
        });
        
        valikko_ala.add(poistaNappula);
        
        valikko_ala.add(lblTesti2);
        
        ListSelectionModel SelectionModel = linnutTable.getSelectionModel(); // TODO tarkista, että ymmärrät
        SelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        SelectionModel.addListSelectionListener(new ListSelectionListener() {
          @Override
        public void valueChanged(ListSelectionEvent e) {
            linnutswing.naytaLaji();
            naytaHav();
          }
        });
        
    }
    
    /**
     * Näyttää valitun lajin havainnot tai kaikki havainnot
     * riippuen siitä mikä nappula on valittuna
     */
    protected void naytaHav() {
        ButtonModel b = buttonGroupHavainnot.getSelection();
        char nappain = (char)b.getMnemonic();
        switch(nappain) {
            case 'K': linnutswing.naytaKaikkiHavainnot(); break;
            case 'V': linnutswing.naytaHavainto(); break;
            default:  break;
        }
    }   
    
    /**
     * Avaa uuden havainnot tiedoston
     * TODO: if ei tiedosta -> avaa dialog, modal
     */
    public void avaa() {
        String name = JOptionPane.showInputDialog(null,
                "Anna tiedoston nimi",
                "Avaa havainnot tiedosto",
               JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null,"Ei toimi");
        //linnutswing.lueTiedostosta();
    }
    
    protected void lueTiedosto(String nimi) {
        linnutswing.lueTiedostosta(nimi);
    }
    
    /**
     * Näyttää TiedotDialogin
     */
    protected void about() {
        new TiedotDialog();        
    }
    
    /**
     * Tulostaa tiedot
     */
    protected void tulosta() {
        TulostaDialog dialog = new TulostaDialog();
        linnutswing.tulostaValitut(dialog.getTextArea());
        dialog.setVisible(true);
    }
    
    /**
     * Lopettaa ohjelman
     */
    protected void lopeta() {
        processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));    
    }    
    
}
