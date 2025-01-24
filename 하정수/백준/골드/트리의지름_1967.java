package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 트리의지름_1967 {
    static class Node {
        int to, val;

        public Node(int to, int val) {
            this.to = to;
            this.val = val;
        }
    }

    static int N, max;
    static boolean[] isLeaf, visited;
    static ArrayList<Node>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new ArrayList[N + 1];
        isLeaf = new boolean[N + 1]; // leaf 노드인지 체크해주는 배열

        Arrays.fill(isLeaf, true);

        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            arr[from].add(new Node(to, val));
            arr[to].add(new Node(from, val));
            isLeaf[from] = false; // from에 없는 노드들이 leaf노드이기 때문
        }

        max = 0;

        for (int i = 1; i <= N; i++) {
            if (isLeaf[i]) {
                visited = new boolean[N + 1];
                DFS(i, 0);
            }
        }

        System.out.println(max);
    }

    static void DFS(int from, int sum) {
        visited[from] = true;
        for (int i = 0; i < arr[from].size(); i++) {
            Node child = arr[from].get(i);
            int val = child.val;
            if (visited[child.to] == false) {
                DFS(child.to, sum + val);
            }
        }
        max = Math.max(max, sum);
    }
}
