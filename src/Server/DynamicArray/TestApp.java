/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.DynamicArray;

import Server.Bid;
import Server.DyamicGameArray;
import Server.DynamicBidsArray;
import Server.DynamicBidsArray2;
import Server.DynamicOffersArray2;
import Server.Game;
import Server.Offer;

/**
 *
 * @author Leigh Briody
 */
public class TestApp {

    public static void main(String[] args) {

       DyamicGameArray arr= new DyamicGameArray();
       
       Game g1 = new Game("fifa");
       Game g2 = new Game("spiderman");
         Game g3 = new Game("call-of-duty");
       
       arr.addGame(g1);
       arr.addGame(g2);
       arr.addGame(g3);
       
       arr.printElements();
       

    }
}
