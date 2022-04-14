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

    //fields
    private Scanner input;
    private volatile boolean stop = false;
    private static String username;

    public Response(Scanner s, String username) {
        input = s;
        this.username = username;
    }

    @Override
    public void run() {
        String response = "";
        while (!stop) {
            response = input.nextLine();
            handleResponse(response);

        }

    }

    public synchronized void stopResponse() {
        stop = true;
    }

    /**
     * *
     * This will handle the response we get back from the server //Based on if
     * it is the current user who bought or sold we can display that to them
     *
     * @param response
     */
    public static void handleResponse(String response) {

        //If response contains sucessfully bought and their username we can display ot to them as they are the user
        if (response.contains("you have successfully purchased")) {
            //get username at substring ssee if it contains users username
            String buyerEmail = response.substring(0, response.indexOf(" "));
            if (buyerEmail.equalsIgnoreCase(username)) {
                System.out.println(response);
            }
        } else if (response.contains("you have successfully sold")) {
            //get username at substring ssee if it contains users username
            String sellerEmail = response.substring(0, response.indexOf(" "));
            if (sellerEmail.equalsIgnoreCase(username)) {
                System.out.println(response);
            }
        } else {
            //otherwise thy can see the message
            System.out.println(response);
        }

    }

}
