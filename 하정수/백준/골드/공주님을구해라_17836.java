import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *   공주님을구해라_17836_골드5
 *   전형적인 BFS 문제
 *   Queue 선언
 *   int 형 N,M,T 선언
 *   boolean형 visited 선언
 *   int 형 2차원 배열 arr 선언
 *   상하좌우 dr,dc 선언
 *   Queue는 int[] = {0,0,0,0} 으로 초기화 (R,C,T,gram)
 *
 *
 * */

public class 공주님을구해라_17836 {
    static int N, M, T;
    static boolean[][][] visited;
    static int[][] arr;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        q.add(new int[]{0, 0, 0, 0});
        visited[0][0][0] = true;

        BFS();
    }

    public static void BFS() {
        while (!q.isEmpty()) {
            int cnt = q.size();
            for (int c = 0; c < cnt; c++) {
                int[] tmp = q.poll();
                if (tmp[0] == N - 1 && tmp[1] == M - 1) {
                    System.out.println(tmp[2]);
                    return;
                }
                if (tmp[2] > T) {
                    System.out.println("Fail");
                    return;
                }
                for (int i = 0; i < 4; i++) {
                    int rr = tmp[0] + dr[i];
                    int cc = tmp[1] + dc[i];
                    if (rr < N && rr >= 0 && cc < M && cc >= 0) {
                        if (visited[rr][cc][tmp[3]]) continue;
                        if (tmp[3] == 1) {
                            visited[rr][cc][1] = true;
                            q.add(new int[]{rr, cc, tmp[2] + 1, 1});
                        } else if (tmp[3] != 2) {
                            if (arr[rr][cc] == 1) {
                                continue;
                            }
                            if (arr[rr][cc] == 0) {
                                visited[rr][cc][0] = true;
                                q.add(new int[]{rr, cc, tmp[2] + 1, 0});
                            } else if (arr[rr][cc] == 2) {
                                visited[rr][cc][1] = true;
                                q.add(new int[]{rr, cc, tmp[2] + 1, 1});
                            }
                        }
                    }
                }
            }

        }
        System.out.println("Fail");
    }
}
