import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    void printGraph(int[][] graph, int n) {
        System.out.printf("    |");
        for (int i = 0; i < n; i++) {
            System.out.printf("%4d", i);
        }
        System.out.printf("\n");
        System.out.printf("_____|");
        for (int i = 0; i < n; i++) {
            System.out.printf("____");
        }
        System.out.printf("\n");

        for (int i = 0; i < n; i++) {
            System.out.printf("%4d|", i);
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == Integer.MAX_VALUE) {
                    System.out.printf("    ");
                } else {
                    System.out.printf("%4d", graph[i][j]);
                }
            }
            System.out.printf("\n");
        }
        System.out.printf("\n");
    }

    int getNeighbors(int[] neighbors, int me, int rows, int cols) {
        int r = me / cols;
        int c = me % cols;

        //up
        int index = 0;
        if (r - 1 >= 0) {
            neighbors[index++] = (r - 1) * cols + c;
        }
        if (c + 1 < cols) {
            neighbors[index++] = r * cols +c+ 1;
        }
        if (r + 1 < rows) {
            neighbors[index++] = (r + 1) * cols + c;
        }
        if (c - 1 >= 0) {
            neighbors[index++] = r * cols + c - 1;
        }
        return index;
    }

    int bfs(char[][] arr, int rows, int cols, int start, int end, int[] costs) {
        Queue<Integer> queue = new LinkedList<>();
        int[] neighbors = new int[4];

        costs[start] = 1;
        queue.add(start);
        while (queue.isEmpty() == false) {
            int me = queue.remove();
            if (me == end) {return costs[me];}
            int neighborCount = getNeighbors(neighbors, me, rows, cols);
            for (int i = 0; i < neighborCount; i++) {
                int neighbor = neighbors[i];
                if (arr[neighbor/cols][neighbor%cols] == '0') continue;
                int cost = costs[me] + (arr[neighbor / cols][neighbor % cols] - '0');
                if (cost < costs[neighbor]){
                    costs[neighbor] = cost;
                    queue.add(neighbor);
                }
            }
        }
        return 0;
    }

    void dfs(char[][] arr, int rows, int cols, int me, int end, int[] costs){
        int[] neighbors = new int[4];
        int neighborCount = getNeighbors(neighbors, me, rows, cols);
        for(int i=0;i<neighborCount;i++){
            int neighbor = neighbors[i];
            if(arr[neighbor/cols][neighbor%cols]=='0') continue;
            int cost = costs[me] + (arr[neighbor/cols][neighbor%cols]-'0');
            if(cost<costs[neighbor]){
                costs[neighbor] = cost;
                dfs(arr,rows,cols,neighbor, end, costs);
            }
        }
    }

    int answer(char[][] arr, int rows, int cols){
        int[] costs = new int[rows*cols];
        for(int i=0; i<rows*cols; i++){
            costs[i] = Integer.MAX_VALUE;
        }
        //int res = bfs(arr, rows, cols, 0, rows*cols-1, costs);
        //return res;
        costs[0] = 1;
        dfs(arr,rows,cols,0,rows*cols-1, costs);
        return costs[rows*cols-1];
    }

    void printArr(char[][] arr, int rows, int cols){
        System.out.print("    |");
        for(int i=0;i<cols; i++){
            System.out.printf("%3d ", i);
        }
        System.out.printf("\n");
        for(int i=0; i<=cols;i++){
            System.out.printf("____");
        }
        System.out.printf("\n");
        for(int i=0; i<rows;i++){
            System.out.printf("%3d|", i);
            for(int j=0; j<cols;j++){
                System.out.printf("%3c ", arr[i][j]);
            }
            System.out.printf("\n");
        }
        System.out.printf("\n");
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        StringBuilder result = new StringBuilder();

//        String[] test = {
//                "4 6\n"
//                        + "101111\n"
//                        + "101010\n"
//                        + "101011\n"
//                        + "111011",
//
//                "4 6\n"
//                        + "110110\n"
//                        + "110110\n"
//                        + "111111\n"
//                        + "111101",
//
//                "2 25\n"
//                        + "1011101110111011101110111\n"
//                        + "1110111011101110111011101",
//
//                "7 7\n"
//                        + "10111111\n"
//                        + "1110001\n"
//                        + "1000001\n"
//                        + "1000001\n"
//                        + "1000001\n"
//                        + "1000001\n"
//                        + "1111111",
//
//        };


        //BufferedReader bf = new BufferedReader(new FileReader("test_in.txt"));

        for(int t=0; t<1/*test.length*/;t++){
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            //BufferedReader bf = new BufferedReader(new StringReader(test[t]));

            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            char[][] arr = new char[n][m];
            for(int i=0;i<n; i++){
                String aline = bf.readLine();
                for(int j=0;j<m;j++){
                    arr[i][j] = aline.charAt(j);
                }
            }
            //main.printArr(arr, n,m);
            int answer = main.answer(arr, n,m);
            System.out.println(answer);
        }

    }
}