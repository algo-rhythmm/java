package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 플로이드_11404 {
    static class Node {
        int to, val;

        public Node(int to, int val) {
            this.to = to;
            this.val = val;
        }
    }

    static int N, M;
    static int[][] dist;
    static ArrayList<Node>[] arr;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N + 1][N + 1];
        arr = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            if(dist[from][to] > val){
                dist[from][to] = val;
            }

            arr[from].add(new Node(to, val));
        }

        for (int i = 1; i <= N; i++) {
            dijkstra(i);
            for (int j = 1; j <= N; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    dist[i][j] = 0;
                }
                sb.append(dist[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        pq.add(new Node(start, 0));
        dist[start][start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            for (int i = 0; i < arr[current.to].size(); i++) {
                Node next = arr[current.to].get(i);
                if (dist[start][next.to] > dist[start][current.to] + next.val) {
                    dist[start][next.to] = dist[start][current.to] + next.val;
                    pq.add(new Node(next.to, dist[start][next.to]));
                }
            }
        }

    }
}
