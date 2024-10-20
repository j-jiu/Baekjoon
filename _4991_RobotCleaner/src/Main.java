import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {




    int answer(int[][] arr, int rows, int cols, int robot) {
        List<Integer> distances = new ArrayList<>();
        distances.add(distanceAtoB1);

        //우선순위 큐를 이용하여 거리가 짧은 것부터 remove



        return cost;
    }


    public static void main(String[] args) throws IOException {

        Main main = new Main();
        StringBuilder result = new StringBuilder();


        String test= ""
//				+ "7 5\n"
//		 		+ ".......\n"
//		 		+ ".o...*.\n"
//		 		+ ".......\n"
//		 		+ ".*...*.\n"
//		 		+ ".......\n"
//		 		+ "0 0\n";
//		 		+ "15 13\n"
//		 		+ ".......x.......\n"
//		 		+ "...o...x....*..\n"
//		 		+ ".......x.......\n"
//		 		+ ".......x.......\n"
//		 		+ ".......x.......\n"
//		 		+ "...............\n"
//		 		+ "xxxxx.....xxxxx\n"
//		 		+ "...............\n"
//		 		+ ".......x.......\n"
//		 		+ ".......x.......\n"
//		 		+ ".......x.......\n"
//		 		+ "..*....x....*..\n"
//		 		+ ".......x.......\n"
////		 		+ "0 0\n"
//		 		+ "10 10\n"
//		 		+ "..........\n"
//		 		+ "..o.......\n"
//		 		+ "..........\n"
//		 		+ "..........\n"
//		 		+ "..........\n"
//		 		+ ".....xxxxx\n"
//		 		+ ".....x....\n"
//		 		+ ".....x.*..\n"
//		 		+ ".....x....\n"
//		 		+ ".....x....\n"
//		 		+ "0 0\n"
//		 		+ "7 10\n"
//		 		+ "......x\n"
//		 		+ "*...*.x\n"
//		 		+ ".......\n"
//		 		+ ".......\n"
//		 		+ "....*.*\n"
//		 		+ ".......\n"
//		 		+ ".......\n"
//		 		+ ".o.....\n"
//		 		+ "**.....\n"
//		 		+ "..*....\n"	//22
//		 		+ "0 0\n"
                + "6 8\n"
                + "xx...*\n"
                + "x.....\n"
                + "*...x*\n"
                + "......\n"
                + ".o.*.*\n"
                + "xx.xxx\n"
                + "xx.xxx\n"
                + "xx.xxx\n"	// 14
//				+ "3 2\n"
//				+ "*x.\n"
//				+ "xo*\n"		// 3?
                + "0 0\n";

        //BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader bf = new BufferedReader(new FileReader("test_in.txt"));
        //BufferedReader bf = new BufferedReader(new StringReader(test));


        while(true) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            int robot = -1;
            if(W == 0 && H == 0)	break;


            int[][] arr = new int[H+2][W+2];
            for(int h=1;h<=H;h++) {
                String aline = bf.readLine();

                for(int w=1;w<=W;w++) {
                    char character = (char)aline.charAt(w-1);

                    switch(character) {
                        case 'x':
                            arr[h][w] = Integer.MAX_VALUE;
                            break;
                        case '*':
                            arr[h][w] = 2;
                            break;
                        case '.':
                            arr[h][w] = 1;
                            break;
                        case 'o':
                            arr[h][w] = 1;
                            robot = h*(W+2)+w;
                            break;
                    }
                }
            }
            for(int h=0;h<=H+1;h++) {
                arr[h][0] = arr[h][W+1] = Integer.MAX_VALUE;
            }
            for(int w=0;w<=W+1;w++) {
                arr[0][w] = arr[H+1][w] = Integer.MAX_VALUE;
            }

            int ans = main.answer(arr, H+2, W+2, robot);
            System.out.println(ans);
        }

    }
}