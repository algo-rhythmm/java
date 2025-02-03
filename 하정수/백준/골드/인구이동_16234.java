package 백준;

import java.io.*;
import java.util.*;

public class 인구이동_16234 {
    static int N, L, R;
    static Queue<int[]> q;
    static ArrayList<int[]> list;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;

        while (true) {
            boolean flag = false;
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        int sum = BFS(i, j);
                        if (list.size() >= 2) {
                            int avg = sum / list.size();
                            for (int[] tmp : list) {
                                arr[tmp[0]][tmp[1]] = avg;
                            }
                            flag = true;
                        }
                    }
                }
            }
            if (!flag) break;
            day++;
        }
        System.out.println(day);
    }

    public static int BFS(int i, int j) {
        q = new LinkedList<>();
        list = new ArrayList<>();

        q.add(new int[]{i, j, arr[i][j]});
        list.add(new int[]{i, j, arr[i][j]});

        visited[i][j] = true;

        int sum = arr[i][j];

        while (!q.isEmpty()) {
            int[] tmp = q.poll();

            for (int d = 0; d < 4; d++) {
                int rr = tmp[0] + dr[d];
                int cc = tmp[1] + dc[d];
                if (rr >= 0 && rr < N && cc >= 0 && cc < N && !visited[rr][cc]) {
                    int diff = Math.abs(tmp[2] - arr[rr][cc]);
                    if (diff >= L && diff <= R) {
                        q.add(new int[]{rr, cc, arr[rr][cc]});
                        list.add(new int[]{rr, cc, arr[rr][cc]});
                        sum += arr[rr][cc];
                        visited[rr][cc] = true;
                    }

                }
            }
        }
        return sum;
    }
}
