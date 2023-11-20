package org.example;

import org.example.domain.Person;
import org.example.utils.FileVerifayer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

public class MainWindow extends JFrame {
    private final List<Person> personList;
    private final List<Person> filterdPersonList;
    private final JTextField searchFIO;
    private final JTable table;
    public MainWindow(List<Person> persons) throws HeadlessException {
        setTitle("Телефонная книга");
        ImageIcon img = new ImageIcon(new FileVerifayer("src/main/resources","phonebook.png").getFile().getAbsolutePath());
        setIconImage(img.getImage());
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.personList=new ArrayList<>(persons);
        this.filterdPersonList=new ArrayList<>(persons);
        JPanel rootPanel = new JPanel(new BorderLayout());
        MyTableModel tableModel = new MyTableModel(filterdPersonList);
        table=new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(20);
        setColumnWidth();
        rootPanel.add(new JScrollPane(table),BorderLayout.CENTER);
        JPanel controlPanel = new JPanel(new BorderLayout());
        JLabel promtText = new JLabel("Введите текст для поиска: ");
        searchFIO = new JTextField(30);
        addWindowListener( new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
                searchFIO.requestFocus();
            }
        });
        searchFIO.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchText();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchText();
            }
        });
        controlPanel.add(promtText, BorderLayout.WEST);
        controlPanel.add(searchFIO, BorderLayout.CENTER);
        rootPanel.add(controlPanel, BorderLayout.PAGE_END);
        setContentPane(rootPanel);
        setVisible(true);
    }

    private void setColumnWidth(){
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(15);
        columnModel.getColumn(1).setPreferredWidth(170);
        columnModel.getColumn(1).setMinWidth(170);
        columnModel.getColumn(2).setPreferredWidth(90);
        columnModel.getColumn(2).setMinWidth(90);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(3).setMaxWidth(50);
        columnModel.getColumn(4).setPreferredWidth(15);
        columnModel.getColumn(5).setPreferredWidth(15);
        columnModel.getColumn(6).setPreferredWidth(15);
        columnModel.getColumn(7).setPreferredWidth(40);
        columnModel.getColumn(7).setMaxWidth(40);
        columnModel.getColumn(8).setPreferredWidth(15);
    }

    public void searchText(){
        this.filterdPersonList.clear();
        for (Person p:this.personList) {
            System.out.println(p.getName());
            if (p.contains(searchFIO.getText())){
                this.filterdPersonList.add(p);
            }
        }
        table.updateUI();
    }
    public static class MyTableModel implements TableModel {
        private final String[] columnsHeader = new String[]{"Отдел", "ФИО", "Телефон", "IP-телефон", "Должность", "e-mail", "Адрес", "Кабинет", "Имя компьютера"};
        private final Set<TableModelListener> listeners = new HashSet<>();

        private final List<Person> persons;

        public MyTableModel(List<Person> persons) {
            this.persons = persons;
        }

        public void addTableModelListener(TableModelListener listener) {
            listeners.add(listener);
        }

        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        public int getColumnCount() {
            return columnsHeader.length;
        }

        public String getColumnName(int columnIndex) {
            if (columnIndex>=0&&columnIndex<columnsHeader.length)
                return columnsHeader[columnIndex];
            return "";
        }

        public int getRowCount() {
            return persons.size();
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Person person = persons.get(rowIndex);
            switch (columnsHeader[columnIndex]) {
                case "ФИО":
                    return person.getName();
                case "Должность":
                    return person.getPosition();
                case "Отдел":
                    return person.getDepartment();
                case "Телефон":
                    return person.getTelephoneNumber();
                case "e-mail":
                    return person.getMail();
                case "Адрес":
                    return person.getStreetAddress();
                case "Имя компьютера":
                    return person.getDescription();
                case "IP-телефон":
                    return person.getIpPhone();
                case "Кабинет":
                    return person.getOffice();
            }
            return "";
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        public void removeTableModelListener(TableModelListener listener) {
            listeners.remove(listener);
        }

        public void setValueAt(Object value, int rowIndex, int columnIndex) {

        }

    }
}
