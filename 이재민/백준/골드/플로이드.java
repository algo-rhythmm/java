package algo0212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class 플로이드 {

    static final int INT_VAL = 1000000000;

    static int N, M;
    static int[][] map;

    static void init() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(i == j) continue;
                map[i][j] = INT_VAL;
            }
        }
    }

    static void floydWarshall() {

        for(int k=1; k<=N; k++){
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];

        init();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            map[a][b] = Math.min(map[a][b],w);
        }
        floydWarshall();

        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                System.out.print((map[i][j] == INT_VAL ? 0 : map[i][j]) + " ");
            }
            System.out.println();
        }

    }
}
