import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class Main {

    void permutation(int n, int curr, int depth, int[] visited, int[] per) {
        if(depth != 0) {
            per[depth-1] = curr;
        }

        if(depth == n) {
            for(int i=0;i<n;i++) {
                System.out.printf("%d ", per[i]);
            }
            System.out.println();

        }

        visited[curr] = 1;
        for(int i=1;i<=n;i++) {
            if(visited[i] == 1)	continue;
            permutation(n, i, depth+1, visited, per);
        }
        visited[curr] = 0;
    }

    boolean found = false;
    boolean dfs(int[] arr, int n, int curr, int depth, int[] visited, int[] per) {
        if(depth != 0) {
            per[depth-1] = curr;
        }
        if(depth == n) {
            if(found == true)	return true;
            for(int i=0;i<n;i++) {
                if(arr[i] != per[i])	return false;
            }
            found = true;
            return false;
        }

        visited[curr] = 1;
        for(int i=1;i<=n;i++) {
            if(visited[i] == 1)	continue;
            // 앞의 dfs0에서 arr을 찾은 경우 true를 리턴하고 못팢으면 계속 찾아야 함
            if(dfs(arr, n, i, depth+1, visited, per) == true) {
                return true;
            }
        }
        visited[curr] = 0;
        return false;
    }

    boolean dfs1(int[] arr, int n, int curr, int depth, int[] visited, int[] per) {

        if(depth != 0) {
            per[depth-1] = curr;
        }

        // 앞과 동일
        if(depth == n) {
            if(found == true)	return true;
            for(int i=0;i<n;i++) {
                if(arr[i] != per[i])	return false;
            }
            found = true;
            return false;
        }
        visited[curr] = 1;

        for(int i=1;i<=n;i++) {
            if(visited[i] == 1)	continue;

            if(found == false && i<arr[depth]) continue;//3 1 2 4라면  1XXX와 2XXX를 돌아볼 필요가 없음.

            if(dfs1(arr, n, i, depth+1, visited, per) == true) {
                return true;
            }
        }
        visited[curr] = 0;
        return false;
    }

    int[] answer(int[] arr, int n) {

        found = true;

        for(int i=0;i<n;i++) {
            if(arr[i] != n-i) {
                found = false;
            }
        }
        if(found == true) {
            return new int[] {-1};
        }

        int[] answer = new int[n];
        int[] map = new int[n+1];
        //permutation(4, 0, 0, map, answer);

        //System.out.println("------");

        dfs1(arr, n, 0, 0, map, answer);

        return answer;
        //return new int[] {-1};

    }

    public static void main(String[] args) throws IOException {

        Main	 main = new Main();

        String[] test = {
                "4\n"
                        + "1 2 4 3", //1 3 2 4
                "4\n"
                        + "1 2 3 4", // 1 2 4 3
                "5\n"
                        + "5 4 3 2 1", // -1
        };

        //BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader bf = new BufferedReader(new FileReader("test_in.txt"));

        for(int t=0;t<3/*test.length*/;t++) {
            BufferedReader bf = new BufferedReader(new StringReader(test[t]));
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int n = Integer.parseInt(st.nextToken());
            int[] arr = new int[n];

            st = new StringTokenizer(bf.readLine());
            for(int i=0;i<n;i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int[] answer = main.answer(arr, n);
            for(int i=0;i<answer.length;i++) {
                System.out.printf("%d ", answer[i]);
            }
            System.out.println();


        }
    }

}

