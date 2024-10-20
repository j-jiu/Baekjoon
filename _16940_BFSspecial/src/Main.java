import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;

public class Main {

    int bfs1(ArrayList<int[]> g, int n, int[] sequence, int[] visited) {

        Queue<Integer>	queue = new LinkedList<>();
        int[] path = new int[n];
        int vcount = 0;

        queue.add(sequence[0]);
        visited[sequence[0]] = 1;
        path[vcount++] = sequence[0];

        while(queue.isEmpty() == false) {

            int me = queue.remove();
            if(vcount == n)	break;
            int[] edges = g.get(me);

            for(int i=0;i<edges.length;i++) {
//			for(int i=edges.length-1;i>=0;i--) {
                if(visited[edges[i]] == 1)	continue;
                queue.add(edges[i]);
                visited[edges[i]] = 1;
                path[vcount++] = edges[i];
            }

        }
        for(int i=0;i<vcount;i++) {
            System.out.printf("%d ", path[i]);
        }
        System.out.println();
        return 0;
    }

    int bfs11(ArrayList<int[]> g, int n, int[] sequence, int[] visited) {

        int[] queue = new int[n];
        int qfront = 0;
        int qtrail = 0;

        int[] path = new int[n];
        int vcount = 0;

        //queue.add(sequence[0]);
        queue[qtrail++] = sequence[0];
        visited[sequence[0]] = 1;
        path[vcount++] = sequence[0];

        while(qfront < qtrail/*queue.isEmpty() == false*/) {

            //int me = queue.remove();
            int me = queue[qfront++];
            if(vcount == n)	break;
            int[] edges = g.get(me);

            for(int i=0;i<edges.length;i++) {
//			for(int i=edges.length-1;i>=0;i--) {
                if(visited[edges[i]] == 1)	continue;
                //queue.add(edges[i]);
                queue[qtrail++] = edges[i];
                visited[edges[i]] = 1;
                path[vcount++] = edges[i];
            }

        }
        for(int i=0;i<vcount;i++) {
            System.out.printf("%d ", path[i]);
        }
        System.out.println();
        return 0;
    }

    int bfs12(ArrayList<int[]> g, int n, int[] sequence, int[] visited) {

        long[] tmp = new long[n];

        int[] priorities = new int[n+1];
        for(int i=0;i<n;i++) {
            priorities[sequence[i]] = i;// 각 노드의 우선순위 기록
        }

        Integer[] arr = new Integer[n];
        int front = 0;
        int back = 0;

        int[] path = new int[n];
        int vcount = 0;

        arr[back++] = sequence[0];
        visited[sequence[0]] = 1;


        while(front < back) {

            int me = arr[front++];//dequeue
            path[vcount++] = me;

            if(path[vcount-1] != sequence[vcount-1])	return 0; //break;
            if(vcount == n)	return 1; //break;// 모든 노드 방문 완료

            int[] edges = g.get(me);
            int start = back;

            for(int i=0;i<edges.length;i++) {
                if(visited[edges[i]] == 1)	continue;
                arr[back++] = edges[i];
                visited[edges[i]] = 1;
            }

//			// 여기서 큐의 순서를 가능하면 sequence의 순에 따라 바뀨려고 한다.
            //우선순위대로 정렬
            Comparator<Integer> comp = new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    //priorities의 값을 기준으로 비교
                    return priorities[o1]-priorities[o2];
                }
            };

            //arr을 start~back까지 정렬
            Arrays.sort(arr, start, back, comp);


        }

        for(int i=0;i<n;i++) {
            if(path[i] != sequence[i])	return 0;
        }
        return 1;

    }

    int answer(ArrayList<int[]> g, int n, int[] sequence) {

        int[] visited = new int[n+1];
        if(sequence[0] != 1) return 0;
        return bfs12(g, n, sequence, visited);
    }


    public static void main(String[] args) throws IOException {

        Main	 main = new Main();

        String[] test = {
                "7\n"
                        + "1 2\n"
                        + "1 3\n"
                        + "2 4\n"
                        + "2 5\n"
                        + "3 6\n"
                        + "3 7\n"
                        + "1 3 2 4 5 6 7", // 0

                "10\n"
                        + "1 2\n"
                        + "2 3\n"
                        + "2 4\n"
                        + "3 5\n"
                        + "1 7\n"
                        + "7 10\n"
                        + "6 8\n"
                        + "6 9\n"
                        + "1 6\n"
                        + "1 7 6 2 10 9 8 4 3 5", // 1






                "4\n"
                        + "1 2\n"
                        + "1 3\n"
                        + "2 4\n"
                        + "3 2 4 1\n", // 0

                "4\n"
                        + "1 2\n"
                        + "1 3\n"
                        + "2 4\n"
                        + "1 2 3 4", // 1


                "4\n"
                        + "1 2\n"
                        + "1 3\n"
                        + "2 4\n"
                        + "1 2 4 3", // 0



        };
        //BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader bf = new BufferedReader(new FileReader("test_in.txt"));

        for(int t=0;t<5/*test.length*/;t++) {
            BufferedReader bf = new BufferedReader(new StringReader(test[t]));

            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());

            int[][] arr = new int[n][2];
            int[]	counts = new int[n+1];

            for(int i=0;i<n-1;i++) {
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if(a == b)	continue;
                counts[a]++;
                counts[b]++;
                arr[i][0] = a;
                arr[i][1] = b;
            }

            int[] sequence = new int[n];
            st = new StringTokenizer(bf.readLine());
            for(int i=0;i<n;i++) {
                sequence[i] = Integer.parseInt(st.nextToken());
            }


            ArrayList<int[]> g = new ArrayList<>();
            for(int i=0;i<=n;i++) {
                int[] edges = new int[counts[i]];
                g.add(edges);
            }
            for(int i=0;i<=n;i++) {
                counts[i] = 0;
            }

            for(int i=0;i<n-1;i++) {
                int a = arr[i][0];
                int b = arr[i][1];
                int[] aa = g.get(a);
                int[] bb = g.get(b);
                g.get(a)[counts[a]++] = b;
                g.get(b)[counts[b]++] = a;
            }

            int answer = main.answer(g,  n, sequence);
            System.out.println(answer);


        }
    }

}