package algo0203;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    293604KB | 488ms
 */

public class 인구이동 {

    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static List<Pos> pos;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static int movePerson(int x, int y) {
        Queue<Pos> q = new ArrayDeque<>();
        visited[x][y] = true;
        int sum = map[x][y];
        q.add(new Pos(x, y));

        while (!q.isEmpty()) {
            Pos p = q.poll();

            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                if(!rangeCheck(nx, ny)) continue;
                if(visited[nx][ny]) continue;
                int diff = Math.abs(map[nx][ny] - map[p.x][p.y]);
                if(diff < L || diff > R) continue;

                pos.add(new Pos(nx, ny));
                visited[nx][ny] = true;
                sum += map[nx][ny];
                q.add(new Pos(nx, ny));
            }
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        pos = new ArrayList<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int time = 0;
        while(true) {
            boolean flag = false;
            visited = new boolean[N][N];
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++){
                    if(visited[i][j]) continue;
                    pos.clear();
                    pos.add(new Pos(i, j));
                    int sum = movePerson(i, j);
                    if(sum != map[i][j]) {
                        flag = true;
                        for(int k=0; k<pos.size(); k++) {
                            int x = pos.get(k).x;
                            int y = pos.get(k).y;
                            map[x][y] = sum / pos.size();
                        }
                    }
                }
            }
            if(!flag) break;
            time++;

        }

        System.out.println(time);

    }
}
