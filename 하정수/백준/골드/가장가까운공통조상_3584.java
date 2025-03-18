package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장가까운공통조상_3584 {
    static int N;
    static boolean[] visited;
    static int[] parent;

//    static class Node {
//        int val;
//        int child;
//
//        public Node(int val, int child) {
//            this.val = val;
//            this.child = child;
//        }
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            parent = new int[N + 1];
            visited = new boolean[N + 1];
            for (int i = 1; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                parent[y] = x;
            }

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            while (a > 0) {
                visited[a] = true;
                a = parent[a];
            }

            while (b > 0) {
                if (visited[b]) {
                    System.out.println(b);
                    break;
                }
                b = parent[b];
            }
        }
    }
}
