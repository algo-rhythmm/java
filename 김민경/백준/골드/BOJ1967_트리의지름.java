import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int v, w;
    Node(int v, int w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public String toString() {
        return "Node{" +
                "v=" + v +
                ", w=" + w +
                '}';
    }
}

public class BOJ1967_트리의지름 {
    static int answer=0, start=0, N;
    static List<Node> [] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());


        adjList = new List[N + 1];

        for (int i=0; i<=N; i++) {
            adjList[i] = new ArrayList<>();
        }


        for (int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            int val = Integer.parseInt(st.nextToken());

            adjList[from].add(new Node(to, val));
            adjList[to].add(new Node(from, val));

        }

        BFS(1);

        BFS(start);

        System.out.println(answer);

    }

    static void BFS(int curStart) {
        boolean[] visit = new boolean[N + 1];
        Deque<Node> q = new ArrayDeque<>();
        q.add(new Node(curStart, 0));
        visit[curStart] = true;
        int cnt=0;
        while (!q.isEmpty()) {
            cnt++;
            System.out.println(cnt+" " +q);
            cnt++;
            Node temp = q.poll();
            for (Node node : adjList[temp.v]) {
                System.out.println(temp.v);
                if (visit[node.v]) continue;

                int cmp = temp.w+node.w;
                if(cmp > answer) {
                    answer = cmp;
                    start = node.v;
                }

                q.add(new Node(node.v, cmp));
                visit[node.v] = true;

            }

        }
    }
}
