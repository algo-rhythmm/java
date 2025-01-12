package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 마법사상어와파이어볼_20056 {
    static int N, M, K;
    static int[][] arr;
    static Queue<int[]> q;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        int r = 0;
        int c = 0;
        int m = 0;
        int s = 0;
        int d = 0;

        q = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            arr[r - 1][c - 1] = m; // 맵에 파이어볼 질량 저장

            q.add(new int[]{r - 1, c - 1, m, s, d});
        }

        for (int i = 0; i < K; i++) {
            int size = q.size();
            for (int j = 0; j < size; j++) {
                int[] qq = q.poll();

                arr[qq[0]][qq[1]] -= qq[2]; // 맵에 파이어볼 질량만큼 뺀 다음 이동

                int rr = qq[0] + (qq[3] % N) * dr[qq[4]]; // 0번 행은 N번 행과 연결되어 있으므로
                int cc = qq[1] + (qq[3] % N) * dc[qq[4]]; // 0번 열은 N번 열과 연결되어 있으므로

                rr = rr >= 0 ? rr % N : rr + N;
                cc = cc >= 0 ? cc % N : cc + N;

                arr[rr][cc] += qq[2];
                q.add(new int[]{rr, cc, qq[2], qq[3], qq[4]});
            }

            // 이동이 끝나면
            Queue<int[]> temp = new LinkedList<>();

            while (!q.isEmpty()) {
                int[] now = q.poll();

                if (now[2] < arr[now[0]][now[1]]) {
                    int sum_m = now[2];
                    int sum_s = now[3];
                    boolean isEven = now[4] % 2 == 0;
                    boolean isOdd = now[4] % 2 == 1;
                    int count = 1;

                    int qSize = q.size();
                    for (int j = 0; j < qSize; j++) {
                        int[] next = q.poll();
                        if (now[0] == next[0] && now[1] == next[1]) {
                            sum_m += next[2];
                            sum_s += next[3];
                            count++;

                            if (next[4] % 2 == 0) {
                                if (!isEven) isOdd = false;
                            } else {
                                if (!isOdd) isEven = false;
                            }

                            arr[next[0]][next[1]] -= next[2];
                        } else {
                            q.add(next);
                        }
                    }

                    if (sum_m / 5 > 0) {
                        int new_m = sum_m / 5;
                        int new_s = sum_s / count;
                        arr[now[0]][now[1]] = new_m * 4;

                        int dir = (isEven || isOdd) ? 0 : 1;

                        for (int j = 0; j < 4; j++) {
                            temp.add(new int[]{now[0], now[1], new_m, new_s, j * 2 + dir});
                        }
                    } else {
                        arr[now[0]][now[1]] = 0;
                    }
                } else {
                    temp.add(now);
                }
            }
            q = temp;
        }

        int result = 0;
        while (!q.isEmpty()) {
            result += q.poll()[2];
        }

        System.out.println(result);

    }
}
