/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

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

    //add init 
    private PrintWriter output;
    private Scanner input;
    private Socket dataSocket;
    private Observable o;

    private String test;

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

                //we need top 
                //if input == listgamesoutput true
                //should probably put a switch statement here
                handleInput(inp, this.o, this.output, this);

                //  o.notifyObservers(inp);
            }
            o.deleteObserver(this);
            dataSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // called by notifyObservers, write arg to this connection
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Update...");
        if (arg instanceof String) {
            output.println((String) arg);
            output.flush();
            System.out.println("Sending " + arg);
        }
    }

    public static void handleInput(String inp, Observable o, PrintWriter output, Connection c) {

        //intstance of the server
        Server theServer = (Server) c.o;

        //split the choice up into a character array
        //last element of array will always be the username
        //[B , 100 , GAME , username] OR [O , 100 , GAME , username]
        String[] choices = inp.split(" ");

        switch (choices[0].trim()) {
            case "loggedin":
                output.println((String) "Hello and welcome to leighs video game store"
                        + "\n" + "-----------------"
                        + "\n" + "Below are your options of functionaility"
                        + "\n" + "1) Enter 'listgames' to view all the games we have" + "");

                output.flush();
                break;
            case "listgames":

                String allGamesDisplayString = "Here is a list of all the games " + "\n";

                for (Game g : theServer.getGames()) {
                    allGamesDisplayString += g.toString() + "\n";
                }
                allGamesDisplayString += "\n" + "Enter 'B (price) (gamename) to make a bid on the game"
                        + "\n" + "Enter 'O (price) (gamename) to make a offer on the game";

                output.println((String) allGamesDisplayString);
                output.flush();
                break;
            case "O":
                //bid details
                int offerPrice = Integer.parseInt(choices[1]);
                String gameName = choices[2].trim();
                String userEmail = choices[choices.length - 1];
                //make the bid
                theServer.makeOfferForGame(offerPrice, gameName, userEmail);
                //output message
                int bestOffer = theServer.getGamesBestOffer(gameName);
                output.println((String) "You have made an offer of " + offerPrice + " for the game " + gameName + "the best offer for this is now" + bestOffer);
                output.flush();
                
                //want to to display order book to the log
                System.out.println(theServer.getGamesOrderBook(gameName));
                break;
            case "B":
                //bid details
                int bidPrice = Integer.parseInt(choices[1]);
                String gameNameBid = choices[3];
                String userEmailOffer = choices[choices.length - 1];
                //make the bid
                theServer.bidOnGame(bidPrice, gameNameBid, userEmailOffer);
                //output message
                output.println((String) "Here is a list of all games " + "\n" + "-----------------" + "\n" + theServer.getGames().toString() + "\n" + "-------------"
                        + "\n" + "Enter 'B (price) (gamename) to make a bid on the game"
                        + "\n" + "Enter 'O (price) (gamename) to make a offer on the game");
                output.flush();
                break;

        }

    }

    public static void displayMessage(String inp) {
        //gets rendered on the server side each time
    }

}
