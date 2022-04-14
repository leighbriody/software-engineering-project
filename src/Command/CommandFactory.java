/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

/**
 *
 * @author Leigh Briody
 */
public class CommandFactory {
     public static Command createCommand(String action) {
        Command c;
        if(action == null){
             action = "";
        }
        
        switch (action) {
            case "login":
                
                break;
            case "register":
           
                break;
            case "update":
              
                break;
            case "searchCustomer":
               
                break;
            case "logout":
               
                break;
            case "delete":
               
                break;
            default:
                c = new NoActionSuppliedCommand();
        }

        return c;
    }
}
