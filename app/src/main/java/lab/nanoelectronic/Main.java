package lab.nanoelectronic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainFrame extends JFrame {
    private JPanel sideBar, mainNotScrollPane;
    private JScrollPane mainPane;
    private JTextField fileJText;
    private JButton fileConfirmJButton;

    private JTable[] jTables = new JTable[2];



    private DataHandler dataHandler = null;

    MainFrame() {
        initHandler();
        initLayout();

        setVisible(true);
    }

    private void initLayout() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 700);
        setLayout(new BorderLayout(0, 0));

        sideBarLayout();
        mainPanelLayout();
        
        add(sideBar, BorderLayout.WEST);
        add(mainPane, BorderLayout.CENTER);
        
        //mainPane = new JScrollPane();
        //add(mainPane, BorderLayout.CENTER);
    }
    
    private void initHandler() {
        dataHandler = new DataHandler();
    }

    private void sideBarLayout() {
        sideBar = new JPanel();
        sideBar.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JLabel fileJLabel = new JLabel("file");
        sideBar.add(fileJLabel);

        fileJText = new JTextField();
        fileJText.setColumns(10);
        sideBar.add(fileJText);

        fileConfirmJButton = new JButton("confirm");
        sideBar.add(fileConfirmJButton);
        fileConfirmJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.err.println("pressed");
                if (!fileJText.getText().isEmpty()) {
                    handleData(fileJText.getText());
                }
            }
        });

    }

    private void mainPanelLayout() {
        mainPane = new JScrollPane();
    }

    private void handleData(String file) {
        System.err.println("handleData");

        //int openStatus = dataHandler.openTable();    // test data
        int openStatus = dataHandler.openTable(fileJText.getText(), getFileType());
        if (openStatus == 0) {
            // file Exist
        } else if (openStatus == 1) {
            // open success
            showData();
        } else if (openStatus == 2) {
            // file doesn't exist
            ;
        }
    }

    String getFileType() {
        return "LabVIEW";
    }

    private void showData() {
        System.err.println("showData");
        if (dataHandler == null) {
            System.err.println("error: data handler not found.");
            return;
        }

        remove(mainPane);

        if (dataHandler.getTableNum() == 1) {
            Double[][] table = dataHandler.getTable();
            jTables[0] = new JTable(table, dataHandler.getColumnName());
            jTables[0].setFillsViewportHeight(true);
            System.err.println("add");
        } else if (dataHandler.getTableNum() == 2) {
            System.err.println("Error: file not found.");
        }
        mainPane = new JScrollPane(jTables[0]);
        add(mainPane);
        repaint();
        revalidate();
    }
}

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
    }
}