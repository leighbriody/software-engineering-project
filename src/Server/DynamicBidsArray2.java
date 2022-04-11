/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import static Server.DynamicBidsArray.merge;
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
        array = new Bid[2];
        size = 0;
        capacity = 2;
    }
    
    

    // to add an element at the end
    public void addElement(Bid element) {
        // double the capacity if all the allocated space is utilized
        if (size == capacity) {
            ensureCapacity(2);
        }
        array[size] = element;
        size++;
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
    }

    /* method to increase the capacity, if necessary, to ensure it can hold at least the 
    *  number of elements specified by minimum capacity arguement
     */
    public void ensureCapacity(int minCapacity) {
        Bid temp[] = new Bid[capacity * minCapacity];
       
        //init temp array to prevent errors
        for(int i = 0; i < temp.length; i ++){
            temp[i] = new Bid(0 , null);
        }
        
        for (int i = 0; i < capacity; i++) {
            temp[i] = array[i];
        }
        array = temp;
        capacity = capacity * minCapacity;
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
