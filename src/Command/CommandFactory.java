/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Server.Connection;
import Server.Server;

/**
 *
 * @author Leigh Briody
 */
public class CommandFactory {
     public static Command createCommand(String decision) {
        Command c = null;
        if(decision == null){
             decision = "";
        }
         
        switch (decision) {
            case "listgames":
                 c = new ListGamesCommand();
                break;
                 case "loggedin":
                 c = new LoggedInCommand();
                break;
                 case "O":
                 c = new MakeOfferCommand();
                break;
                 case "o":
                 c = new MakeOfferCommand();
                break;
                 case "B":
                 c = new MakeBidCommand();
                break;
                 case "b":
                 c = new MakeBidCommand();
                break;
            default:
                c = new NoActionSuppliedCommand();
        }

        return c;
    }
}
