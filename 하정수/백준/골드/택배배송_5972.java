package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 *  택배배송_5972_골드5
 *  다익스트라 문제.
 *
 * */

public class 택배배송_5972 {
    static class Node {
        int to, val;

        public Node(int to, int val) {
            this.to = to;
            this.val = val;
        }
    }

    static int N, M;
    static int[] dist;
    static ArrayList<Node>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            arr[from].add(new Node(to, val));
            arr[to].add(new Node(from, val));
        }

        dist = new int[N + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        dijkstra();
        System.out.println(dist[N]);

    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        pq.add(new Node(1, 0));
        dist[1] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            for (int i = 0; i < arr[current.to].size(); i++) {
                Node next = arr[current.to].get(i);
                if (dist[next.to] > dist[current.to] + next.val) {
                    dist[next.to] = dist[current.to] + next.val;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }
        }
    }
}
