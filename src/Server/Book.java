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

    //Fields which will hold all the bids and offers
    //private ArrayList<Integer> bids;
    // private ArrayList<Integer> offers;
    //key value , key is user and value is bid
    private Map<String, Integer> mapBids;
    private Map<String, Integer> mapOffers;

    //constructor
    public Book() {

        //Bids needs to be sorted ascending
        this.mapBids = new HashMap<String, Integer>();

        //offers needs to be sorted descending
        this.mapOffers = new HashMap<String, Integer>();
    }

    //getters and setters
    public void makeBid(int price, String userEmail) {
        this.mapBids.put(userEmail, price);
        
        //returns the best bid
        //get best bid
    

    }

    public void makeTrade() {

        //this is when the best bid matches the best offer
        //we want to make the trade
        Map.Entry<String, Integer> entry = mapBids.entrySet().iterator().next();
        String bidUser = entry.getKey();
        Integer bidValue = entry.getValue();

        //Offers
        Map.Entry<String, Integer> entry2 = mapOffers.entrySet().iterator().next();
        String offerUser = entry2.getKey();
        Integer offerValue = entry2.getValue();

        //check 
        if (bidValue >= offerValue) {
            //then we want to remove the best bid and offer
            this.mapBids.remove(bidUser);
            this.mapOffers.remove(offerUser);
        }

    }

    public void makeOffer(int price, String userEmail) {
        this.mapOffers.put(userEmail, price);

        //return the order books bids and offers so we can display to server.
    }

    public void cancleUsersBid(String userEmail) {
        this.mapBids.remove(userEmail);
    }

    public void cancleUsersOffer(String userEmail) {
        this.mapOffers.remove(userEmail);
    }

    public String displayOrderBook(String gameName) {
        //Set output string

        String output = "\n" + "-- Order Book For Game : " + gameName + "--" + "\n" + "BIDS" + "\t" + "OFFERS";

        //cast the bids and offers to integer arrays
        Set<String> bidsKeySet = this.mapBids.keySet();
        Set<String> offers = this.mapOffers.keySet();

        // Creating an ArrayList of values
        Collection<Integer> offerValues = this.mapOffers.values();
        Collection<Integer> bidValues = this.mapBids.values();

        // Creating an ArrayList of values
        ArrayList<Integer> bidsArrayList = new ArrayList<>(bidValues);
        ArrayList<Integer> offersArrayList = new ArrayList<>(offerValues);

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

    //get
    public Map<String, Integer> getBids() {
        return mapBids;
    }

    public Map<String, Integer> getOffers() {
        return mapOffers;
    }

}
