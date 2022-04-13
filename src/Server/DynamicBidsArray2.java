/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import static Server.DynamicBidsArray.merge;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Leigh Briody
 */
public class DynamicBidsArray2 {

    private Bid array[];
    // holds the current size of array
    private int size;
    // holds the total capacity of array
    private int capacity;

    // default constructor to initialize the array and values
    public DynamicBidsArray2() {
        array = new Bid[4];
        size = 0;
        capacity = 2;
        populateNullValues();
    }

    // to add an element at the end
    public void addElement(Bid element) {

        String username = element.getUsername();

        // double the capacity if all the allocated space is utilized
        if (size == capacity) {
            ensureCapacity(2);
        }

        //want to check if they currently have a bid
        if (hasUserAlreadyBidded(username)) {
            changeUsersBid(element);
        } else {

            array[size] = element;
            size++;
        }

        //anytime we add we need to sort
        populateNullValues();
        sortArray();
    }

    public void changeUsersBid(Bid element) {
        //find bid and change it
        String username = element.getUsername();
        int bidPrice = element.getPrice();

        for (Bid b : array) {
            try {
                if (b.getUsername().equalsIgnoreCase(username)) {
                    b.setPrice(bidPrice);
                }
            } catch (Exception e) {
                //ignore
            }
        }
    }

    public boolean hasUserAlreadyBidded(String username) {
        boolean flag = false;

        for (Bid b : array) {
            try {
                if (b.getUsername().equalsIgnoreCase(username)) {
                    flag = true;
                }
            } catch (Exception e) {
                //ignore 
            }
        }

        return flag;
    }
    
    public int getCurrentBestBid(){
        return array[0].getPrice();
    }

    
    public void populateNullValues(){
       

        for(int i = 0; i< array.length ; i ++){
           if(array[i] == null){
               array[i] = new Bid(0 , "undefined");
           }
        }
        
   
    }
    public ArrayList<Integer> getAllBidValues() {

        ArrayList<Integer> bids = new ArrayList<>();
        for (Bid b : array) {
            try {
                //only if != 0 as the 0 values are placeholders for dynamic array
                if (b.getPrice() != 0) {
                    bids.add(b.getPrice());
                }

            } catch (Exception e) {

            }

        }

        return bids;
    }

    // to add an element at a particular index
    public void addElement(int index, Bid element) {
        // double the capacity if all the allocated space is utilized
        if (size == capacity) {
            ensureCapacity(2);
        }
        // shift all elements from the given index to right
        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        // insert the element at the specified index
        array[index] = element;
        size++;
    }

    // to get an element at an index
    public Bid getElement(int index) {
        return array[index];
    }

    public void cancleUserBid(String userEmail) {

        for (int i = 0; i < array.length; i++) {
            try {
                if (array[i].getUsername().equalsIgnoreCase(userEmail)) {
                    this.remove(i);
                }
            } catch (Exception e) {
                //ignore
            }
        }

     
    }

    // to remove an element at a particular index
    public void remove(int index) {
        if (index >= size || index < 0) {
            System.out.println("No element at this index");
        } else {
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            array[size - 1] = null;
            size--;
        }

        //sortArray(); needed ? 
    }

    /* method to increase the capacity, if necessary, to ensure it can hold at least the 
    *  number of elements specified by minimum capacity arguement
     */
    public void ensureCapacity(int minCapacity) {
        Bid temp[] = new Bid[capacity * minCapacity];

        //init temp array to prevent errors
        for (int i = 0; i < temp.length; i++) {
            temp[i] = new Bid(0, "undefined");
        }

        for (int i = 0; i < capacity; i++) {
            temp[i] = array[i];
        }
        array = temp;
        capacity = capacity * minCapacity;
        
         populateNullValues();
    }

    /*
    *  Trim the capacity of dynamic array to the current size. i.e. remove unused space
     */
    public void trimToSize() {
        System.out.println("Trimming the array");
        Bid temp[] = new Bid[size];
        for (int i = 0; i < size; i++) {
            temp[i] = array[i];
        }
        array = temp;
        capacity = array.length;

    }

    // to get the current size
    public int size() {
        return size;
    }

    // to get the current capacity
    public int capacity() {
        return capacity;
    }

    // method to print elements in array
    public void printElements() {
        for (Bid b : array) {
            try {
                System.out.println(b.toString());
            } catch (Exception e) {
                //null
            }

        }
    }

    //Methods to sort the array
    public void sortArray() {
        this.mergeSort(array, array.length);
    }
    //will put merge sort

    public void mergeSort(Bid[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Bid[] l = new Bid[mid];
        Bid[] r = new Bid[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public static void merge(
            Bid[] a, Bid[] l, Bid[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].getPrice() >= r[j].getPrice()) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }
}
