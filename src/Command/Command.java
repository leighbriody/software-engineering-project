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
public interface Command {
    

    public void doAction(String input ,Server theServer , PrintWriter output, Connection c);
}
