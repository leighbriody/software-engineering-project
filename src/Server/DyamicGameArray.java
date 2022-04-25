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

    /***
     * Adds a game to the game array
     * @param newGame 
     */
    public void addGame(Game newGame) {
        // double the capacity if all the allocated space is utilized
        if (size == capacity) {
            ensureCapacity(2);
        }
        array[size] = newGame;
        size++;
    }

    
    /***
     * Adds a game to the array given an index
     * @param index
     * @param element 
     */
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

    
    /***
     *  to get an element at an index
     * @param index
     * @return 
     */
    public Game getElement(int index) {
        return array[index];
    }

    
    /***
     * to remove an element at a particular index
     * @param index 
     */
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

   

    /****
     *  /* method to increase the capacity, if necessary, to ensure it can hold at least the 
    *  number of elements specified by minimum capacity arguement
     * @param minCapacity 
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

    
    /***
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

    
    /***
     *  to get the current size
     * @return 
     */
    public int size() {
        return size;
    }

    
    /***
     * to get the current capacity
     * @return 
     */
    public int capacity() {
        return capacity;
    }

   
    /***
     *  method to print elements in array
     */
    public void printElements() {
        for (Game b : array) {
            try {
                System.out.println(b.toString());
            } catch (Exception e) {
                //null
            }

        }
    }

    
    /***
     * returns all the games in the array as a string
     * @return 
     */
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

    /**
     * Checks if a user has already bidded on the game  returns true if so
     * @param gameName
     * @param username
     * @return 
     */
    public boolean hasUserAlreadyBidded(String gameName, String username) {
        for (Game g : array) {
            if (g.getGameName().equalsIgnoreCase(gameName)) {
                //pass instance of the server so we can call update if best bid chanegs
                return g.hasUserAlreadyBidded(username);
            }
        }
        return true;
    }

    /***
     * Checks if a user has already offered on a game , returns true if so
     * @param gameName
     * @param username
     * @return 
     */
    public boolean hasUserAlreadyOffered(String gameName, String username) {
        for (Game g : array) {
            if (g.getGameName().equalsIgnoreCase(gameName)) {
                //pass instance of the server so we can call update if best bid chanegs
                return g.hasUserAlreadyOffered(username);
            }
        }

        return true;
    }

    /***
     * Allows a user to bid on a game
     * @param bidPrice
     * @param gameName
     * @param userEmail
     * @param theServer 
     */
    void bidOnGame(int bidPrice, String gameName, String userEmail, Server theServer) {
        for (Game g : array) {
            if (g.getGameName().equalsIgnoreCase(gameName)) {
                //pass instance of the server so we can call update if best bid chanegs
                g.makeBid(bidPrice, userEmail, theServer);
            }

        }
    }

    /***
     * Gets the best offer for game
     * @param gameName
     * @return 
     */
    public int getGamesBestOffer(String gameName) {
        int bestOffer = -1;
        for (Game g : array) {
            if (g.getGameName().equalsIgnoreCase(gameName.trim())) {
                bestOffer = g.getBestOffer();
            }
        }
        return bestOffer;
    }

    /***
     * Makes offerr for game
     * @param offerPrice
     * @param gameName
     * @param userEmail
     * @param theServer 
     */
    void makeOfferForGame(int offerPrice, String gameName, String userEmail, Server theServer) {
        for (Game g : array) {
            if (g.getGameName().equalsIgnoreCase(gameName)) {
                //pass instance of the server so we can call update if best bid chanegs
                g.makeOffer(offerPrice, userEmail, theServer);
            }
        }
    }

    /**
     * Returns the game order book  as a string
     * @param gameName
     * @return 
     */
    public String getGamesOrderBook(String gameName) {
        for (Game g : array) {
            if (g.getGameName().trim().equalsIgnoreCase(gameName)) {
                return g.displayOrderBook(g.getGameName());
            }
        }

        return "order book not found";
    }

    /***
     * Cancles a users bid
     * @param gameName
     * @param userEmail 
     */
    public void cancleUsersBid(String gameName, String userEmail) {
        for (Game g : array) {
            if (g.getGameName().equalsIgnoreCase(gameName)) {
                //found gamme
                g.cancleUsersBid(userEmail);
            }
        }
    }

    /***
     * Cancles a users offer
     * @param gameName
     * @param userEmail 
     */
    public void cancleUsersOffer(String gameName, String userEmail) {
        for (Game g : array) {
            if (g.getGameName().equalsIgnoreCase(gameName)) {
                //found gamme
                g.cancleUsersOffer(userEmail);
            }
        }
    }

    /**
     * Returns the game object at the index
     * @param index
     * @return 
     */
    public Game getItem(int index) {
        return array[index];
    }

}
