package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  전쟁_전투_1303_실버1
 *
 *  DFS문제
 *  N,M int 형 선언
 *  배열 char[][] 선언
 *  방문 배열 boolean[][] 선언
 *
 *  dr, dc 선언
 *  team1, team2 int 형 선언해서 DFS 끝날때 마다 더해줘
 *  0,0 부터 DFS 돌려
 *
 *
 *
 * */

public class 전쟁_전투_1303 {

    static int N, M, team1, team2, depth;
    static char[][] arr;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        team1 = 0;
        team2 = 0;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    depth = 1;
                    DFS(i, j, arr[i][j]);
                    if (arr[i][j] == 'W') {
                        team1 += depth * depth;
                    } else {
                        team2 += depth * depth;
                    }
                }
            }
        }

        System.out.println(team1 + " " + team2);

    }

    static void DFS(int i, int j, char ch) {
        visited[i][j] = true;
        for (int d = 0; d < 4; d++) {
            int rr = i + dr[d];
            int cc = j + dc[d];
            if (rr >= 0 && rr < M && cc >= 0 && cc < N && !visited[rr][cc] && arr[rr][cc] == ch) {
                depth++;
                DFS(rr, cc, ch);
            }
        }
    }

}
