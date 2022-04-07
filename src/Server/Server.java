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

    //Possibly need to change to dynamic array
    private static ArrayList<Game> games;

    public static void main(String[] args) {
        games = ainitGames();
        new Server();

    }

    @Override
    public void notifyObservers(Object arg) {
        setChanged();
        super.notifyObservers(arg);
    }

    public Server() {
        try {

            // allGames.add(cod);
            // allGames.add(battlefield);
            // Step 1) Set up a connection socket for other programs to connect to
            ServerSocket listeningSocket = new ServerSocket(Details.LISTENING_PORT);

            boolean continueRunning = true;

            while (continueRunning) {
                // Step 2) wait for incoming connection and build communications link
                Socket dataSocket = listeningSocket.accept();

                // create a new connection, pass it socket and then register it with this instance
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

    public ArrayList<Game> getGames() {
        return games;
    }

    public void bidOnGame(int bidPrice, String gameName, String userEmail) {
        for (Game g : games) {
            if (g.getGameName().equalsIgnoreCase(gameName)) {
                g.makeBid(bidPrice, userEmail);
            }

        }

    }

    public int getGamesBestOffer(String gameName) {
        int bestOffer = -1;
        for (Game g : games) {
            if (g.getGameName().equalsIgnoreCase(gameName.trim())) {
                bestOffer = g.getBestOffer();
            }

        }
        return bestOffer;

    }

    public void makeOfferForGame(int offerPrice, String gameName, String userEmail) {
        for (Game g : games) {
            if (g.getGameName().equalsIgnoreCase(gameName)) {
                g.makeOffer(offerPrice, userEmail);
            }

        }

    }

    public String getGamesOrderBook(String gameName) {
        for (Game g : games) {
            if (g.getGameName().trim().equalsIgnoreCase(gameName)) {
                return g.displayOrderBook(g.getGameName());
            }
        }

        return "order book not found";
    }

    public void deleteGame(String gameName) {
        games.remove(0);

    }

    public static ArrayList<Game> ainitGames() {
        ArrayList<Game> allGames = new ArrayList<>();
        allGames.add(new Game("fifa"));
        allGames.add(new Game("call-of-duty"));
        allGames.add(new Game("spider-man"));
        return allGames;
    }

}
