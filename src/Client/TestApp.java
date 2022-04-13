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
