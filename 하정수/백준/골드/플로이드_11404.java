package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 *  이 문제는 플로이드워셜로 풀라는 문제지만 다익스트라로 구현해보았다.
 *  시간 복잡도를 생각해봤을 때, 플로이드워셜은 최악의 경우 N^3이고,
 *  다익스트라는 N*MlogN 이라고 한다.
 *  그래서 일반적인 경우는 다익스트라가 쬐끔 더 유리할 수도 있지만,
 *  우리는 항상 최악의 경우를 생각해야 하기 때문에,
 *  문제의 조건에서 M의 최대 개수가 100,000개 이므로 N의 최대 수인 100의 2승 10,000 보다 결국
 *  크기 때문에 결론적으로 다익스트라는 N^3logN 이 되고, 플로이드워셜은 N^3이기 때문에
 *  플로이드 워셜로 푸는 것이 더 적합하다고 볼 수 있다고 한다.
 *
 * */

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

            if (dist[from][to] > val) {
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
