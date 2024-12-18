package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *   상범빌딩_6593_골드5
 *   3차원 토마토 문제랑 똑같은 듯.
 *   근데 빈 공백으로 받을때는 처리를 어케 해줘야지?
 *
 * */

public class 상범빌딩_6593 {
    static int L, R, C, sec;
    static char[][][] arr;
    static boolean[][][] visited;
    static int[] dr = {-1, 1, 0, 0, 0, 0};
    static int[] dc = {0, 0, -1, 1, 0, 0};
    static int[] dl = {0, 0, 0, 0, -1, 1};
    static Queue<int[]> q;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        while (L > 0 && R > 0 && C > 0) {
            q = new LinkedList<>();
            arr = new char[L][R][C];
            visited = new boolean[L][R][C]; // 테스트케이스 한 번 돌면 초기화해라!!!

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String str = br.readLine();
//                    System.out.println(str);
                    for (int k = 0; k < C; k++) {
                        arr[i][j][k] = str.charAt(k);
                        if (arr[i][j][k] == 'S' || arr[i][j][k] == '#') {
                            visited[i][j][k] = true;
                            if (arr[i][j][k] == 'S') q.add(new int[]{i, j, k});
                        }
                    }
                }
                String tmp = br.readLine();
            }

            sec = 0;

            BFS();

            st = new StringTokenizer(br.readLine());

            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
        }

        System.out.println(sb);
    }

    public static void BFS() {
        while (!q.isEmpty()) {
            int size = q.size();
            sec++;
            for (int i = 0; i < size; i++) {
                int[] tmp = q.poll();
                for (int d = 0; d < 6; d++) {
                    int ll = tmp[0] + dl[d];
                    int rr = tmp[1] + dr[d];
                    int cc = tmp[2] + dc[d];
                    if (ll >= 0 && ll < L && rr >= 0 && rr < R && cc >= 0 && cc < C && !visited[ll][rr][cc]) {
                        visited[ll][rr][cc] = true;
                        if (arr[ll][rr][cc] == 'E') {
                            sb.append("Escaped in " + sec + " minute(s).\n");
                            return;
                        }
                        q.add(new int[]{ll, rr, cc});
                    }
                }
            }
        }

        sb.append("Trapped!\n");
    }
}
