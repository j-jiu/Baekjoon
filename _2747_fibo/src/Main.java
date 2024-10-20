/*
 아래 fibonacci는 일반적인 해결방법이지만 시간초과에 걸린다.
 아래 fibonacci1은 dp(메모제이션 기법: 다이나믹 프로그래밍)을 사용하여 이 시간초과 문제를 해결한다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class Main {

    int fibonacci(int n) {
        if(n == 0)	return 0;
        if(n == 1)	return 1;

        int fn_1 = fibonacci(n-1);
        int fn_2 = fibonacci(n-2);

        int res = fn_1+fn_2;
        return res;
    }

    long fibonacci1(int n, long[] dp) {
        if(n == 0)	return 0;
        if(n == 1)	return 1;

        if(n<dp.length && dp[n] !=0) return dp[n];//dp에 기록된 fn이 있으면 재사용
        long fn_1 = fibonacci1(n-1,dp);
        long fn_2 = fibonacci1(n-2,dp);

        dp[n] = fn_1+fn_2;//결과 저장
        return dp[n];
    }

    long answer(int n) {
        long[] dp = new long[n+1];
        //return fibonacci(n);
        return fibonacci1(n, dp);
    }

    public static void main(String[] args) throws IOException{

        Main main = new Main();

        String[] test = {
                "5",
                "10",   // 55
                "17",	// 1597
                "45",	//
        };

        //BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader bf = new BufferedReader(new FileReader("d.txt"));
        for(int t=0;t<3;t++) {
            BufferedReader bf = new BufferedReader(new StringReader(test[t]));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(st.nextToken());

            long answer = main.answer(N);
            System.out.println(answer);
        }

    }
}