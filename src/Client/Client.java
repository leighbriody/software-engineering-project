/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Leigh Briody
 */
public class Client {

    private static int allClientsId;
    private static int id;
    private boolean loggedIn = false;
    private String userEmail = "";

    public static void main(String[] args) {

        new Client();
    }

    public Client() {
        try {

            // Step 1 (on consumer side) - Establish channel of communication
            Socket dataSocket = new Socket("localhost", Server.Details.LISTENING_PORT);

            // Step 3) Build output anhed input objects
            OutputStream out = dataSocket.getOutputStream();
            PrintWriter output = new PrintWriter(new OutputStreamWriter(out));

            InputStream in = dataSocket.getInputStream();
            Scanner input = new Scanner(new InputStreamReader(in));

            Scanner keyboard = new Scanner(System.in);
            String message = "";

            //display the welcome message
            // like before, except pass the input stream to another thread to print out incoming messages
            Response resp = new Response(input);
            resp.start();

            while (!message.equals(".")) {
                //if user is logged in
                if (this.loggedIn) {

                    message = keyboard.nextLine();
                    // Exchange messages with provider
                    output.println(message + " " + this.userEmail);
                    output.flush();
                } else {
                    displayLogin();
                    System.out.println("Enter email to login");
                    message = keyboard.nextLine();
                    if (handleLogin(message)) {
                        //let the server know we have logged in
                        output.println("loggedin");
                        output.flush();
                    }

                }
            }
            resp.stopResponse();
            resp.join();
            dataSocket.close();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void displayLogin() {
        System.out.println("Welcome to leighs video game store");
        System.out.println("-- Enter your email below to login -- ");

    }

    public boolean handleLogin(String email) {

        boolean acceptedEmail = false;
        //check if its a valid email and if so login and set logged in to true
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        if (pat.matcher(email).matches()) {
            this.loggedIn = true;
            this.userEmail = email;
            acceptedEmail = true;
        }
        return acceptedEmail;
    }

    public static void displayWelcomeMessage() {
        System.out.println("Hello and welcome to leighs video game store");
        System.out.println("-------------------------------------------");
        System.out.println("Below are your options of functionaility");
        System.out.println("1) Enter 'listgames' to view all the games we have");
    }
}
