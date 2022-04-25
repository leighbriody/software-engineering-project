/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Server.Connection;
import Server.Server;
import java.io.PrintWriter;

/**
 *
 * @author Leigh Briody
 */
public class ListGamesCommand implements Command {

    @Override
    public void doAction(String input, Server theServer, PrintWriter output, Connection c) {

        //get all games on the server and store as a string for ease of use
        String allGames = theServer.getListOfGames();

        String allGamesDisplayString = "Here is a list of all the games " + "\n";

        //format the string
        allGamesDisplayString += allGames;
        allGamesDisplayString += getFunctionailityMenu();

        //send to client
        output.println((String) allGamesDisplayString);
        output.flush();
       
    }

    public static String getFunctionailityMenu() {
        return "\n"
                + "********* MENU OPTIONS *********" + "\n"
                + "Enter 'B (price) (gamename) to make a bid on the game"
                + "\n" + "Enter 'O (price) (gamename) to make a offer on the game"
                + "\n" + "--- If you already have a current bid or offer --- "
                + "\n" + "Enter 'O (price) (gamename) to change offer price "
                + "\n" + "Enter 'B (price) (gamename) to change bid price "
                + "\n" + "Enter 'B 0  (gamename) to cancle bid  "
                + "\n" + "Enter 'O  0  (gamename) to cancle offer  "
                + "\n" + "***********************************";
    }

}
