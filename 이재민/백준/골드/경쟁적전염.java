package algo1216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    목표지점의 바이러스는 전염은
    입력 받은 바이러스 위치와의 맨해튼 거리중 짧은 것으로 선택
    16948KB | 124ms

 */

public class 경쟁적전염 {
    static int N, K, S, X, Y;
    static List<Pos> virusPos;

    static class Pos {
        int virus, x, y;

        public Pos(int virus, int x, int y) {
            this.virus = virus;
            this.x = x;
            this.y = y;
        }

    }

    static int solution() {
        int minDist = 40001;
        int res = 0;

        for(Pos p : virusPos) {
            int dist = Math.abs(p.x - (X-1)) + Math.abs(p.y - (Y-1));

            if(dist > S) continue;

            if(dist < minDist) {
                minDist = dist;
                res = p.virus;
            }
            else if(dist == minDist) {
                res = Math.min(res, p.virus);
            }
        }

        return res;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        virusPos = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int virus = Integer.parseInt(st.nextToken());
                if(virus != 0) virusPos.add(new Pos(virus, i, j));
            }
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        System.out.println(solution());
    }
}
