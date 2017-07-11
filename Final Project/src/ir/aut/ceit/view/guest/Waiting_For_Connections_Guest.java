package ir.aut.ceit.view.guest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Admin on 6/17/2017.
 */
public class Waiting_For_Connections_Guest {

    public Waiting_For_Connections_Guest() {
        MainFrame mainFrame = new MainFrame();
    }

    static class MainFrame extends JFrame implements ActionListener {

        MainFrame() {


            setLayout(new FlowLayout());  // set frame layout
            JMenuBar mainMenu;

            JLabel label;
            JLabel buttonLabel;

            JButton cancelButton;


            mainMenu = new JMenuBar();
            setJMenuBar(mainMenu);
            setTitle("Waiting for connections ...");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
            setVisible(true);
            setBounds(0, 0, 300, 200);


            label = new JLabel("Waiting for the host to join...", Label.LEFT);
            add(label, Label.LEFT);

            buttonLabel = new JLabel();
            add(buttonLabel, RIGHT_ALIGNMENT);

            cancelButton = new JButton("Cancel");
            add(cancelButton, RIGHT_ALIGNMENT);

        }

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }
}



