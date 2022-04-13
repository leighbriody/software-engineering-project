/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author Leigh Briody
 */
public class Server extends Observable {

    //Hold all games within the server on a dynamic array
    private static DyamicGameArray games = new DyamicGameArray();

    public static void main(String[] args) {
     
        //add 2 games to the server (add more in the future)
        games.addGame(new Game("fifa"));
        games.addGame(new Game("call-of-duty"));

        //create instance of the server
        new Server();
    }

    public Server() {
        try {
            //Set up a connection socket for other programs to connect to
            ServerSocket listeningSocket = new ServerSocket(Details.LISTENING_PORT);

            boolean continueRunning = true;

            while (continueRunning) {
                
                //Listen for incoming connection and build data socket
                Socket dataSocket = listeningSocket.accept();

                // Create connection and add obsrver as we want this connection (client) to listen
                Connection c = new Connection(dataSocket, this);
                addObserver(c);
                
                // start the thread
                Thread t = new Thread(c);
                t.start();

            }
            listeningSocket.close();

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    //Returns all the games on the server as a string
    public String getListOfGames() {
        return games.getAllGamesAsString();
    }

    //Allows a user to bid on a game
    public void bidOnGame(int bidPrice, String gameName, String userEmail) {
        games.bidOnGame(bidPrice, gameName, userEmail, this);
    }

    //Gets the current best offer for a game
    public int getGamesBestOffer(String gameName) {
        return games.getGamesBestOffer(gameName);
    }

    //Allows a user to make an offer for a game
    public void makeOfferForGame(int offerPrice, String gameName, String userEmail) {
            games.makeOfferForGame(offerPrice, gameName, userEmail, this);
    }

    //Gets a games order book as a string
    public String getGamesOrderBook(String gameName) {
        return games.getGamesOrderBook(gameName);
    }

    //Allows a user to cancle their bid 
    public void cancleUsersBid(String gameName, String userEmail) {
        games.cancleUsersBid(gameName, userEmail);
    }

    //allows a user to cancle their offer
    public void cancleUsersOffer(String gameName, String userEmail) {
        games.cancleUsersOffer(gameName, userEmail);
    }

    //Notify observers (clients) of changes
       @Override
    public void notifyObservers(Object arg) {
        setChanged();
        super.notifyObservers(arg);
    }
    
    
    
   

}
