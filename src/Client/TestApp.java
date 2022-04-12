/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Server.Bid;
import Server.Book;
import Server.DynamicBidsArray2;
import Server.DynamicOffersArray2;
import java.util.ArrayList;
import Server.Game;
import Server.Offer;
import Server.Server;
import java.util.List;

/**
 *
 * @author Leigh Briody
 *
 *
 */
public class TestApp {

    public static void main(String[] args) {

        DynamicOffersArray2 arr = new DynamicOffersArray2();
        DynamicBidsArray2 arr2 = new DynamicBidsArray2();
        

        Offer o1 = new Offer(20, "leigh");
        Offer o2 = new Offer(30, "jack");
        Offer o3 = new Offer(15, "paul");
        Offer o7= new Offer(30, "jack");
        arr.addElement(o1);
        arr.addElement(o2);
         arr.addElement(o3);
        arr.addElement(o7);
        
        Bid b1 = new Bid(20 , "osama");
        Bid b2 = new Bid(30 , "sam");
        arr2.addElement(b2);
        arr2.addElement(b1);
        
        System.out.println("Offers");
        arr.printElements();
        System.out.println();
        
        System.out.println("Bids");
        arr2.printElements();
    }

}

class Student {

    private String id;
    private String name;
    private int age;
    private Character grade;

    Student(String id, String name, int age, Character grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public Character getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
