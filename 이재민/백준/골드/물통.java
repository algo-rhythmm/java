package algo0206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
    20388KB | 68ms
 */

public class 물통 {

    static int A, B, C;
    static boolean[][][] visited;
    static List<Integer> res;

    static void dfs(int a, int b, int c) {
        if(visited[a][b][c]) return;
        visited[a][b][c] = true;
        if(a == 0) res.add(c);

        // a -> b
        dfs(a + b > B ? a - (B - b) : 0, a + b > B ? B : a + b, c);
        // a -> c
        dfs(a + c > C ? a - (C - c) : 0, b, a + c > C ? C : a + c);
        // b -> a
        dfs(a + b > A ? A : a + b, a + b > A ? b - (A - a) : 0, c);
        // b -> c
        dfs(a, b + c > C ? b - (C - c) : 0, b + c > C ? C : b + c);
        // c -> a
        dfs(a + c > A ? A : a + c, b, a + c > A ? c - (A - a) : 0);
        // c -> b
        dfs(a, b + c > B ? B : b + c, b + c > B ? c - (B - b) : 0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visited = new boolean[A+1][B+1][C+1];
        res = new ArrayList<>();
        dfs(0, 0, C);

        Collections.sort(res);
        for(int i=0; i<res.size(); i++){
            System.out.print(res.get(i) + " ");
        }
    }
}
