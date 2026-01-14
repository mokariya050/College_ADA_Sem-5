package lab_quation;
import java.util.Scanner;

public class prims_algorithm {

    static int minKey(int key[], boolean mstSet[], int n) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < n; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }
        return min_index;
    }

    static void printMST(int parent[], int graph[][], int n) {
        int totalCost = 0;
        System.out.println("Edge \tWeight");
        for (int i = 1; i < n; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
            totalCost += graph[i][parent[i]];
        }
        System.out.println("------------------");
        System.out.println("Total Minimum Cost of MST = " + totalCost);
    }

    static void primMST(int graph[][], int n) {
        int parent[] = new int[n];   
        int key[] = new int[n];      
        boolean mstSet[] = new boolean[n]; 


        for (int i = 0; i < n; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < n - 1; count++) {
            int u = minKey(key, mstSet, n); 
            mstSet[u] = true;

            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        System.out.println("MST using Prims is:");
        printMST(parent, graph, n);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        int graph[][] = new int[n][n];
        System.out.println("Enter adjacency matrix (0 if no edge):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        long startTime=System.nanoTime();
        primMST(graph, n);
        long endTime=System.nanoTime();
        System.out.println("\n Time complexity of prims Algorithms is: "+(endTime-startTime)/1000000.0+" ms");
        sc.close();
    }
}

