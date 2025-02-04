import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ1303_전쟁전투 {
    static int[][] dir = {
            {-1, 0, 1, 0},
            {0, -1, 0, 1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] board = new char[M][N];
        boolean[][] visit = new boolean[M][N];
        for (int i=0; i<M; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int w = 0;
        int b = 0;

        Deque<Integer[]> q = new ArrayDeque<>();

        for (int i=0; i<M; i++) {
            for (int j=0; j<N; j++) {
                if(visit[i][j]) continue;

                char currChar = board[i][j];
                int cnt = 1;
                q.add(new Integer[] {i, j});
                visit[i][j] = true;

                while (!q.isEmpty()) {

                    Integer[] temp = q.poll();
                    int r = temp[0];
                    int c = temp[1];

                    for (int d=0; d<4; d++) {
                        int nr = r+dir[0][d];
                        int nc = c+dir[1][d];

                        if(nr<0 || nr>=M || nc<0 || nc>=N || visit[nr][nc]) continue;

                        if(board[nr][nc]==currChar) {
                            visit[nr][nc] = true;
                            q.offer(new Integer[]{nr, nc});
                            cnt++;
                        }
                    }

                }

                if(currChar=='W') w+=cnt*cnt;
                else b+=cnt*cnt;
            }
        }

        System.out.println(w + " "+ b);
    }
}
