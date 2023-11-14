package org.example.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PassWordDialog extends JDialog {

    private final JLabel jlblUsername = new JLabel("Username");
    private final JLabel jlblPassword = new JLabel("Password");
    private final JTextField jtfUsername = new JTextField(15);
    private final JPasswordField jpfPassword = new JPasswordField();

    private final JButton jbtOk = new JButton("Login");

    private final JLabel jlblStatus = new JLabel(" ");

    @Resource(name = "userDn")
    private String name;
    public PassWordDialog() {
        this(null, true, "");
    }

    public PassWordDialog(final JFrame parent, boolean modal, String username) {
        super(parent, modal);
        JPanel p3 = new JPanel(new GridLayout(2, 1));
        p3.add(jlblUsername);
        p3.add(jlblPassword);
        JPanel p4 = new JPanel(new GridLayout(2, 1));
        jtfUsername.setText(username);
        addWindowListener( new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
                jpfPassword.requestFocus();
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
        p5.add(jlblStatus, BorderLayout.NORTH);
        jlblStatus.setForeground(Color.RED);
        jlblStatus.setHorizontalAlignment(SwingConstants.CENTER);

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


        jbtOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("stackoverflow".equals(String.valueOf(jpfPassword.getPassword()))
                        && "stackoverflow".equals(jtfUsername.getText())) {
                    parent.setVisible(true);
                    setVisible(false);
                } else {
                    jlblStatus.setText("Invalid username or password");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame=new JFrame("input");
        PassWordDialog passWordDialog=new PassWordDialog(frame, true, "login");
        passWordDialog.setVisible(true);
    }
}
