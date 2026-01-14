package lab_quation;
import java.util.ArrayList;
import java.util.Scanner;

public class chain_matrix_multiplication {

    // Print DP table
    static void print(int arr[][]) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

    static void chain(ArrayList<Integer> d, int n) {
        int arr[][] = new int[n + 1][n + 1];

        // First loop for gap
        for (int L = 1; L < n; L++) {
            for (int i = 1; i <= n - L; i++) {
                int j = i + L;
                arr[i][j] = Integer.MAX_VALUE;

                for (int k = i; k <= j - 1; k++) {
                    int q = arr[i][k] + arr[k + 1][j]
                            + d.get(i - 1) * d.get(k) * d.get(j);
                    if (q < arr[i][j])
                        arr[i][j] = q;
                }
            }
        }

        System.out.println("\n--- DP Table (Minimum Multiplications) ---");
        print(arr);
        System.out.println("\nMinimum number of multiplications required: " + arr[1][n]);
        System.out.println("Theoretical Time Complexity: O(n^3)");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Chain Matrix Multiplication using DP ---");
        System.out.print("Enter how many matrices you have: ");
        int n = sc.nextInt();

        ArrayList<Integer> d = new ArrayList<>();

        // Enter dimensions (n matrices = n+1 numbers)
        for (int i = 0; i < n; i++) {
            System.out.print("Enter rows and columns of matrix " + (i + 1) + ": ");
            int r = sc.nextInt();
            int c = sc.nextInt();
            if (i == 0)
                d.add(r);
            d.add(c);
        }

        // Measure execution time
        long startTime = System.nanoTime();
        chain(d, n);
        long endTime = System.nanoTime();
        System.out.println("Time Complexity of chain matrix multiplication is : "+ (endTime - startTime) / 1000000.0+" ms");

        sc.close();
    }
}
