package algo1205;

import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

/*
 * 검을 먹은것과 안먹은것으로 3차원 배열 이용
 * 레벨별 bfs를 활용하여 탐색
 */

public class 공주님을구해라 {

    static int N, M, T;
    static int[][] map;
    static boolean[][][] visited;
    static int depth;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static class Hero {
        int x, y;
        boolean gram;

        public Hero(int x, int y, boolean gram) {
            this.x = x;
            this.y = y;
            this.gram = gram;
        }

    }

    static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static boolean bfs() {
        Queue<Hero> q = new ArrayDeque<>();

        q.add(new Hero(0, 0, false));
        visited[0][0][0] = true;
        int cnt = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Hero hero = q.poll();
                if(hero.x == N-1 && hero.y == M-1) {
                    System.out.println(cnt);
                    System.exit(0);
                }

                for (int d = 0; d < 4; d++) {
                    int nx = hero.x + dx[d];
                    int ny = hero.y + dy[d];
                    if (!rangeCheck(nx, ny))
                        continue;

                    if (hero.gram) {
                        if (visited[nx][ny][1])
                            continue;
                        visited[nx][ny][1] = true;
                        q.add(new Hero(nx, ny, hero.gram));
                    } else {
                        if (map[nx][ny] == 2) {
                            if(visited[nx][ny][1]) continue;
                            visited[nx][ny][1] = true;
                            q.add(new Hero(nx, ny, true));
                        }
                        else if(map[nx][ny] == 0) {
                            if(visited[nx][ny][0]) continue;
                            visited[nx][ny][0] = true;
                            q.add(new Hero(nx, ny, hero.gram));
                        }
                    }
                }
            }
            cnt++;
            if(cnt > T) {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(!bfs() ? "Fail" : 0);

    }

}