import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//class Node implements Comparable<Node>{
//    int i, weight;
//    Node (int i, int weight) {
//        this.i = i;
//        this.weight = weight;
//    }
//
//    @Override
//    public int compareTo(Node o) {
//        return this.weight - o.weight;
//    }
//
//}

public class BOJ5972_택배배송 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//
//        List<Node>[] adjList = new List[N + 1];
//        for (int i=1; i<=N; i++) {
//            adjList[i] = new ArrayList<>();
//        }
//        for (int i=0; i<M; i++) {
//            st = new StringTokenizer(br.readLine());
//
//            int a = Integer.parseInt(st.nextToken());
//            int b = Integer.parseInt(st.nextToken());
//            int weight = Integer.parseInt(st.nextToken());
//
//            adjList[a].add(new Node(b, weight));
//            adjList[b].add(new Node(a, weight));
//        }
//
//        PriorityQueue<Node> q = new PriorityQueue<>();
//        boolean[] visit = new boolean[N + 1];
//        int answer=0;
//
//        q.offer(new Node(1, 0));
//
//        while (!q.isEmpty()) {
//
//            Node curr = q.poll();
//            if (visit[curr.i]) continue;
//            if (curr.i==N) {
//                answer = curr.weight;
//                break;
//            }
//            visit[curr.i] = true;
//
//            for (Node node: adjList[curr.i]) {
//                if (visit[node.i]) continue;
//
//                q.offer(new Node(node.i, curr.weight+node.weight));
//            }
//
//        }
//        System.out.println(answer);
//    }
}
