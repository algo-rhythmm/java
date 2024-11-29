import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 나이트의 이동
 * 테스트 케이스 I
 * 8방향의 dr,dc
 *
 *
 *
 * */

public class 나이트의_이동_7562 {
    static int T, I, res;
    static int[] dr = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dc = {2, 1, -1, -2, -2, -1, 1, 2};
    static boolean[][] visited;
    static Queue<int[]> q;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            I = Integer.parseInt(br.readLine());
            visited = new boolean[I][I];
            q = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            q.add(new int[]{r, c});
            visited[r][c] = true;

            st = new StringTokenizer(br.readLine());
            int end_r = Integer.parseInt(st.nextToken());
            int end_c = Integer.parseInt(st.nextToken());

            if(r == end_r && c == end_c){
                sb.append("0\n");
                continue;
            }

            BFS(end_r, end_c);
        }
        System.out.println(sb);
    }

    public static void BFS(int end_r, int end_c) {
        res = 0;
        Q:
        while (!q.isEmpty()) {
            res++;
            int cnt = q.size();
            for (int i = 0; i < cnt; i++) {
                int[] a = q.poll();
                int r = a[0];
                int c = a[1];
                for (int d = 0; d < 8; d++) {
                    int rr = r + dr[d];
                    int cc = c + dc[d];
                    if (rr >= 0 && cc >= 0 && rr < I && cc < I && visited[rr][cc] == false) {
                        if (rr == end_r && cc == end_c) {
                            sb.append(res + "\n");
                            break Q;
                        }
                        visited[rr][cc] = true;
                        q.add(new int[]{rr, cc});
                    }
                }
            }
        }
    }
}
