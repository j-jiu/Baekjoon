import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    int getNeighbors(int[] neighbors, int me, int rows, int cols) {//이동

        int r = me / cols;
        int c = me % cols;

        int index = 0;
        if(r-1 >= 0) {//상
            neighbors[index++] = (r-1)*cols+c;
        }
        if(c+1 < cols) {//우
            neighbors[index++] = r*cols+c+1;
        }
        if(r+1 < rows) {//하
            neighbors[index++] = (r+1)*cols+c;
        }
        if(c-1 >=0) {//좌
            neighbors[index++] = r*cols+c-1;
        }
        return index;
    }


    class Value implements Comparable<Value>{//객체간 비교하는 클래스
        int me;
        int cost;

        public Value(int me, int cost) {
            super();
            this.me = me;
            this.cost = cost;
        }

        @Override
        public int compareTo(Value o) {
            return cost - o.cost;
        }

    }

    int getDirection(int me, int next, int cols){
        int mr = me/cols;
        int mc = me%cols;

        int nr = next/cols;
        int nc = next%cols;

        if(mr == nr){
            if(mc>nc){
                return 4;//right
            }else{
                return 1;//left
            }
        }
        if(mc==nc){
            if(mr>nr){
                return 8;//down
            }else{
                return 2;//up
            }
        }
        return 0;
    }

    int dijkstra(int[][] arr, int rows, int cols, int start, int end) {
        // 1) costs[rows*cols] 배열을 만들고 초기 값으로 Integer.MAX_VALUE를 저장한다.
        int[] costs = new int[rows*cols];
        for (int i = 0; i < costs.length; i++) { costs[i] = Integer.MAX_VALUE; }

        // 2) directions[rows*cols] 배열을 만들고 전부 0으로 초기화 한다.
        int[] directions = new int[rows * cols];
        int[] in_queue = new int[rows*cols];

        // 3) 우선순위 큐를 만든다
        PriorityQueue<Value> pq = new PriorityQueue<>();

        // 4) (start, 0)를 큐에 넣는다.
        costs[start] = 0;
        directions[start] = 0xf;
        pq.add(new Value(start, 0));
        in_queue[start]++;

        // 5) start는 사방에서 온 것으로 하고 directions[start] = 상하좌우의 값을 저장한다

        while(!pq.isEmpty()){
            // 큐에서 가장 작은 비용을 가진 것을 가져온다. me라하자
            Value current = pq.poll();
            in_queue[current.me]--;

            if(current.me == end){
                return costs[current.me];
            }

            int[] neighbors  = new int[4];//상하좌우
            int ncount = getNeighbors(neighbors, current.me, rows, cols);//이동가능한 이웃위치 개수
            // me의 이윳들을 구한다.
            for(int i=0;i<ncount;i++){
                // 이를 neighbor이라 하자
                int neighbor = neighbors[i];//이웃
                if(arr[neighbor/cols][neighbor%cols]==Integer.MAX_VALUE) continue;//*(벽) 체크

                // me와 neighbor의 위치를 보아서 상, 하, 좌, 우어디에 해당하는지를 계산한다. 이를 ndir이라 하자
                int ndir =getDirection(current.me, neighbor, cols);//이웃으로의 방향
//                // 되돌아간다면 continue
//                if(ndir==1&&directions[me]==4) continue;//상->하
//                if(ndir==2&&directions[me]==8) continue;//하->상
//                if(ndir==8&&directions[me]==2) continue;//우->좌
//                if(ndir==4&&directions[me]==1) continue;//좌->우

                int ncost = costs[current.me] + (((ndir&directions[current.me])!=0) ? 0:1);
                if(ncost < costs[neighbor]){//기존보다 거울 수가 적으면
                    costs[neighbor] = ncost;
                    // 큐에 (neighbor, ncost)를 저장한다.
                    //(me,cost)
                    pq.add(new Value(neighbor, ncost));
                    in_queue[neighbor]++;
                    directions[neighbor] = ndir;//방향 갱신
                } else if (ncost == costs[neighbor]) {
                    if(in_queue[neighbor] == 0) {
                        directions[neighbor] = ndir;
                        pq.add(new Value(neighbor, ncost));
                        in_queue[neighbor]++;
                    }
                    directions[neighbor] = directions[neighbor]|ndir;

                }

            }

        }
        return 0;
    }



    int answer(int[][] arr, int rows, int cols, int c1, int c2) {

        return dijkstra(arr, rows, cols, c1, c2);

    }

    public static void main(String[] args) throws IOException {
        // 1000000003
        Main    main = new Main();
        StringBuilder result = new StringBuilder();

        String[] test = {
                "7 4\n"
                        + "...C...\n"
                        + ".......\n"
                        + ".*****.\n"
                        + "...C...\n", // 2//

                "3 3\n"
                        + "...\n"
                        + "*.C\n"
                        + "C..", // 1 //

                "7 8\n"
                        + ".......\n"
                        + "......C\n"
                        + "......*\n"
                        + "*****.*\n"
                        + "....*..\n"
                        + "....*..\n"
                        + ".C..*..\n"
                        + ".......", // 3

                "1 2\n"
                        + "C\n"
                        + "C", // 0



                "5 5\n"
                        + "C..**\n"
                        + ".*.**\n"
                        + ".*...\n"
                        + ".***C\n"
                        + ".....",    // 2

                "6 5\n"
                        + "******\n"
                        + "C...**\n"
                        + "**..**\n"
                        + "**...C\n"
                        + "******", // 2


                "3 4\n"
                        + "..C\n"
                        + "...\n"
                        + "...\n"
                        + "C*.", // 1

                "7 7\n"
                        + "...*..C\n"
                        + ".*.*.**\n"
                        + ".*...**\n"
                        + ".***.**\n"
                        + ".***...\n"
                        + ".*****.\n"
                        + "C......", // 4


        };


        //BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader bf = new BufferedReader(new FileReader("test_in.txt"));

        for(int t=0; t<8/*test.length*/;t++) {
            BufferedReader bf = new BufferedReader(new StringReader(test[t]));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            int C1 = -1;
            int C2 = -1;

            int[][] arr = new int[H][W];
            for(int h=0;h<H;h++) {
                String aline = bf.readLine();

                for(int w=0;w<W;w++) {
                    char character = (char)aline.charAt(w);
                    switch(character) {
                        case '*':
                            arr[h][w] = Integer.MAX_VALUE;
                            break;
                        case '.':
                            arr[h][w] = 0;
                            break;
                        case 'C':
                        case 'c':
                            arr[h][w] = 0;
                            if(C1 == -1) {
                                C1= h*W+w;
                            }else if(C2 == -1) {
                                C2= h*W+w;
                            }
                            break;
                    }
                }
            }

            int ans = main.answer(arr, H, W, C1, C2);
            System.out.println(ans);
        }
    }
}
