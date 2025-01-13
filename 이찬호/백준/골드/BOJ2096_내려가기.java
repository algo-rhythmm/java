package week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
N줄 동안 3개의 숫자 (0~9)가 적혀있다.
내려가는동안 세 개의 숫자중 하나를 골라야 하는데 처음에 임의로 고른 후 다음 숫자는
그 숫자와 인접한 숫자만 고를 수 있다. (즉 2칸을 건너뛰진 못함)
얻을 수 있는 최소 점수와 최대 점수를 구하라
================================
입력:
    N (1<=N<=100,000)
    N줄동안 3개의 숫자가 주어짐
=================================
N이 10만이니 딱봐도 dp...

 */
public class BOJ2096내려가기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] minDP = new int[N][3];
        int[][] maxDP = new int[N][3];
        int minAns;
        int maxAns;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                int t = Integer.parseInt(st.nextToken());
                minDP[i][j] = t;
                maxDP[i][j] = t;
            }
        }
        for(int i=1; i<N; i++){
            for(int j=0; j<3; j++){
                if(j==0){
                    maxDP[i][j] = Math.max(maxDP[i-1][j], maxDP[i-1][j+1])+maxDP[i][j];
                    minDP[i][j] = Math.min(minDP[i-1][j], minDP[i-1][j+1])+minDP[i][j];
                }
                else if(j==1){
                    maxDP[i][j] = Math.max(Math.max(maxDP[i-1][j], maxDP[i-1][j-1]), maxDP[i-1][j+1])+maxDP[i][j];
                    minDP[i][j] = Math.min(Math.min(minDP[i-1][j], minDP[i-1][j-1]), minDP[i-1][j+1])+minDP[i][j];
                }
                else {
                    maxDP[i][j] = Math.max(maxDP[i-1][j], maxDP[i-1][j-1])+maxDP[i][j];
                    minDP[i][j] = Math.min(minDP[i-1][j], minDP[i-1][j-1])+minDP[i][j];
                }
            }
        }
        minAns = Math.min(Math.min(minDP[N-1][0],minDP[N-1][1]),minDP[N-1][2]);
        maxAns = Math.max(Math.max(maxDP[N-1][0], maxDP[N-1][1]), maxDP[N-1][2]);

        System.out.println(maxAns+" "+minAns);

    }
}
