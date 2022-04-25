/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Command.Command;
import Command.CommandFactory;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 *
 * @author Leigh Briody
 */
public class Connection extends Thread implements Observer {

    //Create our variables
    private PrintWriter output;
    private Scanner input;
    private Socket dataSocket;
    private Observable o;



    public Connection(Socket dataSocket, Observable o) {
        this.dataSocket = dataSocket;
        this.o = o;

    }

    public void run() {
        try {

            // create the input and output streams
            OutputStream out = dataSocket.getOutputStream();
            output = new PrintWriter(new OutputStreamWriter(out));

            InputStream in = dataSocket.getInputStream();
            input = new Scanner(new InputStreamReader(in));

            System.out.println("Connection Started...");
            String inp = "";

            // wait for incoming messages.  When they are received call notifyObservers to send message out to all connections
            while (!inp.equals(".")) {
                inp = input.nextLine();
                System.out.println("Received: " + inp);

                //Create instance of the server
                Server theServer = (Server) this.o;

                //split the recievbed input into an array
                String[] choices = inp.split(" ");
                
                //pass the first input to command factory to determine logic / command needed
                Command commandNeeded = CommandFactory.createCommand(choices[0]);
                
                //call the necessary action needed
                commandNeeded.doAction(inp, theServer, output, this);
            

            }
            //remove observer when user wants to exit and enters "."
            o.deleteObserver(this);
            dataSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Update...");
        if (arg instanceof String) {
            output.println((String) arg);
            output.flush();
            System.out.println("Sending " + arg + o.toString());
        }
    }

  
    /**
     * *
     * returns the login menu as a string
     *
     * @return
     */
    public static String loginMenuString() {
        return "Hello and welcome to leighs video game store"
                + "\n" + "-----------------"
                + "\n" + "Below are your options of functionaility"
                + "\n" + "1) Enter 'listgames' to view all the games we have" + "";
    }

    /**
     * *
     * returns functionaility menu available to the user as a string
     *
     * @return
     */
    public static String getFunctionailityMenu() {
        return "\n"
                + "********* MENU OPTIONS *********" + "\n"
                + "Enter 'B (price) (gamename) to make a bid on the game"
                + "\n" + "Enter 'O (price) (gamename) to make a offer on the game"
                + "\n" + "--- If you already have a current bid or offer --- "
                + "\n" + "Enter 'O (price) (gamename) to change offer price "
                + "\n" + "Enter 'B (price) (gamename) to change bid price "
                + "\n" + "Enter 'B 0  (gamename) to cancle bid  "
                + "\n" + "Enter 'O  0  (gamename) to cancle offer  "
                + "\n" + "***********************************";
    }

}
