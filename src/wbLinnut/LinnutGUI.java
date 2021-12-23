package wbLinnut;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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
import javax.swing.RowFilter;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

import fi.jyu.mit.gui.EditPanel;
import linnutswing.LinnutSwing;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;

/**
 * Lintup‰iv‰kirjan k‰yttˆliittym‰ WindowBuilderill‰.
 * 
 * -poistaminen ei toimi
 * -havainnot tallennetaan, vaikka eiv‰t olisi muuttuneet
 * -Havainnot v‰lilehdell‰ tehdyt muutokset j‰‰v‰t n‰kyviin, vaikka vaihtaa v‰lilehte‰. Tallennusta ei kysyt‰.
 * -havaintojen j‰rjest‰minen ei toimi
 * -havainnot taulukon kahden ensimm‰isen sarakkeen muuttaminen pit‰isi est‰‰
 * -ohje avaa vain suunnitelman
 * -k‰ynist‰ess‰ avataan oletus tiedosto, ei viimeksi avattua
 * -tulosta n‰ytt‰‰ vain esikatselun
 * -tallentaminen poistaa kommentti rivin tiedostosta
 * -lintujen TableModel addrow metodin saisi ehk‰ ottamaan parametrina lintu olion
 * -olisi varmaan parempi tehd‰ jotenkin virheiden k‰sittelyn kautta tarkistaa lˆytyykˆ tiedostoa
 * -modaaliset dialogit puuttuvat
 * -yleist‰minen?
 * @author Jyrki M‰ki
 * @version 01.03.2021
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
    private final JPanel tiedotPanel = new JPanel();
    private final EditPanel editPanelLaji = new EditPanel();
    private final EditPanel editPanelTNimi = new EditPanel();
    private final EditPanel editPanelPituus = new EditPanel();
    private final EditPanel editPanelSiipivali = new EditPanel();
    private final EditPanel editPanelPaino = new EditPanel();
    private final EditPanel editPanelUhanalai = new EditPanel();
    private final JMenuItem tiedostoMenu_4 = new JMenuItem("Lopeta");

    private final LinnutSwing linnutswing = new LinnutSwing();
    private final ButtonGroup buttonGroupHavainnot = new ButtonGroup();
    private final JLabel lblTesti = new JLabel("");
    private final JScrollPane scrollPane = new JScrollPane();
    private final JScrollPane scrollPane_1 = new JScrollPane();
    private final JTable havainnotTable = new JTable();
    private LintuTaulukkoModel model = new LintuTaulukkoModel();
    private TableRowSorter<LintuTaulukkoModel> sorter = new TableRowSorter<LintuTaulukkoModel>(model);;
    private final JTable linnutTable = new JTable(model);
    private final JComboBox<String> comboBox;

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
                    String tiedostoHav = "Havainnot.hav";
                    if (args.length > 0) {
                        frame.lueTiedosto(args[0]);
                    } else if (new File("havainnot.hav").isFile()) { // voisi varmaan tarkistaa virheiden k‰sittelyn
                                                                     // kautta onnistuuko tiedoston lukeminen
                        frame.lueTiedosto(tiedostoHav);
                    } else {
                        frame.avaa();
                    }
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
        String[] valinnat = { "Laji", "Tieteellinen nimi" };
        comboBox = new JComboBox<>(valinnat);

        linnutswing.setTableLinnut(linnutTable);
        linnutswing.setPanelLintu(tiedotPanel);
        linnutswing.setTableHavainnot(havainnotTable);
        linnutswing.setLabelVirhe(lblTesti);
        linnutswing.setEditHaku(EtsiLaatikko);
        linnutswing.setLinnutTaulukkoModel(model);

        linnutTable.setRowSorter(sorter);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                if (linnutswing.getMuutos()) {
                    int vastaus = JOptionPane.showConfirmDialog(null, "Haluatko tallentaa", "Tallennetaanko?",
                            JOptionPane.YES_NO_OPTION);
                    if (vastaus == JOptionPane.YES_OPTION) {
                        linnutswing.tallenna();
                    }
                }
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
                linnutswing.tallenna();
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
                int vastaus = JOptionPane.showConfirmDialog(null, "Talletetaanko?", "Haluatko tallentaa?",
                        JOptionPane.YES_NO_OPTION);
                if (vastaus == JOptionPane.YES_OPTION) {
                    linnutswing.tallenna();
                }
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
        valilehdet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (valilehdet.getSelectedIndex() != 1 && linnutswing.getTietojaEditoitu()) {
                    int vastaus = JOptionPane.showConfirmDialog(null, "Talletetaanko?", "Linnun tiedot muuttuneet.",
                            JOptionPane.YES_NO_OPTION);
                    if (vastaus == JOptionPane.YES_OPTION) {
                        linnutswing.tallenna();
                    } else {
                        linnutswing.palauta();
                    }
                }
            }
        });

        contentPane.add(valilehdet, BorderLayout.CENTER);

        valilehdet.addTab("Linnut", null, linnut, null);
        linnut.setLayout(new BorderLayout(0, 0));
        FlowLayout fl_linnutEtsi = (FlowLayout) linnutEtsi.getLayout();
        fl_linnutEtsi.setAlignment(FlowLayout.LEFT);

        linnut.add(linnutEtsi, BorderLayout.NORTH);
        Etsi.setHorizontalAlignment(SwingConstants.CENTER);

        linnutEtsi.add(Etsi);

        linnutEtsi.add(EtsiLaatikko);

        linnutEtsi.add(comboBox);

        linnut.add(scrollPane, BorderLayout.CENTER);

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
        havainnotTable.setModel(new DefaultTableModel(new Object[][] { { null, null, "test", "test", null }, },
                new String[] { "Laji", "Tieteellinen nimi", "P\u00E4iv\u00E4m\u00E4\u00E4r\u00E4", "Paikka",
                        "Lis\u00E4tiedot" }));

        EtsiLaatikko.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                newFilter(comboBox.getSelectedIndex());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                newFilter(comboBox.getSelectedIndex());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                newFilter(comboBox.getSelectedIndex());
            }
        });

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

        valikko_ala.add(lblTesti);

        ListSelectionModel SelectionModel = linnutTable.getSelectionModel(); // TODO tarkista, ett‰ ymm‰rr‰t
        SelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        SelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                linnutswing.naytaLaji();
                naytaHav();
            }
        });
        

        havainnotTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent arg0) {
                ButtonModel b = buttonGroupHavainnot.getSelection();
                char nappain = (char) b.getMnemonic();
                switch (nappain) {
                case 'K':
                    linnutswing.editHavainnotTable();
                    break;
                case 'V':
                    linnutswing.editHavainnotTableValittu();
                    break;
                default:
                    break;
                }
            }
        });

    }

    
    /**
     * N‰ytt‰‰ valitun lajin havainnot tai kaikki havainnot
     * riippuen siit‰ mik‰ nappula on valittuna
     */
    protected void naytaHav() {
        ButtonModel b = buttonGroupHavainnot.getSelection();
        char nappain = (char) b.getMnemonic();
        switch (nappain) {
        case 'K':
            linnutswing.naytaKaikkiHavainnot();
            break;
        case 'V':
            linnutswing.naytaHavainto();
            break;
        default:
            break;
        }
    }

    
    /**
     * Avaa uuden havainnot tiedoston
     */
    public void avaa() {
        String tiedosto = JOptionPane.showInputDialog(null, "Anna tiedoston nimi", "Avaa havainnot tiedosto",
                JOptionPane.INFORMATION_MESSAGE);
        if (tiedosto == null) {
        } else if (new File(tiedosto).isFile()) { // voisi varmaan tarkistaa virheiden k‰sittelyn kautta onnistuuko
                                                  // tiedoston lukeminen
            linnutswing.lueTiedostosta(tiedosto);
        } else {
            JOptionPane.showMessageDialog(null, "Tiedoston avaaminen ei onnistu");
            avaa();
        }
    }

    
    /**
     * Lukee tiedot tiedostosta
     * @param nimi tiedosto, josta luetaan lintujen tiedot
     */
    protected void lueTiedosto(String nimi) {
        String virhe = linnutswing.lueTiedostosta(nimi);
        if (virhe != null)
            JOptionPane.showMessageDialog(null, virhe);
    }

    
    /**
     * N‰ytt‰‰ TiedotDialogin
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
    

    /**
     * Suodattaa tiedot hakusanan mukaan
     * @param sarake sarake, josta etsit‰‰n
     */
    private void newFilter(int sarake) {
        RowFilter<LintuTaulukkoModel, Object> rf = null;
        try {
            rf = RowFilter.regexFilter(EtsiLaatikko.getText(), sarake); // sarake josta etsit‰‰n
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
