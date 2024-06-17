package lab.nanoelectronic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MyJFrame extends JFrame {
    private JPanel sideBar;
    private JScrollPane mainPane;

    MyJFrame() {
        init();
        
        //JButton btnC = new JButton("按鈕元件C");
        //contentPane.add(btnC, BorderLayout.CENTER);

        
        
        setVisible(true);
    }

    void init() {
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

    void sideBarLayout() {
        sideBar = new JPanel();
        sideBar.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JLabel fileJLabel = new JLabel("file");
        sideBar.add(fileJLabel);

        JTextField fileJText = new JTextField();
        fileJText.setColumns(20);
        sideBar.add(fileJText);
    }

    void mainPanelLayout() {
        mainPane = new JScrollPane();
        mainPane.setLayout(null);
    }
}

public class Main {
    public static void main(String[] args) {
        MyJFrame frame = new MyJFrame();
    }
}