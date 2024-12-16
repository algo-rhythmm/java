package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 경쟁적전염_18405_골드5
 * PriotyQueue 사용하면 될거같은데 아닌가
 */

public class 경쟁적전염_18405 {
    static int N, K, S, X, Y;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        }
    });
    static Queue<int[]> realQ = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] != 0) {
                    q.add(new int[]{arr[i][j], i, j});
                    visited[i][j] = true;
                }
            }
        }

        int size = q.size();
        for (int i = 0; i < size; i++) {
            realQ.add(q.poll());
        }
        st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        bfs();
    }

    public static void bfs() {
        int time = 0;
        while (!realQ.isEmpty()) {
            if (time == S) break;
            time++;
            int size = realQ.size();
            for (int i = 0; i < size; i++) {
                int[] tmp = realQ.poll();
                for (int j = 0; j < 4; j++) {
                    int rr = dr[j] + tmp[1];
                    int cc = dc[j] + tmp[2];
                    if (rr > 0 && rr <= N && cc > 0 && cc <= N && visited[rr][cc] == false) {
                        arr[rr][cc] = tmp[0];
                        visited[rr][cc] = true;
                        realQ.add(new int[]{tmp[0], rr, cc});
                    }
                }
            }
        }

        System.out.println(arr[X][Y]);
    }
}
