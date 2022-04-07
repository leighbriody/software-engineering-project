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
    }

    //getters and setters
    public String getGameName() {
        return gameName;
    }

    public Map<String, Integer> getAllBids() {
        return orderBook.getBids();
    }

    public Map<String, Integer> getAllOffers() {
        return orderBook.getOffers();
    }

    public void makeBid(int bidPrice, String userEmail , Server theServer) {

        if (this.bestBid < bidPrice) {
            this.bestBid = bidPrice;
            this.orderBook.makeBid(bidPrice, userEmail);
            //update observers
            theServer.notifyObservers("The best bid for the game " + this.gameName + " has now changed to " + this.bestBid);
        } else {
            this.orderBook.makeBid(bidPrice, userEmail);
        }

    }
    
  
    public void cancleUsersBid(String userEmail){
        //need to remove the bid from the array
        this.orderBook.cancleUsersBid(userEmail);
    }
    
    public void cancleUsersOffer(String userEmail){
        //need to remove the bid from the array
        this.orderBook.cancleUsersOffer(userEmail);
    }

    public void makeOffer(int offerPrice, String userEmail , Server theServer) {

        //if the best offer changes we set best offer
        if (this.bestOffer < offerPrice) {
            this.bestOffer = offerPrice;
            this.orderBook.makeOffer(offerPrice, userEmail);
            //update obsevrers of best price
            theServer.notifyObservers("The best offer for the game " + this.gameName + " has now changed to " + offerPrice);
        } else {
            this.orderBook.makeOffer(offerPrice, userEmail);
        }

    }

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
