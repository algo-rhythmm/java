package algo0205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    32216KB | 552ms
 */

public class 테트로미노 {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    // 위로 갈 필요 없음
    static int[] dx = {1, 0, 0};
    static int[] dy = {0, -1, 1};

    // 위 오, 외 왼, 아 왼, 아 오
    static int[][] tx = {
            {-1, 0},
            {-1, 0},
            {1, 0},
            {1, 0}
    };
    static int[][] ty = {
            {0, 1},
            {0, -1},
            {0, -1},
            {0, 1}
    };

    static int res;

    static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static void dfs(int x, int y, int count, int sum) {
//        System.out.println(x + " " + y + " " + count + " " + sum);
        if(count == 4) {
            res = Math.max(res, sum);
            return;
        }

        if(count == 2) {
            for (int d = 0; d < 4; d++) {
                int nx1 = x + tx[d][0];
                int nx2 = x + tx[d][1];
                int ny1 = y + ty[d][0];
                int ny2 = y + ty[d][1];

                if(!rangeCheck(nx1, ny1) || !rangeCheck(nx2, ny2)) continue;
                if(visited[nx1][ny1]) continue;
                if(visited[nx2][ny2]) continue;
                res = Math.max(res, sum + map[nx1][ny1] + map[nx2][ny2]);
            }
        }
        for(int d = 0; d < 3; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(!rangeCheck(nx, ny)) continue;
            if(visited[nx][ny]) continue;
            visited[nx][ny] = true;
            dfs(nx, ny, count+1, sum + map[nx][ny]);
            visited[nx][ny] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(res);
    }
}
