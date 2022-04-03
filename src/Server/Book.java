/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Leigh Briody
 */
public class Book {
    
    //Fields which will hold all the bids and offers
    private ArrayList<Integer> bids;
    private ArrayList<Integer> offers;
    
    //key value , key is user and value is bid
    private Map<String ,Integer> mapBids;
     private Map<String ,Integer> mapOffers;

    //constructor
    public Book() {
       this.bids = new ArrayList<>();
       this.offers = new ArrayList<>();
       
       //map
       this.mapBids = new HashMap<String , Integer>();
       this.mapOffers = new HashMap<String , Integer>();
    }
    
    //getters and setters
    public void makeBid(int price , String userEmail){
        
        this.mapBids.put(userEmail, price);
       
   
    }
    
    public void makeOffer(int price , String userEmail){
        this.mapOffers.put(userEmail, price);
    }
    
    public void displayOrderBook(){
       
 
    }
    
    //get
    public ArrayList<Integer> getBids() {
        return bids;
    }

    public void setBids(ArrayList<Integer> bids) {
        this.bids = bids;
    }

    public ArrayList<Integer> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<Integer> offers) {
        this.offers = offers;
    }
    
    
    
    
    
    
}
