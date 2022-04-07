/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Server.Book;
import java.util.ArrayList;
import Server.Game;
import java.util.List;

/**
 *
 * @author Leigh Briody
 *
 *
 */
public class TestApp {

    public static void main(String[] args) {
        
        ArrayList<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(2);
     

        List<Student> students = new ArrayList<>();
        students.add(new Student("S101", "John", 8, '4'));
        students.add(new Student("S102", "Leo", 10, '6'));
        students.add(new Student("S103", "Mary", 5, '2'));
        students.add(new Student("S104", "Lisa", 6, '3'));

        ArrayList<Game> allGames = new ArrayList<>();
        Game callOfDurty = new Game("call-of-duty");
        allGames.add(callOfDurty);

        //Make bids and offers
        callOfDurty.makeBid(100, "john@gmai.com");
        callOfDurty.makeBid(99, "gary@gmail.com");
        callOfDurty.makeBid(97, "paul@gmail.com");
        callOfDurty.makeOffer(102, "lbriody@gmail.com");
        callOfDurty.makeOffer(103, "dennis@gmail.com");
        
        System.out.println("Get order book");
        System.out.println(callOfDurty.displayOrderBook());
        
        

        /*
        System.out.println("Get call of duty order book");
        System.out.println(callOfDurty.displayOrderBook());
        System.out.println("");
        System.out.println("----");

        System.out.println("Order book for call of duty");

        System.out.println("-----------------------------------------------------------------------------");

        System.out.printf("%10s %20s", "BIDS", "OFFERS");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        for (Student student : students) {
            System.out.format("%10s %20s",
                    student.getId(), student.getName());
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------------");

         */
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
