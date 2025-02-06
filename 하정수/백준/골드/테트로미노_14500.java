package 백준;

import java.util.*;
import java.io.*;

/*
 *  내가 생각한 구현 방식은
 *  DFS로 일단 네방향을 돌리면서 큰 값을 저장한다.
 *  그 큰값으로 dfs 재귀를 돌린다.
 *  만약 깊이가 2면 두번째 큰 값을 저장한다.
 *  현재 깊이가 3이 됐으면 네 방향 중 제일 큰 값과 두번째 큰 값을 비교해서 더 큰 값을 더하고
 *  깊이가 4 이상이 됐을 때 max와 비교해서 return한다
 *  DFS(현재 r값, 현재 c값, 현재 깊이, sum값) 으로 재귀를 돌면서 진행.
 *
 *
 *  _________________________________
 *
 *  안되넹,,
 *
 * */

public class 테트로미노_14500 {
    static int N, M, max;
    static int second;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];

        max = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                second = 0;
                DFS(i, j, 1, arr[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(max);

    }

    public static void DFS(int r, int c, int depth, int sum) {
        if (depth >= 4) {
            if (sum > max) {
                max = sum;
            }
            return;
        }

        if (depth == 2) {
            int count = 0;
            int tmp = sum;
            for (int d = 0; d < 4; d++) {
                int rr = r + dr[d];
                int cc = c + dc[d];
                if (rr >= 0 && rr < N && cc >= 0 && cc < M && !visited[rr][cc]) {
                    tmp += arr[rr][cc];
                    count++;
                }
            }

            if (count == 2) {
                if (tmp > max) {
                    max = tmp;
                }
            } else {
                for (int d = 0; d < 4; d++) {
                    int rr = r + dr[d];
                    int cc = c + dc[d];
                    if (rr >= 0 && rr < N && cc >= 0 && cc < M && !visited[rr][cc]) {
                        if ((tmp - arr[rr][cc]) > max) {
                            max = tmp - arr[rr][cc];
                        }
                    }
                }
            }
        }

        for (int d = 0; d < 4; d++) {
            int rr = r + dr[d];
            int cc = c + dc[d];
            if (rr >= 0 && rr < N && cc >= 0 && cc < M && !visited[rr][cc]) {
                visited[rr][cc] = true;
                DFS(rr, cc, depth + 1, sum + arr[rr][cc]);
                visited[rr][cc] = false;
            }
        }
    }
}
