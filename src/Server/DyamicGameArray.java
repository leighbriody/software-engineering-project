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

    private Game[] games;
    private int numItems;

    public DyamicGameArray() {

        //default start at 10
        games = new Game[10];
        numItems = 0;
    }

    public void addGame(Game newGame) {

        if (numItems == games.length) {
            growArray();
        }
        games[numItems] = newGame;
        numItems++;
    }

    public void removeItem() {
        numItems--; // might first 0 out of element if security is a concert
    }

    public Game getItem(int index) {
        return games[index];
    }

    public void growArray() {
        Game[] tempArr = new Game[games.length * 2];
        for (int i = 0; i < numItems; i++) {
            tempArr[i] = games[i];
            games = tempArr;
        }
    }

    public void displayArr() {
        for (Game g : games) {
            System.out.println(g);
        }
    }
}
