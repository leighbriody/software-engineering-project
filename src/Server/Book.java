/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Leigh Briody
 */
public class Book {

    //Hold all bids and offers within a dynamic array
    private DynamicBidsArray2 mapBids;
    private DynamicOffersArray2 mapOffers;

    public Book() {
        //Init these dynamic array when book object is init
        this.mapBids = new DynamicBidsArray2();
        this.mapOffers = new DynamicOffersArray2();
    }

    public void makeTrade(String gameName, Server theServer) {

        // System.out.println(this.mapOffers);
        Bid bidderMatched = this.mapBids.getElement(0);
        Offer offerMatched = this.mapOffers.getElement(this.mapOffers.getBestOfferIndex());

        //remove both elements
        this.mapBids.remove(0);
        this.mapOffers.removeValue(this.mapOffers.getBestOfferIndex());

        //add two new null elements
        this.mapBids.addElement(new Bid(0, null));
        this.mapOffers.addElement(new Offer(0, null));

        //notify them
        theServer.notifyObservers("test " + bidderMatched.getUsername());
         theServer.notifyObservers(offerMatched.getUsername());
        theServer.notifyObservers("A trade has been made for the game " + gameName + "The buyer : " + bidderMatched.getUsername() + " and the seller :" + offerMatched.getUsername());

    }

    //Adds a bid object to the dynamic array
    public void makeBid(int price, String userEmail) {
        this.mapBids.addElement(new Bid(price, userEmail));

        //
    }

    //Adds an offer object to the dynamic offers
    public void makeOffer(int price, String userEmail) {
        this.mapOffers.addElement(new Offer(price, userEmail));
    }

    //allows a user to cancle their bid (removes it from array)
    public void cancleUsersBid(String userEmail) {
        this.mapBids.cancleUserBid(userEmail);
    }

    //allows user to cancle their offer(removes it from array)
    public void cancleUsersOffer(String userEmail) {
        this.mapOffers.cancleUserOffer(userEmail);
    }

    public int getCurrentBestBid() {
        return mapBids.getCurrentBestBid();
    }

    public int getCurrentBestOffer() {
        return mapOffers.getCurrentBestOffer();
    }

    //Will return the games order book as a formatted string
    public String displayOrderBook(String gameName) {
        //Set output string
        String output = "\n" + "-- Order Book For Game : " + gameName + "--" + "\n" + "BIDS" + "\t" + "OFFERS";

        // Creating an ArrayList of values
        ArrayList<Integer> offersArrayList = this.mapOffers.getAllOfferValues();
        ArrayList<Integer> bidsArrayList = this.mapBids.getAllBidValues();

        //get the max value
        int MAX = Math.max(bidsArrayList.size(), offersArrayList.size());

        for (int i = 0; i <= MAX; i++) {
            String currentBid = "";
            String currentOffer = "";

            try {
                currentBid += String.valueOf(bidsArrayList.get(i));
            } catch (Exception e) {

            }

            try {
                currentOffer += String.valueOf(offersArrayList.get(i));
            } catch (Exception e) {

            }

            //ammend the output
            output += "\n" + currentBid + "\t" + currentOffer;

        }
        return output;
    }

}
