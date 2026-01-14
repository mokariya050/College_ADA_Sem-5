package lab_quation;

import java.util.ArrayList;
import java.util.Scanner;

public class kruskal_algorithms {
    static int find(int[] parent, int node) {
        if (parent[node] == node) return node;
        return parent[node] = find(parent, parent[node]);
    }

    // Union two subsets
    static void union(int[] parent, int[] rank, int rootU, int rootV) {
        if (rank[rootU] < rank[rootV]) {
            parent[rootU] = rootV;
        } else if (rank[rootV] < rank[rootU]) {
            parent[rootV] = rootU;
        } else {
            parent[rootV] = rootU;
            rank[rootU]++;
        }
    }

    // Merge Sort for edges by weight
    static void mergeSort(int[][] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    static void merge(int[][] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[][] L = new int[n1][3];
        int[][] R = new int[n2][3];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i][2] <= R[j][2]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    static void kruskal(int[][] arr, int nVertices) {
        mergeSort(arr, 0, arr.length - 1);

        ArrayList<int[]> mst = new ArrayList<>();
        int[] parent = new int[nVertices+1];
        int[] rank = new int[nVertices+1];

        for (int i = 0; i <= nVertices; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        int edgeCount = 0;
        int i = 0;

        while (edgeCount < nVertices - 1 && i < arr.length) {
            int u = arr[i][0];
            int v = arr[i][1];
            int w = arr[i][2];

            int rootU = find(parent, u);
            int rootV = find(parent, v);

            if (rootU != rootV) {
                mst.add(arr[i]);
                union(parent, rank, rootU, rootV);
                edgeCount++;
            }
            i++;
        }

        int totalWeight = 0;
        System.out.println("Minimum Spanning Tree:");
        for (int[] edge : mst) {
            System.out.println(edge[0] + " -- " + edge[1] + " == " + edge[2]);
            totalWeight += edge[2];
        }
        System.out.println("Total weight of MST = " + totalWeight);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int nVertices = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int nEdges = sc.nextInt();

        int[][] graph = new int[nEdges][3];
        System.out.println("Enter edges (u v weight):");
        for (int i = 0; i < nEdges; i++) {
            graph[i][0] = sc.nextInt();
            graph[i][1] = sc.nextInt();
            graph[i][2] = sc.nextInt();
        }
        long startTime=System.nanoTime();
        kruskal(graph, nVertices);
        long endTime=System.nanoTime();
        System.out.println("Time complexity for kruskal algorithm is:"+(endTime-startTime)/1000000+" ms");
    }



    }
