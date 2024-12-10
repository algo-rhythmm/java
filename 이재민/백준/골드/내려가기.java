package algo1210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    이전 상태를 계속해서 저장하는
    DP문제
    54944KB	| 328ms
 */

public class 내려가기 {

    static int N;
    static int[][] score, min, max;

    static void sumScore() {

        for(int i=1; i<N; i++){
            for(int j=0; j<3; j++){
                if(j == 0){
                    min[i][j] = score[i][j] + Math.min(min[i-1][0], min[i-1][1]);
                    max[i][j] = score[i][j] + Math.max(max[i-1][0], max[i-1][1]);
                }
                else if(j == 1){
                    min[i][j] = score[i][j] + Math.min(min[i-1][0], Math.min(min[i-1][1], min[i-1][2]));
                    max[i][j] = score[i][j] + Math.max(max[i-1][0], Math.max(max[i-1][1], max[i-1][2]));
                }
                else {
                    min[i][j] = score[i][j] + Math.min(min[i-1][1], min[i-1][2]);
                    max[i][j] = score[i][j] + Math.max(max[i-1][1], max[i-1][2]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        score = new int[N][3];
        min = new int[N][3]; // 0은 최소, 1은 최대
        max = new int[N][3]; // 0은 최소, 1은 최대

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++){
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        min[0][0] = max[0][0] = score[0][0];
        min[0][1] = max[0][1] = score[0][1];
        min[0][2] = max[0][2] = score[0][2];

        sumScore();

        System.out.println(Math.max(max[N-1][0], Math.max(max[N-1][1], max[N-1][2])) + " " +
                Math.min(min[N-1][0], Math.min(min[N-1][1], min[N-1][2])));
    }
}


