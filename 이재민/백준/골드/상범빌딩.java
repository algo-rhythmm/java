package algo1218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 상범빌딩 {
    static int L, R, C;
    static char[][][] map;
    static Pos start, end;
    static boolean[][][] visited;

    // 동 서 남 북 상 하
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static int[] dx = {0, 0, -1, 1, 0, 0};
    static int[] dy = {1, -1, 0, 0, 0, 0};

    static class Pos {
        int z, x, y, m;

        public Pos(int z, int x, int y, int m) {
            this.z = z;
            this.x = x;
            this.y = y;
            this.m = m;
        }
    }

    static boolean rangeCheck(int z, int x, int y) {
        return z >= 0 && z < L && x >= 0 && x < R && y >= 0 && y < C;
    }

    static void bfs() {
        Queue<Pos> q = new ArrayDeque<>();

        q.add(start);
        visited[start.z][start.x][start.y] = true;

        while(!q.isEmpty()) {
            Pos p = q.poll();

            if(p.z == end.z && p.x == end.x && p.y == end.y) {
                System.out.println("Escaped in " + p.m + " minute(s).");
                return;
            }

            for (int d=0; d<6; d++){
                int nz = p.z + dz[d];
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                if(!rangeCheck(nz, nx, ny)) continue;
                if(map[nz][nx][ny] == '#') continue;
                if(visited[nz][nx][ny]) continue;

                visited[nz][nx][ny] = true;
                q.add(new Pos(nz, nx, ny, p.m + 1));
            }
        }

        System.out.println("Trapped!");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        while(true) {
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if(L == 0 && R == 0 && C == 0) break;

            map = new char[L][R][C];
            visited = new boolean[L][R][C];

            for(int i=0; i<L; i++){
                for(int j=0; j<R; j++){
                    String line = br.readLine();
                    for(int k=0; k<C; k++){
                        map[i][j][k] = line.charAt(k);
                        if(line.charAt(k) == 'S') start = new Pos(i, j, k, 0);
                        else if(line.charAt(k) == 'E') end = new Pos(i, j, k, 0);
                    }

                }
                br.readLine();
            }

            bfs();

        }


    }
}
