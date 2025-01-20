import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
N개의 집, M개의 길 각각 길에서의 비용 C
집을 두 개의 마을로 분리한다.
분리할 때 마을 내부 집들은 서로 연결되어있어야 한다.
마을에는 집이 최소 하나 이상 있어야 한다.
그 후 분리된 마을 사이에서의 길을 없앨수 있다.
또한 두 집을 연결하는 경로가 존재만 한다면 굳이 직접 가는 길이 있어야하는 것은 아님
==========================
입력:
    N, M (2<=N<=100,000)  (1<=M<=1,000,000)
    M줄동안 A,B,C (시작, 끝, 비용) 양방향이다.
==========================
최소신장트리?
문제는 어떻게 두 마을로 나눌 것인가,,
모든 마을을 연결한 후 제일 비싼 간선 하나를 끊는다=처음부터 N-2개의 간선만 연결한다.

PQ로 비용순으로 정렬.

 */
public class BOJ1647도시분할계획 {

    static int[] parent;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static class Node implements Comparable<Node>{
        int s;
        int e;
        int w;
        public Node(int s, int e, int w){
            this.s = s;
            this.e = e;
            this.w = w;
        }
        @Override
        public int compareTo(Node o){
            return this.w- o.w;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        for(int i=1; i<=N; i++){
            parent[i] = i;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.offer(new Node(s,e,w));
        }
        System.out.println(kruskal(N));


    }

    public static int kruskal(int N){
        int cnt = 0;
        int ans = 0;

        while(cnt<N-2){
            Node n = pq.poll();
            if(find(n.s)!=find(n.e)){
                union(n.s, n.e);
                cnt++;
                ans+=n.w;
            }

        }
        return ans;
    }

    public static int find(int x){
        if(parent[x]==x) return x;
        return parent[x] = find(parent[x]);
    }
    public static void union(int x, int y){
        int a = find(x);
        int b = find(y);

        parent[a] = b;
    }
}
