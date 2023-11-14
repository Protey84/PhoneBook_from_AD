package org.example.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PassWordDialog extends JDialog {

    private final JTextField jtfUsername = new JTextField(15);
    private final JPasswordField jpfPassword = new JPasswordField();

    private final JButton jbtOk = new JButton("Login");

    public PassWordDialog() {
        this(null, true, "", "");
    }

    public PassWordDialog(final JFrame parent, boolean modal, String username, String password) {
        super(parent, modal);
        JPanel p3 = new JPanel(new GridLayout(2, 1));
        JLabel jlblUsername = new JLabel("Username");
        p3.add(jlblUsername);
        JLabel jlblPassword = new JLabel("Password");
        p3.add(jlblPassword);
        JPanel p4 = new JPanel(new GridLayout(2, 1));
        jtfUsername.setText(username);
        jpfPassword.setText(password);
        addWindowListener( new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
                jbtOk.requestFocus();
            }
        });
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
        });


        jbtOk.addActionListener(e -> {
            setVisible(false);
            /*if ("stackoverflow".equals(String.valueOf(jpfPassword.getPassword()))
                    && "stackoverflow".equals(jtfUsername.getText())) {
                parent.setVisible(true);
                setVisible(false);
            } else {
                jlblStatus.setText("Invalid username or password");
            }*/
        });
    }
    public String getLogin(){
        return jtfUsername.getText();
    }
    public char[] getPassword() {
        char[] pwd = jpfPassword.getPassword().clone();
        jpfPassword.setText("");
        return pwd;
    }
}
