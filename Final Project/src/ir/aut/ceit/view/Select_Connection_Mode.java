package ir.aut.ceit.view;

import ir.aut.ceit.tools.chat.Code.client.Client;
import ir.aut.ceit.tools.chat.Code.server.Server;
import ir.aut.ceit.view.guest.Waiting_For_Connections_Guest;
import ir.aut.ceit.view.host.Waiting_For_Connections_Host;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Label.LEFT;
import static javax.swing.SwingConstants.RIGHT;

/**
 * Created by Admin on 6/17/2017.
 */
public class Select_Connection_Mode {

    private String name;
    private String Host_IP;    private String Guest_port;
    private String Guest_IP;

    /**
     * constructor
     */
    public Select_Connection_Mode() {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 400); // set Frame size
        mainFrame.setVisible(true); // display Frame
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost_IP() {
        return Host_IP;
    }

    public void setHost_IP(String host_IP) {
        Host_IP = host_IP;
    }

    public String getGuest_port() {
        return Guest_port;
    }

    public void setGuest_port(String guest_port) {
        Guest_port = guest_port;
    }

    public String getGuest_IP() {
        return Guest_IP;
    }

    public void setGuest_IP(String guest_IP) {
        Guest_IP = guest_IP;
    }

    class MainFrame extends JFrame implements ActionListener {

        private JTextField textField1;
        private JTextField textField2;
        private JTextField textField3;
        private JTextField textField4;

        private JRadioButton Host; // selects plain text
        private JRadioButton Guest; // selects bold text

        private JButton Exit;
        private JButton Start;

        private ButtonGroup radioGroup; // buttongroup to hold radio buttons


        private Label nameLabel;
        private Label hostLabel;
        private Label portLabel1;
        private Label guestLabel;
        private Label guest_IP_Port_Label1;
        private Label guest_IP_Port_Label2;
        private Label buttonLabel;

        /**
         * MainFrame constructor adds JRadioButtons to JFrame
         */
        MainFrame() {

            super("Select Connection Mode");
            setLayout(new FlowLayout()); // set Frame layout

            textField1 = new JTextField(30);
            textField1.setHorizontalAlignment(RIGHT);

            textField2 = new JTextField(20);
            textField2.setHorizontalAlignment(RIGHT);


            textField3 = new JTextField(10);
            textField3.setHorizontalAlignment(LEFT);

            textField4 = new JTextField(10);
            textField4.setHorizontalAlignment(RIGHT);


/**
 * creating labels
 */
            nameLabel = new Label("Name:", Label.LEFT);
            hostLabel = new Label("", Label.LEFT);
            portLabel1 = new Label("Port:", Label.LEFT);
            guestLabel = new Label("", Label.LEFT);
            guest_IP_Port_Label1 = new Label("IP:", Label.LEFT);
            guest_IP_Port_Label2 = new Label("Port:", Label.RIGHT);
            buttonLabel = new Label();


            /** create radio buttons
             *
             */
            Host = new JRadioButton("Host", true);
            Guest = new JRadioButton("Guest", false);


            // create logical relationship between JRadioButtons
            radioGroup = new ButtonGroup(); // create ButtonGroup
            radioGroup.add(Host); // add plain to group
            radioGroup.add(Guest); // add bold to group

            //create bottom buttons
            Exit = new JButton("Exit");
            Start = new JButton("Start");


/**
 * adding labels and textfields ,and radiobuttons
 */

            add(nameLabel);
            add(textField1); // add textField to JFrame

            add(hostLabel);
            add(Host); // add Host button to JFrame

            add(portLabel1);
            add(textField2); // add textField to JFrame

            add(guestLabel);
            add(Guest); // add Guest button to JFrame

            add(guest_IP_Port_Label1);
            add(textField3); // add textField to JFrame


            add(guest_IP_Port_Label2);
            add(textField4); // add textField to JFrame

            add(buttonLabel, "20");

            //adding buttons  to jframe
            add(Exit);
            add(Start);

            /**
             * Invoked when an action occurs.
             *
             * @param e
             */



            /*adding  bottom buttons
            buttons = new ButtonGroup();
            buttons.add(Exit);
            buttons.add(Start);
            */
        }


        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Start.isSelected()) {
                if (Host.isSelected()) {
                    Waiting_For_Connections_Host  waiting_for_connections_host = new Waiting_For_Connections_Host();
                    Client client = new Client();

                } else if (Guest.isSelected()) {
                    Waiting_For_Connections_Guest waiting_for_connections_guest = new Waiting_For_Connections_Guest();
                    Server server = new Server();
                }
            }
        }

    }
}

