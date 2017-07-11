package ir.aut.ceit.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MainFrame extends JFrame {

    private JLabel[][] blocks;
    private int[][] tags;
    private JButton reset;
    private JButton ready;
    private JButton four;
    private JButton three;
    private JButton two;
    private JButton one;
    private int selectedBut = -1, selectedX, selectedY, tagIndexX, tagIndexY;
    private int count4 = 0, count3 = 0, count2 = 0, count1 = 0;


    public MainFrame() {

        super("My Game");

        JLabel[] ch;
        JLabel[] num;
        JPanel tablePanel;
        ch = new JLabel[10];
        num = new JLabel[10];
        blocks = new JLabel[10][10];
        tags = new int[15][12];
        tablePanel = new JPanel(null);

        setLayout(null);

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('f');
        fileMenu.setDisplayedMnemonicIndex(0);

        JMenuItem history = new JMenuItem("Conversations History");
        history.setMnemonic('c');
        history.setDisplayedMnemonicIndex(0);
        fileMenu.add(history);
        history.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                    }
                });

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('h');
        helpMenu.setDisplayedMnemonicIndex(0);
        helpMenu.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);


        JLabel jLabel1 = new JLabel("Please arrange your field: ", SwingConstants.CENTER);//
        jLabel1.setForeground(Color.BLACK);
        jLabel1.setLocation(10, 10);
        jLabel1.setSize(150, 30);
        add(jLabel1);

        int x = 100;
        int y = 60;
        char charc = 'A';
        for (int i = 0; i < 10; i++) {
            ch[i] = new JLabel("" + charc, SwingConstants.CENTER);
            ch[i].setLocation(x, y);
            ch[i].setSize(35, 35);
            ch[i].setForeground(Color.BLACK);
            tablePanel.add(ch[i]);
            x += 35;
            charc++;
        }

        x = 65;
        y = 95;
        int number = 1;
        for (int i = 0; i < 10; i++) {
            num[i] = new JLabel("" + number, SwingConstants.CENTER);
            num[i].setLocation(x, y);
            num[i].setSize(35, 35);
            num[i].setForeground(Color.BLACK);
            tablePanel.add(num[i]);
            y += 35;
            number++;
        }

        MouseClickHandler MouseHandler = new MouseClickHandler();
        ButtonHandler handler = new ButtonHandler();

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 12; j++) {
                tags[i][j] = 0;
            }
        }


        x = 100;
        for (int i = 0; i < 10; i++) {
            y = 95;
            for (int j = 0; j < 10; j++) {
                blocks[i][j] = new JLabel();
                Border border = BorderFactory.createLineBorder(Color.blue);
                blocks[i][j].setBorder(border);
                blocks[i][j].setLocation(x, y);
                blocks[i][j].setSize(35, 35);
                blocks[i][j].addMouseListener(MouseHandler);
                blocks[i][j].setBackground(Color.white);
                blocks[i][j].setOpaque(true);
                tablePanel.add(blocks[i][j]);
                y += 35;
            }
            x += 35;
        }

        tablePanel.setSize(590, 600);
        tablePanel.setOpaque(false);
        add(tablePanel);


        reset = new JButton("Reset");
        reset.setLocation(300, 570);
        reset.setSize(70, 30);
        reset.setOpaque(true);

        add(reset);

        ready = new JButton("Ready");
        ready.setLocation(450, 570);
        ready.setSize(70, 30);
        ready.setOpaque(true);
        add(ready);

        four = new JButton();
        four.setLocation(10, 485);
        four.setSize(80, 20);
        four.setOpaque(true);
        add(four);

        three = new JButton();
        three.setLocation(10, 510);
        three.setSize(60, 20);
        three.setOpaque(true);
        add(three);

        two = new JButton();
        two.setLocation(10, 535);
        two.setSize(40, 20);
        two.setOpaque(true);
        add(two);

        one = new JButton();
        one.setLocation(10, 560);
        one.setSize(20, 20);
        one.setOpaque(true);
        add(one);

        four.addActionListener(handler);
        three.addActionListener(handler);
        two.addActionListener(handler);
        one.addActionListener(handler);
        reset.addActionListener(handler);

    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == four) {
                selectedBut = 4;
            } else if (event.getSource() == three) {
                selectedBut = 3;
            } else if (event.getSource() == two) {
                selectedBut = 2;
            } else if (event.getSource() == one) {
                selectedBut = 1;

            } else if (event.getSource() == reset) {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        blocks[i][j].setBackground(Color.white);
                        blocks[i][j].setOpaque(true);
                        tags[i + 2][j + 1] = 0;
                    }
                }
                count4 = 0;
                count3 = 0;
                count2 = 0;
                count1 = 0;
            }
        }

    }

    private class MouseClickHandler extends MouseAdapter {


        @Override
        public void mouseClicked(MouseEvent event) {
            if (!event.isMetaDown() && !event.isAltDown()) {

                for (int i = 0; i < 10; i++) {
                    boolean done = false;
                    for (int j = 0; j < 10; j++) {
                        if (blocks[i][j] == event.getSource()) {
                            selectedX = i;
                            selectedY = j;
                            tagIndexX = i + 2;
                            tagIndexY = j + 1;
                            done = true;
                            break;
                        }
                    }
                    if (done)
                        break;
                }


                if (tags[tagIndexX][tagIndexY] == 0 && tags[tagIndexX - 1][tagIndexY + 1] == 0 &&
                        tags[tagIndexX - 1][tagIndexY] == 0
                        && tags[tagIndexX - 1][tagIndexY - 1] == 0 && tags[tagIndexX][tagIndexY + 1] == 0 &&
                        tags[tagIndexX][tagIndexY - 1] == 0 && tags[tagIndexX + 1][tagIndexY + 1] == 0 &&
                        tags[tagIndexX + 1][tagIndexY] == 0 && tags[tagIndexX + 1][tagIndexY - 1] == 0) {

                    if (selectedBut == 1 && count1 < 4) {
                        tags[tagIndexX][tagIndexY] = 1;
                        blocks[selectedX][selectedY].setBackground(Color.CYAN);
                        blocks[selectedX][selectedY].setOpaque(true);
                        count1++;
                        selectedBut = -1;
                    }

                    if (tags[tagIndexX + 2][tagIndexY + 1] == 0 && tags[tagIndexX + 2][tagIndexY] == 0 &&
                            tags[tagIndexX + 2][tagIndexY - 1] == 0 && (selectedX + 1) < 10) {

                        if (selectedBut == 2 && count2 < 3) {

                            tags[tagIndexX][tagIndexY] = 1;
                            tags[tagIndexX + 1][tagIndexY] = 1;
                            blocks[selectedX][selectedY].setBackground(Color.CYAN);
                            blocks[selectedX][selectedY].setOpaque(true);
                            blocks[selectedX + 1][selectedY].setBackground(Color.CYAN);
                            blocks[selectedX + 1][selectedY].setOpaque(true);
                            count2++;
                            selectedBut = -1;
                        }

                        if (tags[tagIndexX - 2][tagIndexY + 1] == 0 && tags[tagIndexX - 2][tagIndexY] == 0 &&
                                tags[tagIndexX - 2][tagIndexY - 1] == 0 && (selectedX - 1) >= 0) {

                            if (selectedBut == 3 && count3 < 2) {

                                tags[tagIndexX][tagIndexY] = 1;
                                tags[tagIndexX + 1][tagIndexY] = 1;
                                tags[tagIndexX - 1][tagIndexY] = 1;
                                blocks[selectedX][selectedY].setBackground(Color.CYAN);
                                blocks[selectedX][selectedY].setOpaque(true);
                                blocks[selectedX + 1][selectedY].setBackground(Color.CYAN);
                                blocks[selectedX + 1][selectedY].setOpaque(true);
                                blocks[selectedX - 1][selectedY].setBackground(Color.CYAN);
                                blocks[selectedX - 1][selectedY].setOpaque(true);
                                count3++;
                                selectedBut = -1;
                            }

                            if (tags[tagIndexX + 3][tagIndexY + 1] == 0 && tags[tagIndexX + 3][tagIndexY] == 0 &&
                                    tags[tagIndexX + 3][tagIndexY - 1] == 0 && (selectedX + 2) < 10 &&
                                    selectedBut == 4 && count4 < 1) {

                                tags[tagIndexX][tagIndexY] = 1;
                                tags[tagIndexX + 1][tagIndexY] = 1;
                                tags[tagIndexX - 1][tagIndexY] = 1;
                                tags[tagIndexX + 2][tagIndexY] = 1;
                                blocks[selectedX][selectedY].setBackground(Color.CYAN);
                                blocks[selectedX][selectedY].setOpaque(true);
                                blocks[selectedX + 1][selectedY].setBackground(Color.CYAN);
                                blocks[selectedX + 1][selectedY].setOpaque(true);
                                blocks[selectedX - 1][selectedY].setBackground(Color.CYAN);
                                blocks[selectedX - 1][selectedY].setOpaque(true);
                                blocks[selectedX + 2][selectedY].setBackground(Color.CYAN);
                                blocks[selectedX + 2][selectedY].setOpaque(true);
                                count4++;
                                selectedBut = -1;
                            }
                        }
                    }
                }

            }
        }

    }


    public static void main(String[] args) {

        // این باعث میشه یکم خوشگل تر باشه
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        MainFrame mainFrame = new MainFrame();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(607, 659);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

    }
}
