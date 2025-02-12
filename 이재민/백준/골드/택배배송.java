package algo0210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    40468KB | 352ms
 */

public class 택배배송 {

    static class Node implements Comparable<Node> {
        int node, weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    static int N, M;
    static List<List<Node>> graph;
    static int[] distance;

    static void dijkstra(int start) {
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curNode = cur.node;
            int curWeight = cur.weight;
            // 우선순위 큐는 간선의 가중치에 따라 정렬되기 때문에
            // 같은 노드에 대한 구식 값이 존재 할 수 있음
            // 정점 3으로 가는 10의 값이 들어 있었지만
            // 이후에 정점 3으로 가는 7의 값이 들어와서 먼저 연산한다면 10의 값 무시
            if(curWeight > distance[curNode]) {
                continue;
            }

            for(Node neighbor : graph.get(curNode)) {
                int nextNode = neighbor.node;
                int weight = neighbor.weight;
                if(distance[nextNode] > distance[curNode] + weight) {
                    distance[nextNode] = distance[curNode] + weight;
                    pq.add(new Node(nextNode, distance[nextNode]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        distance = new int[N + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, w));
            graph.get(b).add(new Node(a, w));
        }
        dijkstra(1);

        System.out.println(distance[N]);
    }
}
