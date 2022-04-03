/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.ArrayList;

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

    public ArrayList<Integer> getAllBids() {
        return orderBook.getBids();
    }

    public ArrayList<Integer> getAllOffers() {
        return orderBook.getOffers();
    }

    public void makeBid(int bidPrice , String userEmail) {
     
        //if it is the best bid
            //
        //if its not the best bid 
            //kept in a book
        this.orderBook.makeBid(bidPrice ,userEmail);
    }

    public void makeOffer(int offerPrice , String userEmail) {

        if(this.bestOffer < offerPrice){
            this.bestOffer = offerPrice;
        }else {
              this.orderBook.makeOffer(offerPrice , userEmail);
        }
      
    }

    public void displayOrderBook() {

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
