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

    private boolean loggedIn = false;
    private String userEmail = "";

   
    
    public static void main(String[] args) {
        //create new instance of client
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

            //need to get the users username
            while (!loggedIn) {
                //display login and wait till logged in
                displayLogin();
                System.out.println("Enter email to login");
                message = keyboard.nextLine();
                if (handleLogin(message)) {
                    //let the server know we have logged in
                    output.println("loggedin");
                    output.flush();
                }
            }

            //get response and start thread so we can keep listening
            Response resp = new Response(input, this.userEmail);
            resp.start();

            while (!message.equals(".")) {
                //if user is logged in  we can proceed with extra functionailty , until then display login loopp
                if (this.loggedIn) {
                    message = keyboard.nextLine();
                    // Exchange messages with provider
                    output.println(message + " " + this.userEmail);
                    output.flush();
                }
            }
            resp.stopResponse();
            resp.join();
            dataSocket.close();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    //This will display the login message to the user
    public static void displayLogin() {
        System.out.println("Welcome to leighs video game store");
        System.out.println("-- Enter your email below to login -- ");

    }

    //Thos will handle the validation of a correct email string
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
