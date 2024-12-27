import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14719_빗물 {

    static int R, C, result;
    static boolean[][] map;
    static int[] dr = {-1, 0, 0};
    static int[] dc = {0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 맵 양 끝에 공간을 만들어서 생성한다.
        map = new boolean[R][C + 2];
        result = R * (C + 2);
        st = new StringTokenizer(br.readLine());

        // 맵 만들기
        for(int c = 1; c <= C; c++) {
            int H = Integer.parseInt(st.nextToken());
            for(int r = R - 1; r >= R - H; r--) {
                map[r][c] = true;
                result--;   // 블록이 있는 곳은 빗물이 고일 수 없으므로 빼준다.
            }
        }

        floodFill();

        System.out.println(result);

    }

    // 맵 양 끝에서 시작하여 방문할 수 있는 모든 공간은 빗물이 고일 수 없는 공간이다.
    // dr, dc에서 아래로 내려가는 값이 없으므로 빗물이 고이지 않는 공간만 방문하게 된다.
    static void floodFill() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {R - 1, 0});
        q.offer(new int[] {R - 1, C + 1});
        map[R - 1][0] = true;
        map[R - 1][C + 1] = true;
        result -= 2;

        while(!q.isEmpty()) {
            int[] f = q.poll();
            int r = f[0];
            int c = f[1];

            for(int d = 0; d < 3; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(nr < 0 || nc < 0 || nc >= C + 2 || map[nr][nc]) continue;

                map[nr][nc] = true;
                result--;
                q.offer(new int[] {nr, nc});
            }
        }
    }
}