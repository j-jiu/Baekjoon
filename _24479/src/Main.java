import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    int correct = 1;

    void dfs(ArrayList<ArrayList<Integer>> g, int n, int me, int[] answer, int[] visited) {

        //방문여부 확인
        if (visited[me] == 1) return;
        visited[me] = 1;
        answer[me] = correct++;//방문 노드 순서 기록

        ArrayList<Integer> neighbors = g.get(me);
        for (int i = 0; i < neighbors.size(); i++) {
            int neighbor = neighbors.get(i);//i번째 이웃 확인        }
            if (visited[neighbor] == 1) continue;
            dfs(g, n, neighbor, answer, visited);
        }
    }


    int answer(ArrayList<ArrayList<Integer>> g, int n, int start, int[] answer) {

        int[] visited = new int[n+1];
        dfs(g, n, start-1, answer, visited);

        return 0;
    }

    public static void main(String[] args) throws IOException {

        Main main = new Main();

        String[] test = {

                "5 5 1\n"
                        + "1 4\n"
                        + "1 2\n"
                        + "2 3\n"
                        + "2 4\n"
                        + "3 4",    // 1 2 3 4 0

                "5 5 1\n"
                        + "1 2\n"
                        + "2 3\n"
                        + "3 4\n"
                        + "1 5\n"
                        + "5 2", // 1 2 3 4 5

                "5 5 4\n"
                        + "1 4\n"
                        + "1 2\n"
                        + "2 3\n"
                        + "2 5\n"
                        + "3 4", // 2 3 4 1 5

                "6 8 1\n"
                        + "1 6\n"
                        + "1 2\n"
                        + "2 6\n"
                        + "2 3\n"
                        + "2 4\n"
                        + "3 5\n"
                        + "4 5\n"
                        + "3 4", // 1 2 3 4 5 6

                "6 7 1\n"
                        + "1 6\n"
                        + "1 2\n"
                        + "2 6\n"
                        + "2 3\n"
                        + "2 4\n"
                        + "3 5\n"
                        + "4 5", // 1 2 3 5 4 6

                "6 8 4\n"
                        + "1 6\n"
                        + "1 2\n"
                        + "2 6\n"
                        + "2 3\n"
                        + "2 4\n"
                        + "3 5\n"
                        + "4 5\n"
                        + "3 4", // 3 2 5 1 6 4

                "5 5 1\n"
                        + "1 4\n"
                        + "1 2\n"
                        + "2 3\n"
                        + "2 4\n"
                        + "3 4", // 1 2 3 4 0

                "6 4 1\n"
                        + "2 3\n"
                        + "1 4\n"
                        + "1 5\n"
                        + "4 6", // 1 0 0 2 4  3


        };


        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader bf = new BufferedReader(new FileReader("test_in.txt"));

        for (int t = 0; t < 1/*test.length*/; t++) {
            //BufferedReader bf = new BufferedReader(new StringReader(test[t]));

            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken()); // 노드 수
            int m = Integer.parseInt(st.nextToken()); // 간선 수
            int start = Integer.parseInt(st.nextToken());//시작노드

            // 초기화
            ArrayList<ArrayList<Integer>> g = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                g.add(new ArrayList<Integer>()); // 각 노드에 리스트 생성
            }

            //간선
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken())-1;
                int b = Integer.parseInt(st.nextToken())-1;
                //무방향그래프
                g.get(a).add(b);
                g.get(b).add(a);
            }
            // 여기서 위 프로그램에 적합하도록 g를 만들는 코딩을 하라

            //오름차순 정렬
            for(int i=0; i<n; i++){
                Collections.sort(g.get(i));
            }


            int[] answer = new int[n];
            Arrays.fill(answer, 0);//초기화

            main.answer(g, n, start, answer);

            // answer의 값을 문제의 요건에 맞도록 변형하여 결과를 출력하여라.
            for (int i = 0; i < n; i++) {
                System.out.println(answer[i]);
            }
        }
    }
}

