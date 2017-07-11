package ir.aut.ceit.view.host;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Admin on 6/17/2017.
 */
public class Waiting_For_Connections_Host {


    public Waiting_For_Connections_Host() {
        MainFrame mainFrame = new MainFrame();
    }


    static class MainFrame extends JFrame implements ActionListener {

        MainFrame() {
            JFrame jFrame;
            JMenuBar mainMenu;
            Label receivedConnection ;


            /**
             * inja bayad be andazeii k connection ijaad mishe label bezarim , dorost nmidoonm vali fekr konm bayad too
             * actionPerformed handle she
             * IMP
             * **
             * hala vase nemoone ye panele example dorost mikonm , badan tamimesh midim
             */

            jFrame = new JFrame();

            setTitle("Waiting for connections ...");

            setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);


            jFrame.setLayout(new BorderLayout( ));
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setSize(300,200);


            jFrame.setVisible(true);


            receivedConnection = new Label("Received Connections:");
            jFrame.getContentPane().add(receivedConnection);


            add(new JSeparator());


            /**
             * ScrollBar
             * chera kaar nmikone ?
             */
            JScrollBar jScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 30, 20, 0, 500);
            jFrame.getContentPane().add(jScrollBar, BorderLayout.EAST);
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

