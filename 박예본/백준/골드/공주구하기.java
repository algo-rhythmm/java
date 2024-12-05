import java.util.*;
import java.io.*;

public class 공주구하기 {

    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int[][] map;
    static int r, c, T;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[r][c];

        for (int R = 0; R < r; R++) {
            st = new StringTokenizer(br.readLine());
            for (int C = 0; C < c; C++) {
                map[R][C] = Integer.parseInt(st.nextToken());
            }
        }

        int result = findPrincess();
        if (result == -1 || result > T) {
            System.out.println("Fail");
        } else {
            System.out.println(result);
        }
    }

    private static int findPrincess() {
        Queue<int[]> hero = new LinkedList<>();
        boolean[][][] visited = new boolean[r][c][2]; // [row][col][검 사용 여부]
        hero.add(new int[]{0, 0, 0, 0}); // {row, col, 이동 시간, 검 사용 여부}
        visited[0][0][0] = true;

        int minTime = Integer.MAX_VALUE;

        while (!hero.isEmpty()) {
            int[] tmp = hero.poll();
            int row = tmp[0], col = tmp[1], time = tmp[2], hasSword = tmp[3];

            // 시간 초과 시 탐색 종료
            if (time > T) continue;

            // 공주 도달 시 최단 시간 갱신
            if (row == r - 1 && col == c - 1) {
                minTime = Math.min(minTime, time);
                continue;
            }

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int tmpR = row + dr[i];
                int tmpC = col + dc[i];

                // 범위를 벗어나는 경우
                if (tmpR < 0 || tmpC < 0 || tmpR >= r || tmpC >= c) continue;

                // 검이 없을 경우 벽을 통과할 수 없음
                if (map[tmpR][tmpC] == 1 && hasSword == 0) continue;

                // 이미 방문한 경우
                if (visited[tmpR][tmpC][hasSword]) continue;

                // 검을 찾은 경우
                if (map[tmpR][tmpC] == 2) {
                    visited[tmpR][tmpC][1] = true;
                    hero.add(new int[]{tmpR, tmpC, time + 1, 1}); // 검 획득 후 이동
                } else {
                    visited[tmpR][tmpC][hasSword] = true;
                    hero.add(new int[]{tmpR, tmpC, time + 1, hasSword}); // 기존 상태로 이동
                }
            }
        }

        return minTime == Integer.MAX_VALUE ? -1 : minTime;
    }
}
