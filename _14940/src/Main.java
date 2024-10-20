// 14940
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {

    int getNeighbors(int[] neighbors, int me, int rows, int cols) {

        int r = me / cols;
        int c = me % cols;

        // up
        int index = 0;
        if(r-1 >= 0) {
            neighbors[index++] = (r-1)*cols+c;
        }
        if(c+1 < cols) {
            neighbors[index++] = r*cols+c+1;
        }
        if(r+1 < rows) {
            neighbors[index++] = (r+1)*cols+c;
        }
        if(c-1 >=0) {
            neighbors[index++] = r*cols+c-1;
        }
        return index;
    }



    void bfs(int[][] arr, int rows, int cols, int start, int[] costs) {
        Queue<Integer> queue = new LinkedList<>();
        int[] neighbors = new int[4];

        costs[start] = 0;
        queue.add(start);
        while (queue.isEmpty() == false) {//큐가 빌 때까지
            int me = queue.remove();
            int neighborCount = getNeighbors(neighbors, me, rows, cols);

            for (int i = 0; i < neighborCount; i++) {
                int neighbor = neighbors[i];//상하좌우

                int cost = costs[me] + 1;//distance+1
                if (costs[neighbor]==-1&&arr[neighbor/cols][neighbor%cols] != 0){//갈 수 있으면(!0)
                    costs[neighbor] = cost;
                    queue.add(neighbor);
                }

            }
        }


    }

    int answer(int[][] arr, int rows, int cols, int target, StringBuilder result) {
        int[] costs = new int[rows*cols];
        for(int i=0; i<rows*cols; i++){
            costs[i] = -1;
        }
        bfs(arr, rows, cols, target, costs);
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(arr[i][j] == 0){//도달할 수 없는 0은 0으로
                    System.out.print(0+ " ");
                }else{
                    System.out.print(costs[i*cols+j]+ " ");
                }
            }
            System.out.print("\n");
        }
        return 0;
    }


    void printArr(int[][] arr, int n) {
    }

    public static void main(String[] args) throws IOException {

        Main	 main = new Main();
        StringBuilder result = new StringBuilder();

        String[] test = {
                "15 15\n"
                        + "2 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 0 0 0 0 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 0 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 0 1 0 0 0\n"
                        + "1 1 1 1 1 1 1 1 1 1 0 1 1 1 1",
/*
0 1 2 3 4 5 6 7 8 9 10 11 12 13 14
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
3 4 5 6 7 8 9 10 11 12 13 14 15 16 17
4 5 6 7 8 9 10 11 12 13 14 15 16 17 18
5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
7 8 9 10 11 12 13 14 15 16 17 18 19 20 21
8 9 10 11 12 13 14 15 16 17 18 19 20 21 22
9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
10 11 12 13 14 15 16 17 18 19 20 21 22 23 24
11 12 13 14 15 16 17 18 19 20 0 0 0 0 25
12 13 14 15 16 17 18 19 20 21 0 29 28 27 26
13 14 15 16 17 18 19 20 21 22 0 30 0 0 0
14 15 16 17 18 19 20 21 22 23 0 31 32 33 34
*/
                "3 3\n"
                        + "1 2 1\n"
                        + "1 1 0\n"
                        + "1 0 1",
/*
1 0 1
2 1 0
3 0 -1
 */
                "5 5\n"
                        + "1 0 1 1 0\n"
                        + "1 1 1 0 2\n"
                        + "1 1 0 0 0\n"
                        + "1 1 1 1 1\n"
                        + "1 1 1 1 1",
/*
-1 0 -1 -1 0
-1 -1 -1 0 0
-1 -1 0 0 0
-1 -1 -1 -1 -1
-1 -1 -1 -1 -1
 */
                "3 3\n"
                        + "1 0 1\n"
                        + "0 0 1\n"
                        + "2 0 1",
/*
-1 0 -1
0 0 -1
0 0 -1
 */
                "15 15\n"
                        + "2 0 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "0 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 0 0 0 0 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 0 1 1 1 1\n"
                        + "1 1 1 1 1 1 1 1 1 1 0 1 0 0 0\n"
                        + "1 1 1 1 1 1 1 1 1 1 0 1 1 1 1",
        };


        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader bf = new BufferedReader(new FileReader("test_in.txt"));

        for(int t=0;t<1/*test.length*/;t++) {
            //ufferedReader bf = new BufferedReader(new StringReader(test[t]));

            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());


            int target = -1;
            int[][] arr = new int[n][m];
            for(int i=0;i<n;i++) {
                st = new StringTokenizer(bf.readLine());
                for(int j=0;j<m;j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if(arr[i][j] == 2){
                        target = i*m+j;
                    }
                }
            }

            //main.printArr(arr, n+1);
            int answer = main.answer(arr, n, m, target, result);
            System.out.println(result);

        }
    }

}