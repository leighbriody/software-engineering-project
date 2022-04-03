/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Server.Book;
import java.util.ArrayList;
import Server.Game;

/**
 *
 * @author Leigh Briody
 */
public class TestApp {

    public static void main(String[] args) {

        ArrayList<Game> allGames = new ArrayList<>();
        Game callOfDurty = new Game("call-of-duty");
        allGames.add(callOfDurty);

        
        Book b = new Book();
        b.makeBid(50);
        b.makeOffer(40);

        b.displayOrderBook();

    }
}
