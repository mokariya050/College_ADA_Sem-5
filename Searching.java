package lab_quation;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Searching {

    // Recursive Binary Search
    static int binarySearch(int[] arr, int key, int start, int end) {
        if (start > end) return -1;

        int mid = start + (end - start) / 2;//reduce overflow

        if (arr[mid] == key) return mid;
        else if (key > arr[mid]) return binarySearch(arr, key, mid + 1, end);
        else return binarySearch(arr, key, start, mid - 1);
    }

    // Linear Search
    static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) return i;
        }
        return -1;
    }

    // Print Array
    static void printArray(int[] arr) {
        System.out.println("Sorted Array: " +Arrays.toString(arr));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Enter the size of the array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        // Fill array with random values
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(10000);  // 0 to 99
        }

        Arrays.sort(arr);
        printArray(arr);


        while (true) {
            System.out.println("Choose Searching Algorithm:");
            System.out.println("1. Linear Search");
            System.out.println("2. Binary Search");
            System.out.println("3. Exit");
            int choice = sc.nextInt();
            if(choice==3){
                System.out.println("Exiting program. Goodbye!");
                sc.close();
                System.exit(0);
            }
            System.out.print("Enter the element to search: ");
            int key = sc.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    long startTime = System.nanoTime();
                    int index = linearSearch(arr, key);
                    long endTime = System.nanoTime();

                    if (index != -1)
                        System.out.println("Element found at index: " + index);
                    else
                        System.out.println("Element not found!");

                    System.out.println("Linear Search Time: "+ (endTime - startTime) / 1000000.0+"milliseconds");
                    System.out.println("--------------------------------------------------------------------------------------------------");
                    break;


                case 2:
                     startTime = System.nanoTime();
                     index = binarySearch(arr, key, 0, n - 1);
                     endTime = System.nanoTime();

                    if (index != -1)
                        System.out.println("Element found at index " + index);
                    else
                        System.out.println("Element not found!");

                    System.out.println("Binary Search Time: "+ (endTime - startTime) / 1000000.0+" milliseconds");
                    System.out.println("--------------------------------------------------------------------------------------------------");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}