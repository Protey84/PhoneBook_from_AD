package org.example.gui;

import org.example.utils.AppPreferences;
import org.example.utils.PropertyReader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PassWordDialog extends JDialog {

    private final JTextField jtfUsername = new JTextField(15);
    private final JPasswordField jpfPassword = new JPasswordField();

    private final JButton jbtOk = new JButton("Login");
    private final AppPreferences appPreferences;

    public PassWordDialog(final JFrame parent, boolean modal, String username, AppPreferences appPreferences, PropertyReader propertyReader) {
        super(parent, modal);
        this.appPreferences=appPreferences;
        JPanel p3 = new JPanel(new GridLayout(2, 1));
        JLabel jlblUsername = new JLabel("Username");
        p3.add(jlblUsername);
        JLabel jlblPassword = new JLabel("Password");
        p3.add(jlblPassword);
        JPanel p4 = new JPanel(new GridLayout(2, 1));
        jtfUsername.setText(username);
        jpfPassword.setText(appPreferences.getWord(username));

        p4.add(jtfUsername);
        p4.add(jpfPassword);

        JPanel p1 = new JPanel();
        p1.add(p3);
        p1.add(p4);

        JPanel p2 = new JPanel();
        p2.add(jbtOk);

        JPanel p5 = new JPanel(new BorderLayout());
        p5.add(p2, BorderLayout.CENTER);
        JCheckBox cbStored = new JCheckBox("Запомнить пароль");
        cbStored.setSelected(propertyReader.isSavePass());
        p5.add(cbStored, BorderLayout.NORTH);
        cbStored.setHorizontalAlignment(SwingConstants.CENTER);

        setLayout(new BorderLayout());
        add(p1, BorderLayout.CENTER);
        add(p5, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
            @Override
            public void windowOpened( WindowEvent e ){
                jbtOk.requestFocus();
            }
        });

        jbtOk.addActionListener(e -> {
            if (cbStored.isSelected())
                savePass();
            else
                appPreferences.removeWord();
            propertyReader.setSavePass(cbStored.isSelected());
            setVisible(false);
        });
        JRootPane rootPane = SwingUtilities.getRootPane(jbtOk);
        rootPane.setDefaultButton(jbtOk);
    }
    public String getLogin(){
        return jtfUsername.getText();
    }

    private void savePass(){
        String login=jtfUsername.getText();
        appPreferences.saveWord(new String(jpfPassword.getPassword()), login);
    }
    public char[] getPassword() {
        char[] pwd = jpfPassword.getPassword().clone();
        jpfPassword.setText("");
        return pwd;
    }
}
