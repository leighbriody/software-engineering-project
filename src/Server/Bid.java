/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author Leigh Briody
 */
public class Bid {

    //set variables
    private int price;
    private String username;

    public Bid(int price, String username) {
        this.price = price;
        this.username = username;
    }

    //default
    Bid() {
        this.price = 0;
        this.username = "";
    }

    //getters and setters 
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Bid{" + "price=" + price + ", username=" + username + '}';
    }

}
