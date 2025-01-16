package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  빙산_2573_골드4
 *
 *  빙산을 입력받는다. int[N][M]
 *  빙산을 돌면서 옆에 바닷물이 몇 개 있는지 체크하고 그 갯수만큼 수를 줄인다.
 *  줄이면서 0이 된 빙산은 그 근처에 빙산에게 영향을 끼치지 않도록 한다.
 *  다 줄이고 나면 DFS로 덩어리가 몇개인지 체크한다.
 *
 * */

public class 빙산_2573 {

    static int[][] arr;
    static boolean[][] visited, visited_clone;
    static int N, M, ans;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];
        visited_clone = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0) {
                    visited[i][j] = true;
                }
            }
        }

        int cnt = 0;

        while (true) {
            cnt++;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] != 0 && !visited[i][j]) {
                        for (int d = 0; d < 4; d++) {
                            int rr = i + dr[d];
                            int cc = j + dc[d];
                            if (rr >= 0 && rr < N && cc >= 0 && cc < M && visited[rr][cc] == true) {
                                if (arr[i][j] != 0) {
                                    arr[i][j]--;
                                }
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] == 0) {
                        visited[i][j] = true;
                    }
                }
            }

            for(int i = 0; i < N; i++) {
                visited_clone[i] = visited[i].clone();
            }
            int separate = 0;


            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] != 0 && !visited_clone[i][j]) {
                        separate++;
                        visited_clone[i][j] = true;
                        DFS(i, j);
                    }
                }
            }
            if (separate == 0) {
                System.out.println(0);
                return;
            } else if (separate >= 2) {
                System.out.println(cnt);
                return;
            }

        }
    }

    public static void DFS(int r, int c) {
        for (int d = 0; d < 4; d++) {
            int rr = r + dr[d];
            int cc = c + dc[d];
            if (rr >= 0 && rr < N && cc >= 0 && cc < M && !visited_clone[rr][cc]) {
                visited_clone[rr][cc] = true;
                DFS(rr, cc);
            }
        }
    }

}
