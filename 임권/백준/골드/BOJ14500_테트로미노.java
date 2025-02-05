import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500_테트로미노 {
    
    static int R, C, maxSum = -1;
    static int[][] map;

    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for(int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] v = new boolean[R][C];
        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                v[r][c] = true;
                maxSum = Math.max(maxSum, dfsTetromino(r, c, 1, map[r][c], v));
                v[r][c] = false;
            }
        }

        System.out.println(maxSum);

    }

    static int dfsTetromino(int r, int c, int len, int sum, boolean[][] vi) {
        if(len == 4) {
            return sum;
        }

        int s = 0;

        if(len == 2) {
            int tmp = sum, count = 0;

            //ㅜ ㅓ ㅏ ㅗ 모양을 위해서 길이가 2인 경우 모든 방향을 더한다.
            for(int i = 0; i < dr.length; i++) {
                int rr = r + dr[i];
                int cc = c + dc[i];
                if(rr >= R || rr < 0 || cc >= C || cc < 0 || vi[rr][cc]) continue;
                tmp += map[rr][cc];
                count++;
            }

            if(count == 2) s = Math.max(s, tmp);
            else {
                //그 중 하나를 뺴면서 최대값을 찾는다.
                for(int i = 0; i < dr.length; i++) {
                    int rr = r + dr[i];
                    int cc = c + dc[i];
                    if(rr >= R || rr < 0 || cc >= C || cc < 0 || vi[rr][cc]) continue;
                    s = Math.max(s, tmp - map[rr][cc]);
                }
            }
        }

        for(int i = 0; i < dr.length; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr >= R || nr < 0 || nc >= C || nc < 0 || vi[nr][nc]) continue;
            
            vi[nr][nc] = true;
            s = Math.max(s, dfsTetromino(nr, nc, len + 1, sum + map[nr][nc], vi));
            vi[nr][nc] = false;
        }
        return s;
    }
    
}
