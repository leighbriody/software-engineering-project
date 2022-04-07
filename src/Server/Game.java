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

    public void makeBid(int bidPrice, String userEmail) {

        if (this.bestBid < bidPrice) {
            this.bestBid = bidPrice;
            this.orderBook.makeBid(bidPrice, userEmail);
        } else {
            this.orderBook.makeBid(bidPrice, userEmail);
        }

    }

    public void makeOffer(int offerPrice, String userEmail) {

        //if the best offer changes we set best offer
        if (this.bestOffer < offerPrice) {
            this.bestOffer = offerPrice;
            this.orderBook.makeOffer(offerPrice, userEmail);
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
