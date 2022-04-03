/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.util.Scanner;

/**
 *
 * @author Leigh Briody
 */
public class Response extends Thread {
    
    private Scanner input;
    private volatile boolean stop = false;

    public Response(Scanner s)
    {
        input = s;
    }

    @Override
    public void run()
    {
        String response = "";
        while (!stop)
        {
            response = input.nextLine();
           // System.out.println("Response: " + response);
            System.out.println(response);

        }

    }
    
    public synchronized void stopResponse() {
        stop = true;
    }
}
