/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.ArrayList;

/**
 *
 * @author Leigh Briody
 */
public class DynamicOffersArray2 {

    private Offer array[];
    // holds the current size of array
    private int size;
    // holds the total capacity of array
    private int capacity;

    // default constructor to initialize the array and values
    public DynamicOffersArray2() {
        array = new Offer[2];
        size = 0;
        capacity = 2;
        populateNullValues();
    }

    // to add an element at the end
    public void addElement(Offer element) {

        String username = element.getUsername();

        // double the capacity if all the allocated space is utilized
        if (size == capacity) {
            ensureCapacity(2);
        }
        //want to check if they currently have a bid
        if (hasUserAlreadyOffered(username)) {
            changeUsersOfer(element);
        } else {

           

           
            array[0] = element;
            size++;
        }

        //sort array
        populateNullValues();
        sortArray();
    }

    public void cancleUserOffer(String userEmail) {

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

    public void populateNullValues() {

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = new Offer(0, "undefined");
            }
        }

    }

    public int getCurrentBestOffer() {
        return array[0].getPrice();
    }

    public ArrayList<Integer> getAllOfferValues() {

        ArrayList<Integer> offers = new ArrayList<>();
        for (Offer o : array) {
            try {
                //only if its != 0 as the 0 ones are a placeholder
                if (o.getPrice() != 0) {
                    offers.add(o.getPrice());
                }

            } catch (Exception e) {

            }

        }

        return offers;
    }

    public void changeUsersOfer(Offer element) {
        //find bid and change it
        String username = element.getUsername();
        int offerPrice = element.getPrice();

        for (Offer o : array) {
            try {
                if (o.getUsername().equalsIgnoreCase(username)) {
                    o.setPrice(offerPrice);
                }
            } catch (Exception e) {
                //ignore
            }
        }
    }

    // to add an element at a particular index
    public void addElement(int index, Offer element) {
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

        //sort array
        sortArray();
    }

    public boolean hasUserAlreadyOffered(String username) {
        boolean flag = false;

        for (Offer o : array) {
            try {
                if (o.getUsername().equalsIgnoreCase(username)) {
                    flag = true;
                }
            } catch (Exception e) {
                //ignore 
            }
        }

        return flag;
    }

    // to get an element at an index
    public Offer getElement(int index) {
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
        Offer temp[] = new Offer[capacity * minCapacity];

        //init temp array to prevent errors
        for (int i = 0; i < temp.length; i++) {
            temp[i] = new Offer(0, null);
        }

        for (int i = 0; i < capacity; i++) {
            temp[i] = array[i];
        }
        array = temp;
        capacity = capacity * minCapacity;
        
        //sort arrat 
         sortArray();
    }

    /*
    *  Trim the capacity of dynamic array to the current size. i.e. remove unused space
     */
    public void trimToSize() {
        System.out.println("Trimming the array");
        Offer temp[] = new Offer[size];
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
        for (Offer b : array) {
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

    public void mergeSort(Offer[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Offer[] l = new Offer[mid];
        Offer[] r = new Offer[n - mid];

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
            Offer[] a, Offer[] l, Offer[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].getPrice() <= r[j].getPrice()) {
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
