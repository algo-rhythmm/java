package algo0109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 구현문제
 * 32116KB | 1416ms
 */

public class 마법사상어와파이어볼 {

    static int N, M, K;
    static List<FireBall> fb;
    static int[][] map;

    static int[][] d = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };


    public static class FireBall {
        int r, c, m, s, d;

        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fb = new ArrayList<>();
        map = new int[N][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            fb.add(new FireBall(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
            map[fb.get(i).r][fb.get(i).c] = 1;
        }

        for (int i = 0; i < K; i++) {
            for (int j = 0; j < fb.size(); j++) {
                int r = fb.get(j).r;
                int c = fb.get(j).c;
                int dir = fb.get(j).d;
                int s = fb.get(j).s;

                map[r][c]--;
                s%=N;
                r += d[dir][0] * s;
                c += d[dir][1] * s;
                if(r>=N) r-=N;
                if(c>=N) c-=N;
                if(r<0) r+=N;
                if(c<0) c+=N;

                map[r][c]++;
                fb.get(j).r = r;
                fb.get(j).c = c;

            }
            for (int j = 0; j < N; j++) {
                for (int p = 0; p < N; p++) {
                    if (map[j][p] >= 2) {
                        int cnt = map[j][p]; // 파이어볼 겹친 개수
                        map[j][p] = 4;
                        int newM = 0;
                        int newS = 0;
                        boolean even = true;
                        boolean odd = true;
                        for (int q = 0; q < fb.size(); q++) {
                            int x = fb.get(q).r;
                            int y = fb.get(q).c;
                            if (x == j && p == y) {
                                newM += fb.get(q).m;
                                newS += fb.get(q).s;
                                if (fb.get(q).d % 2 != 0)
                                    even = false;
                                else if (fb.get(q).d % 2 == 0)
                                    odd = false;
                                fb.remove(q--);
                            }
                        }
                        newM /= 5;
                        newS /= cnt;
                        if (newM > 0) {
                            if (even == true || odd == true) {
                                for (int q = 0; q < 7; q += 2) {
                                    fb.add(new FireBall(j, p, newM, newS, q));
                                }
                            } else {
                                for (int q = 1; q < 8; q += 2) {
                                    fb.add(new FireBall(j, p, newM, newS, q));
                                }
                            }
                        } else
                            map[j][p] = 0;
                    }
                }
            }

        } // end k

        int sum = 0;
        for (int i = 0; i < fb.size(); i++) {
            sum += fb.get(i).m;
        }
        System.out.println(sum);
    }
}