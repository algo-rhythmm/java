import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 트리의지름 {

    public static class Node {
        int val; // 간선 가중치
        int to;  // 연결된 노드
        Node(int val, int to) {
            this.val = val;
            this.to = to;
        }
    }

    static List<Node>[] nodes;
    static boolean[] leaf;
    static int result = 0;
    static boolean[] vis;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;




        nodes = new ArrayList[N + 1];
        leaf = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
            leaf[i] = true;
        }

        for (int t = 1; t < N; t++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            nodes[to].add(new Node(val, from));
            nodes[from].add(new Node(val, to));
            leaf[to] = false; // 부모 노드는 리프가 아님
        }
        vis = new boolean[N + 1];
        // 리프가 아닌 노드부터 경로 계산
        for (int i = N; i >= 1; i--) {
            Arrays.fill(vis,false);
            if (leaf[i]) continue;
            find(i, 0);
        }

        System.out.println(result);
    }

    private static void find(int node, int sum) {
        vis[node] = true;
        result = Math.max(result,sum);

        for(Node n: nodes[node]){
            int to = n.to;
            int val = n.val;
            if(!vis[to]) find(to,val+sum);
        }
    }
}
