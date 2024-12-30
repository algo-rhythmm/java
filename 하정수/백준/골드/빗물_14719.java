package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *   빗물_14719_골드5
 *
 *
 *
 * */

public class 빗물_14719 {
    static int H, W, res;
    static int[][] arr;
    static boolean[][] visited;

    static int[] dr = {0, 0, 1}; // 좌, 우, 하
    static int[] dc = {-1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        arr = new int[H][W];
        visited = new boolean[H][W];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < W; i++) {
            int a = Integer.parseInt(st.nextToken());
            if (a == 0) continue;
            for (int j = H - 1; j >= H - a; j--) {
                arr[j][i] = 1;
                visited[j][i] = true;
            }
        }

        res = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (arr[i][j] == 1 && visited[i][j] && j + 1 < W && !visited[i][j + 1]) {
                    if (check(i, j + 1)) {
                        res = res + 1;
                        DFS(i, j + 1, res);
                    }
                }
            }
        }

        System.out.println(res);
    }

    public static boolean check(int r, int c) {
        for (int i = c; i < W; i++) {
            if (arr[r][i] == 1 && visited[r][i]) {
                return true;
            }
        }
        return false;
    }

    public static void DFS(int r, int c, int result) {
        visited[r][c] = true;
        res = result;
        for (int i = 0; i < 3; i++) {
            int rr = r + dr[i];
            int cc = c + dc[i];
            if (rr < H && cc < W && arr[rr][cc] == 0 && !visited[rr][cc]) {
                DFS(rr, cc, res + 1);
            }
        }
    }
}
