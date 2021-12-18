package wbLinnut;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Tulostus dialogi
 * @author Jyrki Mäki
 * @version 6.1.2021
 */
public class TulostaDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private final JTextPane tulostusIkkuna = new JTextPane();
    private final JPanel panel = new JPanel();
    private final JButton tulostaNappula = new JButton("Tulosta");
    private final JButton takaisinNappula = new JButton("Takaisin");

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
                    TulostaDialog frame = new TulostaDialog();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public TulostaDialog() {
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        tulostusIkkuna.setText("Laji\t\tp\u00E4iv\u00E4m\u00E4\u00E4r\u00E4\tpaikkakunta\ttiedot\r\nVaris\t\t23.5.2020\tSein\u00E4joki\t2 puistossa\r\nTalitiainen\t\t24.6.2020\tAlaj\u00E4rvi\t5 pihassa\r\nHaarap\u00E4\u00E4sky\t\t24.7.2020\tAlaj\u00E4rvi\t4 pellolla");
        
        contentPane.add(tulostusIkkuna, BorderLayout.CENTER);
        
        contentPane.add(panel, BorderLayout.SOUTH);
        tulostaNappula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(null,"Ei toimi");
            }
        });
        
        panel.add(tulostaNappula);
        takaisinNappula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        
        panel.add(takaisinNappula);
        
        setVisible(true);
    }

}
