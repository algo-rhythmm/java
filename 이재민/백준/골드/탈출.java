package algo0112;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    구현 BFS
    12108KB | 76ms
 */

public class 탈출 {
    static int R, C;
    static Queue<Loc> water;
    static Queue<Loc> dochi;
    static char[][] map;
    static boolean[][] visited;

    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};


    static class Loc {
        int x, y;

        public Loc(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

    }

    static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        water = new ArrayDeque<>();
        dochi = new ArrayDeque<>();
        Loc exit = new Loc(0, 0);
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'S') {
                    dochi.add(new Loc(i, j));
                } else if (map[i][j] == '*') {
                    water.add(new Loc(i, j));
                } else if (map[i][j] == 'D') {
                    exit.x = i;
                    exit.y = j;
                }
            }
        }

        int time = 0;

        A:
        while (true) {
            int size = water.size();
            for (int i = 0; i < size; i++) {
                Loc loc = water.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = loc.x + dx[j];
                    int ny = loc.y + dy[j];
                    if (!rangeCheck(nx, ny)) continue;
                    if (map[nx][ny] == '.' || map[nx][ny] == 'd') {
                        map[nx][ny] = '*';
                        water.add(new Loc(nx, ny));
                    }
                }
            }

            size = dochi.size();
            if (size == 0) {
                System.out.println("KAKTUS");
                System.exit(0);
            }
            for (int i = 0; i < size; i++) {
                Loc loc = dochi.poll();
                if (loc.x == exit.x && loc.y == exit.y) {
                    break A;
                }
                for (int j = 0; j < 4; j++) {
                    int nx = loc.x + dx[j];
                    int ny = loc.y + dy[j];
                    if (!rangeCheck(nx, ny)) continue;
                    if (map[nx][ny] == '.' || map[nx][ny] == 'D') {
                        map[nx][ny] = 'S';
                        dochi.add(new Loc(nx, ny));
                    }
                }
            }
            time++;
        }

        System.out.println(time);

    }
}