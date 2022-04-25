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
public class MakeBidCommand implements Command {

    @Override
    public void doAction(String input, Server theServer, PrintWriter output, Connection c) {

        String[] choices = input.split(" ");

        String response = "";
        //Set bid details
        int bidPrice = Integer.parseInt(choices[1]);
        String gameNameBid = choices[2];
        String userEmailOffer = choices[choices.length - 1];

        // if the price is 0 we want to cancel bid
        if (bidPrice == 0) {
            //cancle
            theServer.cancleUsersBid(gameNameBid, userEmailOffer);
            //set response
            response = "You have cancled your bid for the game " + gameNameBid;
        } else {
            //Make bid
            theServer.bidOnGame(bidPrice, gameNameBid, userEmailOffer);
            response = "You have made a bid of " + bidPrice + " for the game " + gameNameBid;
        }

        //want to atatch all games and menu to response for client
        response += "\n" + theServer.getListOfGames();;
        response += getFunctionailityMenu();
        //output message to client
        output.println((String) response);
        output.flush();

        //display the order book to the server
        System.out.println(theServer.getGamesOrderBook(gameNameBid));
    }

}
