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
public class DyamicGameArray {

    private Game array[];
    // holds the current size of array
    private int size;
    // holds the total capacity of array
    private int capacity;

    public DyamicGameArray() {
        array = new Game[2];
        size = 0;
        capacity = 2;
    }

    public void addGame(Game newGame) {
        // double the capacity if all the allocated space is utilized
        if (size == capacity) {
            ensureCapacity(2);
        }
        array[size] = newGame;
        size++;
    }

    // to add an element at a particular index
    public void addElement(int index, Game element) {
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
    public Game getElement(int index) {
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
        Game temp[] = new Game[capacity * minCapacity];

        //init temp array to prevent errors
        for (int i = 0; i < temp.length; i++) {
            temp[i] = new Game(null);
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
        Game temp[] = new Game[size];
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
        for (Game b : array) {
            try {
                System.out.println(b.toString());
            } catch (Exception e) {
                //null
            }

        }
    }

    public String getAllGamesAsString() {

        String output = "";
        for (Game g : array) {
            try {
                output += g.toString() + "\n";
            } catch (Exception e) {

            }

        }

        return output;
    }

    void bidOnGame(int bidPrice, String gameName, String userEmail, Server theServer) {
        for (Game g : array) {

            if (g.getGameName().equalsIgnoreCase(gameName)) {
                //pass instance of the server so we can call update if best bid chanegs
                g.makeBid(bidPrice, userEmail, theServer);
            }

        }
    }

    public int getGamesBestOffer(String gameName) {
        int bestOffer = -1;
        for (Game g : array) {
            if (g.getGameName().equalsIgnoreCase(gameName.trim())) {
                bestOffer = g.getBestOffer();
            }
        }
        return bestOffer;
    }

    void makeOfferForGame(int offerPrice, String gameName, String userEmail, Server theServer) {
        for (Game g : array) {

            if (g.getGameName().equalsIgnoreCase(gameName)) {
                //pass instance of the server so we can call update if best bid chanegs
                g.makeOffer(offerPrice, userEmail, theServer);
            }

        }

    }

    public String getGamesOrderBook(String gameName) {
        for (Game g : array) {
            if (g.getGameName().trim().equalsIgnoreCase(gameName)) {
                return g.displayOrderBook(g.getGameName());
            }
        }

        return "order book not found";
    }

    public void cancleUsersBid(String gameName, String userEmail) {
        for (Game g : array) {
            if (g.getGameName().equalsIgnoreCase(gameName)) {
                //found gamme
                g.cancleUsersBid(userEmail);
            }
        }
    }

    public void cancleUsersOffer(String gameName, String userEmail) {
        for (Game g : array) {
            if (g.getGameName().equalsIgnoreCase(gameName)) {
                //found gamme
                g.cancleUsersOffer(userEmail);
            }
        }
    }

    public Game getItem(int index) {
        return array[index];
    }

}
