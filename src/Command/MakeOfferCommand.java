/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Server.Connection;
import static Server.Connection.getFunctionailityMenu;
import Server.Server;
import java.io.PrintWriter;

/**
 *
 * @author Leigh Briody
 */
public class MakeOfferCommand implements Command {

    @Override
    public void doAction(String input, Server theServer, PrintWriter output, Connection c) {
        //offer details

        String functionailityMenu = getFunctionailityMenu();
//get all games on the server and store as a string for ease of use
        String allGames = theServer.getListOfGames();
        String response = "";
        String[] choices = input.split(" ");

        int offerPrice = Integer.parseInt(choices[1].trim());
        String gameName = choices[2].trim();
        String userEmail = choices[choices.length - 1];

        //if price is 0 we want to cancle 
        if (offerPrice == 0) {
            //cancle
            theServer.cancleUsersOffer(gameName, userEmail);
            response = "You have cancled your offer for the game " + gameName;
        } else {
            //make offer
            //make the bid
            theServer.makeOfferForGame(offerPrice, gameName, userEmail);
            response = "You have made an offer of " + offerPrice + " for the game name " + gameName;
        }

        //Want to attatch all games and a menu with the response so they can see other options
        response += "\n" + allGames;
        response += functionailityMenu;
        output.println((String) response);

        //also need to send out the list of games again and maybe functionailty ? 
        output.flush();

        //want to to display order book to the log
        System.out.println(theServer.getGamesOrderBook(gameName));
    }

}
