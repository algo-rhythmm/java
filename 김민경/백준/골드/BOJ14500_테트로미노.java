import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14500_테트로미노 {
    static int N, M, MAX=0;
    static int[][] board;
    static int[][] dir = {
            {-1, 0, 1, 0},
            {0, -1, 0, 1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean [][] visit = new boolean[N][M];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                visit[i][j] = true;
                DFS(i, j, visit,0, board[i][j]);
                visit[i][j] = false;
            }
        }

        System.out.println(MAX);
    }

    static void DFS(int r, int c, boolean[][] visit, int cnt, int sum) {
        if(cnt==3) {
            MAX = Math.max(MAX, sum);
            return;
        }

        for (int d=0; d<4; d++) {
            int nr = r+dir[0][d];
            int nc = c+dir[1][d];

            if(nr>=N || nr<0 || nc>=M || nc<0 || visit[nr][nc]) continue;

            if(cnt==1) {
                visit[nr][nc] = true;
                DFS(r, c, visit, cnt + 1, sum+board[nr][nc]);
                visit[nr][nc] = false;
            }

            visit[nr][nc] = true;
            DFS(nr, nc, visit, cnt+1, sum+board[nr][nc]);
            visit[nr][nc] = false;



        }

    }
}
