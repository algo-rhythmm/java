import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Vertex implements Comparable<Vertex>{
    int n, w;
    Vertex(int n, int w) {
        this.n = n;
        this.w = w;
    }

    @Override
    public int compareTo(Vertex o) {
        return this.w - o.w;
    }
}

public class BOJ1916_최소비용구하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Vertex>[] adjList = new List[N + 1];

        for(int i=1; i<=N; i++) {
            adjList[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adjList[num1].add(new Vertex(num2, w));
        }

        st = new StringTokenizer(br.readLine());
        int src = Integer.parseInt(st.nextToken());
        int dst = Integer.parseInt(st.nextToken());

        int[] weights = new int[N + 1];
        Arrays.fill(weights, Integer.MAX_VALUE);
        weights[src] = 0;

        boolean[] visit = new boolean[N + 1];

        Queue<Vertex> q = new PriorityQueue<>();
        q.add(new Vertex(src, 0));


        while (!q.isEmpty()) {
            Vertex temp = q.poll();
            if(visit[temp.n]) continue;
            visit[temp.n] = true;


            for(int i=0; i<adjList[temp.n].size(); i++) {
                Vertex vertex = adjList[temp.n].get(i);

                int nextW = weights[temp.n]+ vertex.w;

                if( nextW<weights[vertex.n]) {
                    weights[vertex.n] = nextW;

                    q.add(new Vertex(vertex.n, nextW));
                }

//                System.out.println(Arrays.toString(weights));
            }
        }


        System.out.println(weights[dst]);

    }
}
