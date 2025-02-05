import java.util.*;
import java.io.*;

public class BOJ2251_물통 {
    static class Water {
        int water, left;
        public Water(int w, int t) {
            water = w;
            left = t;
        }
    }
    
    static int A, B, C;
    static boolean[][][] hash;
    static PriorityQueue<Integer> pq = new PriorityQueue();
    static Water W = new Water(0, 0);
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        hash = new boolean[C + 1][C + 1][C + 1];  // C물통만 물이 있으므로 최대값은 C

        dfs(0, 0, C);

        while(!pq.isEmpty()) {
            sb.append(pq.poll());
            sb.append(" ");
        }
        System.out.println(sb);
    }

    static void dfs(int a, int b, int c) {
        if(hash[a][b][c]) return;
        hash[a][b][c] = true;
        if(a == 0) {
            pq.offer(c);
        } else if(a > 0) {
            getWater(a, b, B);
            dfs(W.left, W.water, c);

            getWater(a, c, C);
            dfs(W.left, b, W.water);
        }
        if(b > 0) {
            getWater(a, b, A);
            dfs(W.water, W.left, c);

            getWater(b, c, C);
            dfs(a, W.left, W.water);
        }
        if(c > 0) {
            getWater(a, c, A);
            dfs(W.water, b, W.left);

            getWater(b, c, B);
            dfs(a, W.water, W.left);
        }
    }

    // 물을 옮길 곳의 정보를 알려주면 옮긴 후 물의 양을 알려준다.
    static void getWater(int from, int to, int toLimit) {
        int water = from + to;
        int left = 0;
        if(water > toLimit) left = water - toLimit;
        W.water = water - left;
        W.left = left;
    }
}
