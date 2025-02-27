package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 물통_2251 {
    static int A, B, C;
    static boolean[][] visited;
    static Set<Integer> res = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visited = new boolean[201][201];
        dfs(0, 0, C);

        for (int a : res) {
            System.out.print(a + " ");
        }

    }

    public static void dfs(int a, int b, int c) {
        if (visited[a][b]) return;

        if (a == 0) {
//            System.out.println("added " + c);
            res.add(c);
        }
//        System.out.println(a+" "+b+" "+c);
        visited[a][b] = true;
        // A -> B
        if ((a + b) > B) {
            dfs((a + b) - B, B, c);
        } else {
            dfs(0, a + b, c);
        }
        // A -> C
        if ((a + c) > C) {
            dfs((a + c) - C, b, C);
        } else {
            dfs(0, b, a + c);
        }
        // B -> A
        if ((b + a) > A) {
            dfs(A, (b + a) - A, c);
        } else {
            dfs(b + a, 0, c);
        }
        // B -> C
        if ((b + c) > C) {
            dfs(a, (b + c) - C, C);
        } else {
            dfs(a, 0, b + c);
        }
        // C -> A
        if ((c + a) > A) {
            dfs(A, b, (c + a) - A);
        } else {
            dfs(c + a, b, 0);
        }
        // C -> B
        if ((c + b) > B) {
            dfs(a, B, (c + b) - B);
        } else {
            dfs(a, c + b, 0);
        }
    }
}
