/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Leigh Briody
 */
public class Game {

    //init fields
    private String gameName;
    private int bestBid;
    private int bestOffer;

    private Book orderBook;

    //constructor
    public Game(String gameName) {
        this.gameName = gameName;
        this.orderBook = new Book();
        this.bestOffer = -1;
    }

    Game() {
        this.gameName = "undefined";
        this.orderBook = new Book();
        this.bestOffer = -1;
    }

    //getters and setters
    public String getGameName() {
        return gameName;
    }

    /**
     * Checks if a user has offered
     * @param username
     * @return 
     */
    public boolean hasUserAlreadyOffered(String username) {
        return this.orderBook.hasUserAlreadyOffered(username);
    }
    
    /***
     * Check if a user has bidded
     * @param username
     * @return 
     */
    public boolean hasUserAlreadyBidded(String username) {
        return this.orderBook.hasUserAlreadyBidded(username);
    }

    /***
     * Makes a bid on a game
     * @param bidPrice
     * @param userEmail
     * @param theServer 
     */
    public void makeBid(int bidPrice, String userEmail, Server theServer) {

        //each time someone makes a bid or an offer we need to check if the best bid is the same or greater than the offer ? 
        //if bidPrice >= best offer
        //getCurrentBestBid will return the current best bid
        if (this.bestBid < bidPrice) {

            changeBestBid(bidPrice, theServer);

            //check if trade is made  
            this.orderBook.makeBid(bidPrice, userEmail);

            if (isTradeViable()) {
                this.orderBook.makeTrade(gameName, theServer, this.bestBid, this.bestOffer);

                //then need to ger current best offer and best bid and set it
                int newBestOffer = this.orderBook.getCurrentBestOffer();
                int newBestBid = this.orderBook.getCurrentBestBid();

                //change
                changeBestBid(newBestBid, theServer);
                changeBestOffer(newBestOffer, theServer);

            }

        } else {
            this.orderBook.makeBid(bidPrice, userEmail);
        }
    }

    /***
     * Makes an offer on a game
     * @param offerPrice
     * @param userEmail
     * @param theServer 
     */
    public void makeOffer(int offerPrice, String userEmail, Server theServer) {
        //each time someone makes a bid or an offer we need to check if the best bid is the same or greater than the offer ?  
        if (this.bestOffer > offerPrice || this.bestOffer == -1) {

            changeBestOffer(offerPrice, theServer);

            this.orderBook.makeOffer(offerPrice, userEmail);
            //update obsevrers of best price
            if (isTradeViable()) {
                this.orderBook.makeTrade(gameName, theServer, this.bestBid, this.bestOffer);
            }

        } else {
            this.orderBook.makeOffer(offerPrice, userEmail);
        }
    }


    /**
     * Checks to see if best bid matches best offer and if a trade is available
     * @return 
     */
    public boolean isTradeViable() {
        //take the best bid
        if (bestBid >= bestOffer && bestOffer != -1) {
            return true;
        } else {
            return false;
        }
    }

    /***
     * Cancles users bid
     * @param userEmail 
     */
    public void cancleUsersBid(String userEmail) {
        //need to remove the bid from the array
        this.orderBook.cancleUsersBid(userEmail);
    }

    /***
     * Cancle uses offer
     * @param userEmail 
     */
    public void cancleUsersOffer(String userEmail) {
        //need to remove the bid from the array
        this.orderBook.cancleUsersOffer(userEmail);
    }

    /***
     * Returns the order book as a nicely formatted string table
     * @param gameName
     * @return 
     */
    public String displayOrderBook(String gameName) {

        return this.orderBook.displayOrderBook(gameName);

    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getBestBid() {
        return bestBid;
    }

    public void setBestBid(int bestBid) {
        this.bestBid = bestBid;
    }

    public int getBestOffer() {
        return bestOffer;
    }

    public void changeBestBid(int newBestBid, Server theServer) {
        this.bestBid = newBestBid;
        theServer.notifyObservers("The best bid for the game " + this.gameName + " has now changed to " + this.bestBid);
    }

    public void changeBestOffer(int newBestOffer, Server theServer) {
        this.bestOffer = newBestOffer;
        theServer.notifyObservers("The best offer for the game " + this.gameName + " has now changed to " + this.bestOffer);
    }

    public void setBestOffer(int bestOffer) {
        this.bestOffer = bestOffer;
    }

    public Book getOrderBook() {
        return orderBook;
    }

    public void setOrderBook(Book orderBook) {
        this.orderBook = orderBook;
    }

    //to string and such
    @Override
    public String toString() {
        return "Game{" + "gameName=" + gameName + ", bestBid=" + bestBid + ", bestOffer=" + bestOffer + '}';
    }

}
