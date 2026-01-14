//this code work for any n x m and m x o matrix 
package lab_quation;

import java.util.Random;
import java.util.Scanner;

public class matrixMultiplication {

    // Strassen's algorithm for 2x2 matrix multiplication
    static int[][] strassen2X2(int A[][], int B[][]) {
        int[][] result = new int[2][2];


        int M1 = (A[0][0] + A[1][1]) * (B[0][0] + B[1][1]);   
        int M2 = (A[1][0] + A[1][1]) * B[0][0];               
        int M3 = A[0][0] * (B[0][1] - B[1][1]);               
        int M4 = A[1][1] * (B[1][0] - B[0][0]);             
        int M5 = (A[0][0] + A[0][1]) * B[1][1];             
        int M6 = (A[1][0] - A[0][0]) * (B[0][0] + B[0][1]);  
        int M7 = (A[0][1] - A[1][1]) * (B[1][0] + B[1][1]);   

        result[0][0] = M1 + M4 - M5 + M7;
        result[0][1] = M3 + M5;
        result[1][0] = M2 + M4;
        result[1][1] = M1 - M2 + M3 + M6;

        return result;
    }

    static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result[i][j] = A[i][j] + B[i][j];
        return result;
    }

    static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result[i][j] = A[i][j] - B[i][j];
        return result;
    }

    static int[][] strassen(int[][] A, int[][] B, int n) {
        if (n == 2) {
            return strassen2X2(A, B);
        }

        int newSize = n / 2;
        int[][] a11 = new int[newSize][newSize];
        int[][] a12 = new int[newSize][newSize];
        int[][] a21 = new int[newSize][newSize];
        int[][] a22 = new int[newSize][newSize];
        int[][] b11 = new int[newSize][newSize];
        int[][] b12 = new int[newSize][newSize];
        int[][] b21 = new int[newSize][newSize];
        int[][] b22 = new int[newSize][newSize];

        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                a11[i][j] = A[i][j];
                a12[i][j] = A[i][j + newSize];
                a21[i][j] = A[i + newSize][j];
                a22[i][j] = A[i + newSize][j + newSize];

                b11[i][j] = B[i][j];
                b12[i][j] = B[i][j + newSize];
                b21[i][j] = B[i + newSize][j];
                b22[i][j] = B[i + newSize][j + newSize];
            }
        }

        int[][] M1 = strassen(add(a11, a22), add(b11, b22), newSize);
        int[][] M2 = strassen(add(a21, a22), b11, newSize);
        int[][] M3 = strassen(a11, subtract(b12, b22), newSize);
        int[][] M4 = strassen(a22, subtract(b21, b11), newSize);
        int[][] M5 = strassen(add(a11, a12), b22, newSize);
        int[][] M6 = strassen(subtract(a21, a11), add(b11, b12), newSize);
        int[][] M7 = strassen(subtract(a12, a22), add(b21, b22), newSize);

        int[][] c11 = add(subtract(add(M1, M4), M5), M7);
        int[][] c12 = add(M3, M5);
        int[][] c21 = add(M2, M4);
        int[][] c22 = add(subtract(add(M1, M3), M2), M6);

        int[][] result = new int[n][n];
        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                result[i][j] = c11[i][j];
                result[i][j + newSize] = c12[i][j];
                result[i + newSize][j] = c21[i][j];
                result[i + newSize][j + newSize] = c22[i][j];
            }
        }

        return result;
    }

    // Print matrix
    static void print_matrix(int arr[][]) {
        for (int[] row : arr) {
            for (int val : row)
                System.out.print(val + " ");
            System.out.println();
        }
    }

    // Get max of 4 numbers
    static int maxNumber(int a, int b, int c, int d) {
        return Math.max(Math.max(a, b), Math.max(c, d));
    }

    // Get next power of 2
    static int nextPowerOfTwo(int n) {
        int power = 1;
        while (power < n) {
            power *= 2;
        }
        return power;
    }

    // main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        System.out.println("Enter number of rows for first matrix:");
        int r1 = sc.nextInt();
        System.out.println("Enter number of columns for first matrix:");
        int c1 = sc.nextInt();
        System.out.println("Enter number of rows for second matrix:");
        int r2 = sc.nextInt();
        System.out.println("Enter number of columns for second matrix:");
        int c2 = sc.nextInt();

        if (c1 != r2) {
            System.out.println("Matrix multiplication not possible.");
            sc.close();
            return;
        }

        int[][] arr = new int[r1][c1];
        int[][] brr = new int[r2][c2];

        // Fill matrices with random numbers
        for (int i = 0; i < r1; i++)
            for (int j = 0; j < c1; j++)
                arr[i][j] = r.nextInt(10);

        for (int i = 0; i < r2; i++)
            for (int j = 0; j < c2; j++)
                brr[i][j] = r.nextInt(10);

        System.out.println("First matrix:");
        print_matrix(arr);
        System.out.println("Second matrix:");
        print_matrix(brr);

        // Pad to power of 2
        int max = maxNumber(r1, c1, r2, c2);
        max=nextPowerOfTwo(max);
        int[][] A = new int[max][max];
        int[][] B = new int[max][max];

        // Copy arr to A and brr to B
        for (int i = 0; i < r1; i++)
            for (int j = 0; j < c1; j++)
                A[i][j] = arr[i][j];

        for (int i = 0; i < r2; i++)
            for (int j = 0; j < c2; j++)
                B[i][j] = brr[i][j];

        // Multiply using Strassen
        long startTime=System.nanoTime();
        int[][] resultFull = strassen(A, B, max);
        long endTime=System.nanoTime();

        // Copy only valid part to final result
        int[][] result = new int[r1][c2];
        for (int i = 0; i < r1; i++)
            for (int j = 0; j < c2; j++)
                result[i][j] = resultFull[i][j];

        System.out.println("Multiplication Result:");
        print_matrix(result);
        System.out.println("Time complexity is: "+(endTime-startTime)/1000000.0+" milliSeconds");

    }
}


