/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.DynamicArray;

/**
 *
 * @author Leigh Briody
 */
public class MyArrayList {

    private int[] theArray;
    private int numItems;

    public MyArrayList() {

        //default start at 10
        theArray = new int[10];
        numItems = 0;
    }

    public void addItem(int num) {
      
        if (numItems == theArray.length) {
            growArray();
        }
        theArray[numItems] = num;
        numItems++;
    }

    public void removeItem() {
        numItems--; // might first 0 out of element if security is a concert
    }

    public int getItem(int index) {
        return theArray[index];
    }

    public void growArray() {
        int[] tempArr = new int[theArray.length * 2];
        for (int i = 0; i < numItems; i++) {
            tempArr[i] = theArray[i];
            theArray = tempArr;
        }
    }

    public void displayArr() {
        for (Integer i : theArray) {
            System.out.println(i);
        }
    }
}
