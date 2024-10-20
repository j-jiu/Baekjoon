// 2644
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    int getNeighbors(int[] neighbors, int[][] arr, int n, int me) {
        int count = 0;

        for(int i=0;i<n;i++) {
            if(arr[me][i] != 0) {
                neighbors[count++] = i;
            }
        }
        return count;
    }

    //촌수: distance
    int dfs(int[][] arr, int n, int distance, int start, int end, int[] visited) {
        //시작노드 = 최종노드면 return
        if(start == end)
            return distance;

        visited[start] = 1;
        //재귀함수를 이용하여 start->end로
        for(int i=1; i<n; i++){
            //연결되어있고(1) && 방문하지않았으면(0)
            if (arr[start][i] == 1 && visited[i] == 0) {
                int result= dfs(arr, n, distance+1, i, end, visited);
                //최종노드에 도착하면
                if(result !=-1){
                    return result;
                }
            }
        }
        //최종노드에 도착못하면
        return -1;

    }

    int answer(int[][] arr, int n, int p1, int p2) {
        //방문여부배열 생성
        int[] visited = new int[n];
        //dfs호출
        return dfs(arr, n, 0, p1,p2,visited);
    }


    void printArr(int[][] arr, int n) {
        System.out.print("    |");
        for(int i=0;i<n; i++){
            System.out.printf("%3d ", i);
        }
        System.out.printf("\n");
        for(int i=0; i<=n;i++){
            System.out.printf("____");
        }
        System.out.printf("\n");
        for(int i=0; i<n;i++){
            System.out.printf("%3d |  ", i);
            for(int j=0; j<n;j++){
                System.out.print(  arr[i][j] + "   ");
            }
            System.out.printf("\n");
        }
        System.out.printf("\n");

    }


    public static void main(String[] args) throws IOException {

        Main	 main = new Main();
        StringBuilder result = new StringBuilder();

//        String[] test = {
//                "3\n"
//                        + "2 3\n"
//                        + "2\n"
//                        + "1 2\n"
//                        + "2 3",//3
//
//                "9\n"
//                        + "7 3\n"
//                        + "7\n"
//                        + "1 2\n"
//                        + "1 3\n"
//                        + "2 7\n"
//                        + "2 8\n"
//                        + "2 9\n"
//                        + "4 5\n"
//                        + "4 6", // 3
//
//                "9\n"
//                        + "8 6\n"
//                        + "7\n"
//                        + "1 2\n"
//                        + "1 3\n"
//                        + "2 7\n"
//                        + "2 8\n"
//                        + "2 9\n"
//                        + "4 5\n"
//                        + "4 6", // -1
//        };


        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader bf = new BufferedReader(new FileReader("test_in.txt"));

        for(int t=0;t<1/*test.length*/;t++) {
            //BufferedReader bf = new BufferedReader(new StringReader(test[t]));

            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(bf.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(bf.readLine());
            int r = Integer.parseInt(st.nextToken());
            int[][] arr = new int[n+1][n+1];

            for(int i=0;i<r;i++) {
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr[a][b] = 1;
                arr[b][a] = 1;
            }

            //main.printArr(arr, n+1);
            int answer = main.answer(arr, n+1, p1, p2);
            System.out.println(answer);

        }
    }

}
