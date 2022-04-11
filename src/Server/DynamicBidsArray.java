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
public class DynamicBidsArray {

    private Bid[] bids;
    private int numItems;

    public DynamicBidsArray() {

        //default start at 10
        bids = new Bid[10];
        numItems = 0;
        
        //init
        

    }

    public void addBid(Bid newBid) {

        if (numItems == bids.length) {
            growArray();
        }
        bids[numItems] = newBid;
        numItems++;
    }

    public void removeItem() {
        numItems--; // might first 0 out of element if security is a concert
    }

    public Bid getItem(int index) {
        return bids[index];
    }

    public void growArray() {
        Bid[] tempArr = new Bid[bids.length * 2];
        for (int i = 0; i < numItems; i++) {
            if (bids[i] == null) {
                tempArr[i] = new Bid(20, "leigh@gmail.com");
            } else {
                tempArr[i] = bids[i];
            }

            bids = tempArr;
        }
    }

    //will put merge sort
    public  void mergeSort(Bid[] a, int n) {
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

    //sort bids 
    //will sort 
    // A utility function to swap two elements
    static void swap(Bid[] arr, int i, int j) {
        Bid temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void sortArray() {
       this.mergeSort(bids, bids.length);
    }

    static int partition(Bid[] arr, int low, int high) {

        int pivot = arr[high].getPrice();

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller
            // than the pivot
            if (arr[j].getPrice() < pivot) {

                // Increment index of
                // smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    public void quickSort(Bid[] arr, int low, int high) {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public void displayArr() {
        for (Bid b : bids) {
            if (b != null) {
                System.out.println(b.toString());
            }

        }
    }
}
