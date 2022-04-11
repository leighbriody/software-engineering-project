/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.DynamicArray;

import Server.Bid;
import Server.DynamicBidsArray;
import Server.DynamicBidsArray2;

/**
 *
 * @author Leigh Briody
 */
public class TestApp {

    public static void main(String[] args) {

        DynamicBidsArray2 arr = new DynamicBidsArray2();

        Bid bid1 = new Bid(100, "leigh@gmail.com"); //
        Bid bid2 = new Bid(30, "jason@gmail.com"); //
        Bid bid3 = new Bid(60, "tony@gmail.com"); //
        Bid bid4 = new Bid(100, "jack@gmail.com"); //
        Bid bid5 = new Bid(60, "paul@gmail.com"); //
        Bid bid6 = new Bid(30, "redzer@gmail.com");
        Bid bid7 = new Bid(30, "dani@gmail.com"); //
        Bid bid8 = new Bid(65, "deni@gmail.com"); //
        Bid bid9 = new Bid(100, "paulito@gmail.com"); //
        Bid bid10 = new Bid(15, "jessie@gmail.com"); 
        Bid bid11 = new Bid(105, "anto@gmail.com"); //
        Bid bid12 = new Bid(100, "daisy@gmail.com"); //
        //Bid bid5 = new Bid(100, "jack@gmail.com");

        arr.addElement(bid1);
        arr.addElement(bid2);
        arr.addElement(bid3);
        arr.addElement(bid4);
        arr.addElement(bid5);
        arr.addElement(bid6);
        arr.addElement(bid7);
        arr.addElement(bid8);
        arr.addElement(bid9);
        arr.addElement(bid10);
        arr.addElement(bid11);
        arr.addElement(bid12);

        //  arr.addElement(bid5);
        System.out.println("Unsorted");
        arr.printElements();
        System.out.println();

        System.out.println("Sorted");
        arr.sortArray();
        arr.printElements();

    }
}
