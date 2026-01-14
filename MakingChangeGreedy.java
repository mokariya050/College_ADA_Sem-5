package lab_quation;
import java.util.ArrayList;
import java.util.Scanner;

public class making_change_greedy {

    static void merge_sort(int arr[], int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            merge_sort(arr, low, mid);
            merge_sort(arr, mid + 1, high);
            combine(arr, low, mid, high);
        }
    }

    static void combine(int arr[], int low, int mid, int high) {
        int left = low;
        int right = mid + 1;
        int temp[] = new int[high - low + 1];
        int idx = 0;

        while (left <= mid && right <= high) {
            if (arr[left] > arr[right]) {
                temp[idx++] = arr[left++];
            } else {
                temp[idx++] = arr[right++];
            }
        }
        while (left <= mid) {
            temp[idx++] = arr[left++];
        }
        while (right <= high) {
            temp[idx++] = arr[right++];
        }

        // Copy back into original array
        for (int i = 0; i < temp.length; i++) {
            arr[low + i] = temp[i];
        }
    }

    static void making_chnage(int coin[], int change) {
       
        merge_sort(coin, 0, coin.length - 1);

        System.out.print("Coins sorted in descending order: ");
        for (int c : coin) System.out.print(c + " ");
        System.out.println();

        ArrayList<Integer> result = new ArrayList<>();
        int n = 0;
        
        while (n <= coin.length - 1 && change > 0) {
            if (coin[n] <= change) {
                result.add(coin[n]);
                change -= coin[n];
            } else {
                n++;
            }
        }

        System.out.println("\n--- Greedy Making Change Result ---");
        if (change == 0) {
            System.out.println("Selected coins: " + result);
        } else {
            System.out.println("Perfect change not possible. Closest selection of coins: " + result);
        }

        System.out.println("Total coins used: " + result.size());

        int total = result.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Total change made: " + total);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of coin denominations: ");
        int n = sc.nextInt();
        int coin[] = new int[n];
        System.out.println("Enter the coin denominations:");
        for (int i = 0; i < n; i++) {
            coin[i] = sc.nextInt();
        }

        System.out.print("Enter Your Number you want to change: ");
        int change = sc.nextInt();

        long startTime = System.nanoTime();
        making_chnage(coin, change);
        long endTime = System.nanoTime();

        System.out.println("\nTheoretical Time Complexity: O(n log n)");
        System.out.println("Time Complexity for making change problem using Greedy is: "
                + (endTime - startTime) / 1000000.0 + " ms");
    }
}

