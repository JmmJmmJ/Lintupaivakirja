package wbLinnut;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

/**
 * Tulostus dialogi
 * @author Jyrki Mäki
 * @version 2.02.2021
 */
public class TulostaDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private final JPanel panel = new JPanel();
    private final JButton tulostaNappula = new JButton("Tulosta");
    private final JButton takaisinNappula = new JButton("Takaisin");
    private final JScrollPane scrollPane = new JScrollPane();
    private final JTextArea textArea = new JTextArea();

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
        
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        scrollPane.setViewportView(textArea);
        
        setVisible(true);
    }
    
    /**
     * Palautetaan alue, johon saa tulostaa
     * @return alue johon saa tulostaa.
     */
    public JTextArea getTextArea() {
        return textArea;
    }

}
