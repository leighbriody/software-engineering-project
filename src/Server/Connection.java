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

    //Create our variables
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

                //pass input and output to handle input method which deals with logic
                handleInput(inp, this.o, this.output, this);

                //  o.notifyObservers(inp);
            }
            //remove observer when user wants to exit and enters "."
            o.deleteObserver(this);
            dataSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // called by notifyObservers, write arg to this connection
    /*
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Update...");
        if (arg instanceof String) {
            output.println((String) arg);
            output.flush();
            System.out.println("Sending " + arg);
        }
    }
*/
    
    
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
        String response = "";

        //split the choice up into a character array
        //last element of array will always be the username
        //[B , 100 , GAME , username] OR [O , 100 , GAME , username]
        String[] choices = inp.split(" ");

        
        switch (choices[0].trim()) {
            //User wishes to login
            case "loggedin":
                output.println((String) "Hello and welcome to leighs video game store"
                        + "\n" + "-----------------"
                        + "\n" + "Below are your options of functionaility"
                        + "\n" + "1) Enter 'listgames' to view all the games we have" + "");

                output.flush();
                break;
            //User wishes to see the list of games
            case "listgames":

                String allGamesDisplayString = "Here is a list of all the games " + "\n";

                String allGames = theServer.getListOfGames();
                allGamesDisplayString += allGames;
                allGamesDisplayString += "\n" + "Enter 'B (price) (gamename) to make a bid on the game"
                        + "\n" + "Enter 'O (price) (gamename) to make a offer on the game";

                output.println((String) allGamesDisplayString);
                output.flush();
                break;
            //User wishes to make an order
            case "O":
                //bid details
                int offerPrice = Integer.parseInt(choices[1].trim());
                String gameName = choices[2].trim();
                String userEmail = choices[choices.length - 1];

                //if price is 0 we want to cancle 
                if (offerPrice == 0) {
                    //cancle
                    theServer.cancleUsersOffer(gameName, userEmail);
                    response = "You have cancled your offer for the game " + gameName;
                } else {
                    //make offer
                    //make the bid
                    theServer.makeOfferForGame(offerPrice, gameName, userEmail);
                    response = "You have made an offer of " + offerPrice + " for the game name " + gameName;
                }

                output.println((String) response);

                //also need to send out the list of games again and maybe functionailty ? 
                output.flush();

                //want to to display order book to the log
                System.out.println(theServer.getGamesOrderBook(gameName));
                break;
            //user wishes to make a bid
            case "B":

                //Set bid details
                int bidPrice = Integer.parseInt(choices[1]);
                String gameNameBid = choices[2];
                String userEmailOffer = choices[choices.length - 1];

                // if the price is 0 we want to cancel bid
                if (bidPrice == 0) {
                    //cancle
                    theServer.cancleUsersBid(gameNameBid, userEmailOffer);
                    //set response
                    response = "You have cancled your bid for the game " + gameNameBid;
                } else {
                    //Make bid
                    theServer.bidOnGame(bidPrice, gameNameBid, userEmailOffer);
                    response = "You have made a bid of " + bidPrice + " for the game " + gameNameBid;
                }

                //output message to client
                output.println((String) response);
                output.flush();

                //display the order book to the server
                System.out.println(theServer.getGamesOrderBook(gameNameBid));
                break;

        }

    }

    public static void displayMessage(String inp) {
        //gets rendered on the server side each time
    }

}
