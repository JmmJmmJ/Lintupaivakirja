package wbLinnut;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * N‰ytt‰‰ tietoja ohjelmasta
 * @author Jyrki M‰ki
 * @version 10.12.2020
 */
public class TiedotDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private final JLabel ohjelmanNimi = new JLabel("Lintup\u00E4iv\u00E4kirja");
    private final JLabel versioLabel = new JLabel("1.0");
    private final JLabel tekijaLabel = new JLabel("Jyrki M\u00E4ki");
    private final JButton okButton = new JButton("OK");


    /**
     * Create the dialog.
     */
    public TiedotDialog() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        versioLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        tekijaLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        ohjelmanNimi.setFont(new Font("Tahoma", Font.BOLD, 24));
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel.setHorizontalGroup(
            gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPanel.createSequentialGroup()
                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPanel.createSequentialGroup()
                            .addGap(115)
                            .addComponent(ohjelmanNimi))
                        .addGroup(gl_contentPanel.createSequentialGroup()
                            .addGap(194)
                            .addComponent(versioLabel))
                        .addGroup(gl_contentPanel.createSequentialGroup()
                            .addGap(171)
                            .addComponent(tekijaLabel)))
                    .addContainerGap(130, Short.MAX_VALUE))
                .addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
                    .addContainerGap(325, Short.MAX_VALUE)
                    .addComponent(okButton)
                    .addContainerGap())
        );
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        gl_contentPanel.setVerticalGroup(
            gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPanel.createSequentialGroup()
                    .addGap(56)
                    .addComponent(ohjelmanNimi)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(versioLabel)
                    .addGap(31)
                    .addComponent(tekijaLabel)
                    .addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                    .addComponent(okButton)
                    .addContainerGap())
        );
        contentPanel.setLayout(gl_contentPanel);
        setVisible(true);
    }
}
